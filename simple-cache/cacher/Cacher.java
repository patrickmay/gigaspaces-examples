/**
 * Cacher writes Swag objects to a space and reads them back.  The
 * number of objects to write is specified on the command line.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.cache.cacher;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.util.logging.Logger;

public class Cacher implements Runnable
{
  private static Logger logger_ = Logger.getLogger(Cacher.class.getName());

  private String spaceURL_ = null;
  private long swagCount_ = 0;

  private GigaSpace gigaSpace_ = null;

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
   * The full constructor for the Cacher class.
   *
   * @param spaceURL The URL of the GigaSpace cache.
   * @param swagCount The total number of Swag objects to cache.
   */
  public Cacher(String spaceURL,long swagCount)
    {
    spaceURL_ = spaceURL;
    swagCount_ = swagCount;
    }


  /**
   * Write Swag objects to the space.
   */
  private void cache()
    {
    logger_.info("Writing " + swagCount_ + " Swags to the space.");

    try
      {
      for (long i = 0;i < swagCount_;i++)
        gigaSpace().write(new Swag(i));

      logger_.info(Long.toString(swagCount_) + " Swag objects written.");
      }
    catch (Exception e)
      {
      logger_.severe("Error writing Swags:  " + e.toString());
      }
    }


  /**
   * Read Swag objects from the space.
   */
  private void retrieve()
    {
    logger_.info("Retrieving " + swagCount_ + " Swags from the space.");

    Swag template = new Swag();
    Swag swag = null;
    try
      {
      for (long i = 0;i < swagCount_;i++)
        {
        template.setId(new Long(i));
        swag = gigaSpace().read(template);
        if (swag == null)
          {
          logger_.severe("Unable to find cached Swag.");
          break;
          }
        }

      logger_.info(Long.toString(swagCount_) + " Swag objects retrieved.");
      }
    catch (Exception e)
      {
      logger_.severe("Error retrieving Swags:  " + e.toString());
      }
    }


  /**
   * Read Swag objects from the space.
   */
  private void remove()
    {
    logger_.info("Removing " + swagCount_ + " Swags from the space.");

    Swag template = new Swag();
    Swag swag = null;
    try
      {
      for (long i = 0;i < swagCount_;i++)
        {
        template.setId(new Long(i));
        swag = gigaSpace().take(template);
        if (swag == null)
          {
          logger_.severe("Unable to find cached Swag.");
          break;
          }
        }

      logger_.info(Long.toString(swagCount_) + " Swag objects removed.");
      }
    catch (Exception e)
      {
      logger_.severe("Error removing Swags:  " + e.toString());
      }
    }


  /**
   * Run the Cacher.  This method is inherited from Runnable.
   */
  public void run()
    {
    logger_.info("Running...");

    cache();
    retrieve();
    remove();

    logger_.info("Done.");
    }


  /**
   * The command line interface for the Cacher class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 2)
      {
      String spaceURL = args[0];
      long swagCount = Long.parseLong(args[1]);

      if (swagCount > 0)
        {
        Cacher cacher = new Cacher(spaceURL,swagCount);
        cacher.run();
        }
      else
        logger_.severe("object count must be greater than zero.");
      }
    else
      logger_.info("Usage:  java "
                   + Cacher.class.getName()
                   + " <space-url> <object-count>");
    }
}  // end Cacher
