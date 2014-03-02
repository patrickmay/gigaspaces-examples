/**
 * SKU is a very simplified example of a Stock Keeping Unit class.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2014 Patrick May
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
  private String description;

  /**
   * The default constructor for the SKU class.
   */
  public SKU()
    {
    }


  /**
   * The full constructor for the SKU class.
   */
  public SKU(String id,String color,String size,String description)
    {
    this.id = id;
    this.color = color;
    this.size = size;
    this.description = description;
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


  @SpaceIndex
  public String getDescription() { return description; }
  public void setDescription(String description)
    { this.description = description; }
}
