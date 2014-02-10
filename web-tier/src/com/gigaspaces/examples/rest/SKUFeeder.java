/**
 * SKUFeeder writes SKU objects to a space.
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
  private static final String[] SIZES = { "XS", "S", "M", "L", "XL", "XXL" };
  private static final String[] COLORS = { "Red",
                                           "Blue",
                                           "Green",
                                           "Yellow",
                                           "Pink",
                                           "Orange",
                                           "Mauve",
                                           "Navy",
                                           "Purple",
                                           "White",
                                           "Black" };
  private static final String[] DESCRIPTIONS = { "T-Shirt",
                                                 "Shorts",
                                                 "Hoodie",
                                                 "Gown",
                                                 "Skirt",
                                                 "Dress",
                                                 "Trousers",
                                                 "Jacket",
                                                 "Socks",
                                                 "Shoes",
                                                 "Hat",
                                                 "Coat",
                                                 "Gloves" };

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
   */
  public SKUFeeder(String spaceURL)
    {
    spaceURL_ = spaceURL;
    }


  /**
   * Feed all combinations of items into the space.
   */
  private void feed()
    {
    logger_.info("feed():  feeding...");

    long id = 0;

    for (String color : COLORS)
      for (String size : SIZES)
        for (String description : DESCRIPTIONS)
          space().write(new SKU("SKU" + id++,color,size,description));

    logger_.info("feed(): fed.");
    }


  /**
   * The command line interface for the SKUFeeder class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 1)
      {
      String spaceURL = args[0];

      SKUFeeder feeder = new SKUFeeder(spaceURL);
      feeder.feed();
      }
    else
      logger_.info("Usage:  java "
                   + SKUFeeder.class.getName()
                   + " <space-url>");
    }
}  // end SKUFeeder
