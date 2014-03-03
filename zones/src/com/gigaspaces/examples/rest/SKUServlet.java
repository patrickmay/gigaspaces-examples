/**
 * SKUServlet implements a minimal set of REST APIs.
 * <br>
 * The APIs supported are:
 * /v1/items-by-color/{color} A list of items with the specified color
 * /v1/items-by-size/{size}   A list of items with the specified size
 * /v1/items/{color}/{size}    A list of items with the specified color and size
 *
 * @author Patrick May (patrick.may@mac.com)
 * @author &copy; 2013 Patrick May
 * @version 1
 */

package com.gigaspaces.examples.rest;

import java.util.logging.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openspaces.core.GigaSpace;

import com.google.gson.Gson;

public final class SKUServlet extends HttpServlet
{
  private static Logger logger_ = Logger.getLogger(SKUServlet.class.getName());

  private GigaSpace space_ = null;

  /**
   * Return a proxy to a distributed space.
   */
  private GigaSpace space()
    {
    if (space_ == null)
      space_ = (GigaSpace)getServletContext().getAttribute("remoteSpace");

    return space_;
    }


  /**
   * Find all SKUs matching the template and return them in JSON format.
   */
  private String skus(SKU template)
    {
    SKU skus[] = space().readMultiple(template);

    String json = "{}";
    if ((skus != null) && skus.length > 0)
      json = new Gson().toJson(skus);

    return json;
    }


  /**
   * Find all SKUs for items with a particular color and return them in
   * JSON format.
   */
  private String skusByColor(String color)
    {
    SKU template = new SKU();
    template.setColor(color);

    return skus(template);
    }


  /**
   * Find all SKUs for items with a particular size and return them in
   * JSON format.
   */
  private String skusBySize(String size)
    {
    SKU template = new SKU();
    template.setSize(size);

    return skus(template);
    }


  /**
   * Find all SKUs for items with a particular color and size and return
   * them in JSON format.
   */
  private String skus(String color,String size)
    {
    SKU template = new SKU();
    template.setColor(color);
    template.setSize(size);

    return skus(template);
    }


  /**
   * Respond to a GET request for the content produced by this servlet.
   *
   * The supported APIs are:
   * /v1/items-by-color/{color}
   * /v1/items-by-size/{size}
   * /v1/items/{color}/{size}
   */
  public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws IOException, ServletException
    {
    String[] requestElements = request.getRequestURI().split("/");
    String json = new String("{ length : " + requestElements.length + " }");

    logger_.info("SKUServlet.doGet:  " + request.getRequestURI());

    if ((requestElements.length == 5)
        && "items-by-color".equals(requestElements[3]))
      json = skusByColor(requestElements[4]);
    else if ((requestElements.length == 5)
        && "items-by-size".equals(requestElements[3]))
      json = skusBySize(requestElements[4]);
    else if ((requestElements.length == 6)
             && "items".equals(requestElements[3]))
      json = skus(requestElements[4],requestElements[5]);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter writer = response.getWriter();
    writer.write(json);
    writer.flush();
    }
}
