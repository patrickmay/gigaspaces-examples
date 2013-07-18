/**
 * Worker takes Request objects from a space and writes the
 * corresponding Response objects.
 *
 * @author Patrick May (patrick.may@gigaspaces.com)
 * @author &copy; 2013 GigaSpaces Technologies Inc.  All rights reserved.
 * @version 1
 */

package com.gigaspaces.examples.stateless.worker;

import org.openspaces.core.GigaSpace;

import org.springframework.transaction.TransactionStatus;

import com.gigaspaces.examples.stateless.shared.Request;
import com.gigaspaces.examples.stateless.shared.Response;

import java.util.logging.Logger;

public class Worker
{
  private static Logger logger_ = Logger.getLogger(Worker.class.getName());

  /**
   * The default constructor for the Worker class.
   */
  public Worker()
    {
    }


  /**
   * Turn requests into orders.
   */
  public Response processRequest(Request request,
                                 GigaSpace space,
                                 TransactionStatus transactionStatus)
    {
    return new Response(request);
    }
}  // end Worker
