package com.edgardleal.benchmark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by edgardleal on 27/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class MethodRunner implements Runnable {

  private final Object object;
  private Method method;

  /**
   * <p>Constructor for MethodRunner.</p>
   *
   * @param method a {@link java.lang.reflect.Method} object.
   * @param object a {@link java.lang.Object} object.
   */
  public MethodRunner(Method method, Object object) {
    this.method = method;
    this.object = object;
  }

  /**
   * <p>Started by Thread.</p>
   */
  @Override
  public void run() {
    for (int i = 0; i < 2; i++) {
      try {
        method.invoke(this.object, new Object[0]);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
