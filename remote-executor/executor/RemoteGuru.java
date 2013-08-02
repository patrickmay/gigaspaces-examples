/**
 * RemoteGuru is an implementation of the Guru interface to demonstrate
 * remote execution in GigaSpaces.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.executor.remote;

import com.gigaspaces.examples.executor.shared.Guru;

import java.util.Random;

public class RemoteGuru implements Guru
{
  private static final String[] WISDOM
    = { "The question of whether computers can think is like the question of whether submarines can swim. -- E. W. Dijkstra",
        "All models are wrong; some models are useful. -- G. Box",
        "Plan to throw one away; you will anyhow. -- F. Brooks",
        "640K ought to be enough for anybody. -- W. Gates",
        "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live. -- M. Golding",
        "Beware of bugs in the above code; I have only proved it correct, not tried it. -- D. Knuth",
        "A language that doesn't affect the way you think about programming is not worth knowing. -- A. Perlis" };

  private Random generator_ = new Random();

  /**
   * The default constructor for the RemoteGuru class.
   */
  public RemoteGuru()
    {
    }


  /**
   * The advise() method inherited from the Guru interface.
   */
  public String advise()
    {
    return WISDOM[generator_.nextInt(WISDOM.length)];
    }
}
