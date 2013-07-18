/**
 * Request is a request for work to be done.
 *
 * @author Patrick May (patrick.may@gigaspaces.com)
 * @author &copy; 2013 GigaSpaces Technologies Inc.  All rights reserved.
 * @version 1
 */

package com.gigaspaces.examples.stateful.shared;

public class Request
{
  private Long id = null;

  /**
   * The default constructor for the Request class.
   */
  public Request()
    {
    }


  /**
   * The full constructor for the Request class.
   *
   * @param id The ID of the request.
   */
  public Request(long id)
    {
    this.id = new Long(id);
    }


  // Accessor / mutator required by GigaSpaces
  public Long getId() { return id; }
  public void setId(Long id) { this.id = new Long(id); }
}  // end Request
