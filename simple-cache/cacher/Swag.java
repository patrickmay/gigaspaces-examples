/**
 * Swag is an object to put in a cache.
 *
 * @author Patrick May (patrick.may@gigaspaces.com)
 * @author &copy; 2013 GigaSpaces Technologies Inc.  All rights reserved.
 * @version 1
 */

package com.gigaspaces.examples.cache.cacher;

public class Swag
{
  private Long id = null;

  /**
   * The default constructor for the Swag class.
   */
  public Swag()
    {
    }


  /**
   * The full constructor for the Swag class.
   *
   * @param id The ID of the request.
   */
  public Swag(long id)
    {
    this.id = new Long(id);
    }


  // Accessor / mutator required by GigaSpaces
  public Long getId() { return id; }
  public void setId(Long id) { this.id = new Long(id); }
}  // end Swag
