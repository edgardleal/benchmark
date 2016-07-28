package com.edgardleal.benchmark;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by edgardleal on 27/07/16.
 */
public class MethodRunner implements Runnable {

  private final Object object;
  private Method method;

  public MethodRunner(Method method, Object object) {
    this.method = method;
    this.object = object;
  }

  @Override
  public void run() {
    for (int i = 0; i < 2; i++)
    try {
      method.invoke(this.object , new Object[0]);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }
}
