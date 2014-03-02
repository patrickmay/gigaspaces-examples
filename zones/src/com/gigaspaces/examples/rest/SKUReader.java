/**
 * SKUReader reads SKU objects from a space.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2014 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.rest;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.util.logging.Logger;

public class SKUReader
{
  private static Logger logger_ = Logger.getLogger(SKUReader.class.getName());

  private String spaceURL_ = null;
  private GigaSpace gigaSpace_ = null;

  /**
   * Return a proxy to the GigaSpace, creating it if necessary.
   */
  private GigaSpace space()
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
   * The full constructor for the SKUReader class.
   *
   * @param spaceURL The URL of the GigaSpace to feed.
   */
  public SKUReader(String spaceURL)
    {
    spaceURL_ = spaceURL;
    }


  /**
   * Read all SKUs from the space.
   */
  public void read()
    {
    SKU template = new SKU();
    SKU skus[] = space().readMultiple(template);

    logger_.info("read():  read " + skus.length + " SKUs.");
    }


  /**
   * The command line interface for the SKUReader class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 1)
      {
      String spaceURL = args[0];

      SKUReader reader = new SKUReader(spaceURL);
      reader.read();
      }
    else
      logger_.info("Usage:  java "
                   + SKUReader.class.getName()
                   + " <space-url>");
    }
}  // end SKUReader
