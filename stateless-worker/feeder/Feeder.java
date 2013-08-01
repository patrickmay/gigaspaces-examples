/**
 * Feeder writes Request objects to a space.  The number of Requests to
 * write and the number of simultaneous threads is specified on the
 * command line.
 *
 * @author Patrick May (patrick.may@gigaspaces.com)
 * @author &copy; 2013 GigaSpaces Technologies Inc.  All rights reserved.
 * @version 1
 */

package com.gigaspaces.examples.stateless.feeder;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.examples.stateless.shared.Request;

import java.util.logging.Logger;

public class Feeder implements Runnable
{
  private static Logger logger_ = Logger.getLogger(Feeder.class.getName());

  private String spaceURL_ = null;
  private long requestCount_ = 0;
  private int threadCount_ = 0;
  private int blockSize_ = 0;

  private GigaSpace gigaSpace_ = null;

  /**
   * RequestFeeder is used by Feeder to write Request objects in
   * multiple threads.
   */
  private class RequestFeeder implements Runnable
  {
    private long count_ = 0;
    private long startID_ = 0;
    private int blockSize_ = 0;

    /**
     * The full constructor for the RequestFeeder class.
     *
     * @param requestCount The total number of Request objects to feed.
     * @param startID The Request ID to start numbering from.
     * @param blockSize The number of Request objects to write simultaneously.
     */
    public RequestFeeder(long count,long startID,int blockSize)
      {
      count_ = count;
      startID_ = startID;
      blockSize_ = blockSize;
      }


    /**
     * Write Request objects to the space in batches.
     */
    private void writeMultiple()
      {
      logger_.info("Writing " + count_ + " Requests to the space.");

      try
        {
        long remaining = count_;
        int blockSize = blockSize_;
        Request[] requests = new Request[blockSize];
        long id = startID_;

        while (remaining > 0)
          {
          if (blockSize > remaining)
            {
            blockSize = (new Long(remaining)).intValue();
            requests = new Request[blockSize];
            }

          for (int i = 0;i < blockSize;i++)
            requests[i] = new Request(id++);

          gigaSpace().writeMultiple(requests);

          remaining -= blockSize;
          }

        logger_.info(Long.toString(count_) + " Request objects written.");
        }
      catch (Exception e)
        {
        logger_.severe("Error writing Requests:  " + e.toString());
        }
      }


    /**
     * Write Request objects to the space one at a time.
     */
    private void write()
      {
      logger_.info("Writing " + count_ + " Requests to the space.");

      try
        {
        for (long i = 0;i < count_;i++)
          gigaSpace().write(new Request(startID_ + i));

        logger_.info(Long.toString(count_) + " Request objects written.");
        }
      catch (Exception e)
        {
        logger_.severe("Error writing Requests:  " + e.toString());
        }
      }


    /**
     * Write Request objects to the space.
     */
    private void writeRequests()
      {
      if (blockSize_ > 1)
        writeMultiple();
      else
        write();
      }


    /**
     * Run the RequestFeeder.  This method is inherited from Runnable.
     */
    public void run()
      {
      logger_.info("Running...");

      writeRequests();

      logger_.info("Done.");
      }
  }  // end RequestFeeder


  /**
   * Return a proxy to the GigaSpace, creating it if necessary.
   */
  private GigaSpace gigaSpace()
    {
    if (gigaSpace_ == null)
      {
      UrlSpaceConfigurer urlSpaceConfigurer
        = new UrlSpaceConfigurer(spaceURL_);
      GigaSpaceConfigurer gigaSpaceConfigurer
        = new GigaSpaceConfigurer(urlSpaceConfigurer.space());

      gigaSpace_ = gigaSpaceConfigurer.gigaSpace();
      }

    return gigaSpace_;
    }


  /**
   * The full constructor for the Feeder class.
   *
   * @param spaceURL The URL of the GigaSpace to feed.
   * @param requestCount The total number of Request objects to feed.
   * @param threadCount The number of feeding threads.
   * @param blockSize The number of Request objects to write simultaneously.
   */
  public Feeder(String spaceURL,
                long requestCount,
                int threadCount,
                int blockSize)
    {
    spaceURL_ = spaceURL;
    requestCount_ = requestCount;
    threadCount_ = threadCount;
    blockSize_ = blockSize;
    }


  /**
   * Wait for all threads to finish.
   *
   * @param threads The array of threads to wait for.
   */
  private void waitForThreads(Thread[] threads)
    {
    for (int i = 0;i < threads.length;i++)
      {
      try { threads[i].join(); }
      catch (InterruptedException ignore) { }
      }
    }


  /**
   * Kick off the specified number of threads to write the specified
   * number of Request objects to the space.
   */
  private void feed()
    {
    long perThreadCount = requestCount_ / threadCount_;
    long startID = 0;

    Thread[] threads = new Thread[threadCount_];

    for (int i = 0;i < threadCount_;i++)
      {
      if (i == (threadCount_ - 1))  // last batch
        perThreadCount = requestCount_ - (i * perThreadCount);

      startID = perThreadCount * i;

      threads[i]
        = new Thread(new RequestFeeder(perThreadCount,startID,blockSize_));
      threads[i].start();
      }

    waitForThreads(threads);
    }


  /**
   * Run the Feeder.  This method is inherited from Runnable.
   */
  public void run()
    {
    logger_.info("Running...");

    feed();

    logger_.info("Done.");
    }


  /**
   * The command line interface for the Feeder class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 4)
      {
      String spaceURL = args[0];
      long requestCount = Long.parseLong(args[1]);
      int threadCount = Integer.parseInt(args[2]);
      int blockSize = Integer.parseInt(args[3]);

      if ((requestCount > 0) && (threadCount > 0) && (blockSize > 0))
        {
        Feeder feeder
          = new Feeder(spaceURL,requestCount,threadCount,blockSize);
        feeder.run();
        }
      else
        logger_.severe("Request count, thread count, and block size must all be greater than zero.");
      }
    else
      logger_.info("Usage:  java "
                   + Feeder.class.getName()
                   + " <space-url> <request-count> <thread-count> <block-size>");
    }
}  // end Feeder
