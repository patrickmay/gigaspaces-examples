/**
 * Worker takes Request objects from a space and writes the
 * corresponding Response objects.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.stateful.worker;

import org.openspaces.core.GigaSpace;

import org.springframework.transaction.TransactionStatus;

import com.gigaspaces.examples.stateful.shared.Request;
import com.gigaspaces.examples.stateful.shared.Response;

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
