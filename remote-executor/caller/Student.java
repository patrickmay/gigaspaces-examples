/**
 * Student calls a remote Guru to demonstrate remote execution in GigaSpaces.
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.executor.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.remoting.ExecutorRemotingProxyConfigurer;

import com.gigaspaces.examples.executor.shared.Guru;

public class Student
{
  private String spaceURL_ = null;
  private GigaSpace gigaSpace_ = null;
  private Guru guru_ = null;

  /**
   * Return a proxy to the GigaSpace, creating it if necessary.
   */
  private GigaSpace gigaSpace()
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
   * Return a proxy to the remote Guru, creating it if necessary.
   */
  private Guru guru()
    {
    if (guru_ == null)
      guru_ = new ExecutorRemotingProxyConfigurer<Guru>(gigaSpace(),
                                                        Guru.class).proxy();

    return guru_;
    }


  /**
   * The full constructor for the Student class.
   */
  public Student(String spaceURL)
    {
    spaceURL_ = spaceURL;
    }


  /**
   * Get advice from the guru.
   */
  public String seekWisdom()
    {
    return guru().advise();
    }


  /**
   * The command line interface for the Student class.
   *
   * @param args The command line arguments passed in.
   */
  public static void main(String args[])
    {
    if (args.length == 1)
      {
      Student student = new Student(args[0]);

      System.out.println(student.seekWisdom());
      }
    else
      System.out.println("Usage:  java "
                         + Student.class.getName()
                         + " <space-url>");
    }
}
