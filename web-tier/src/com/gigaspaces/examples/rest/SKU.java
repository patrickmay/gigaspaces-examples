/**
 * SKU is a very simplified example of a Stock Keeping Unit class.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.rest;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceIndex;

import java.util.Random;


@SpaceClass
public class SKU
{
  private String id;
  private String color;
  private String size;

  /**
   * The default constructor for the SKU class.
   */
  public SKU()
    {
    }


  /**
   * The full constructor for the SKU class.
   */
  public SKU(String id,String color,String size)
    {
    this.id = id;
    this.color = color;
    this.size = size;
    }


  // Accessors / mutators required by GigaSpaces
  @SpaceId(autoGenerate = false)
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  @SpaceIndex
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }

  @SpaceIndex
  public String getSize() { return size; }
  public void setSize(String size) { this.size = size; }


  /**
   * Create a random SKU object.
   */
  private static final String[] COLORS
    = { "red", "blue", "green", "yellow", "white", "black", "orange", "purple" };
  private static final String[] SIZES = { "XS", "S", "M", "L", "XL", "XXL" };
  private static Random generator = new Random(System.currentTimeMillis());
  public static SKU randomSKU()
    {
    return new SKU(Long.toString(Math.abs(generator.nextLong())),
                   COLORS[generator.nextInt(COLORS.length)],
                   SIZES[generator.nextInt(SIZES.length)]);
    }
}
