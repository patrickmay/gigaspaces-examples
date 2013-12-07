/**
 * SKUFeeder writes SKU objects to a space.  The number of SKU objects
 * to write is specified on the command line.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.rest;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.logging.Logger;

public class SKUFeeder
{
  private static Logger logger_ = Logger.getLogger(SKUFeeder.class.getName());

  private String spaceURL_ = null;
  private int skuCount_ = 0;

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
   * The full constructor for the SKUFeeder class.
   *
   * @param spaceURL The URL of the GigaSpace to feed.
   * @param skuCount The number of SKUs to feed.
   */
  public SKUFeeder(String spaceURL,int skuCount)
    {
    spaceURL_ = spaceURL;
    skuCount_ = skuCount;
    }


  /**
   * Feed random SKUs into the space.
   */
  private void feed()
    {
    logger_.info("feed():  feeding...");

    for (int i = 0;i < skuCount_;i++)
      space().write(SKU.randomSKU());

    logger_.info("feed(): fed.");
    }


  /**
   * The command line interface for the SKUFeeder class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 2)
      {
      String spaceURL = args[0];
      int skuCount = Integer.parseInt(args[1]);

      SKUFeeder feeder = new SKUFeeder(spaceURL,skuCount);
      feeder.feed();
      }
    else
      logger_.info("Usage:  java "
                   + SKUFeeder.class.getName()
                   + " <space-url> <sku-count>");
    }
}  // end SKUFeeder
