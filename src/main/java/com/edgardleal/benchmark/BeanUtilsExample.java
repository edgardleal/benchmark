package com.edgardleal.benchmark;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by edgardleal on 24/07/16.
 */
public class BeanUtilsExample implements Runnable{

  public BeanUtilsExample() {

  }

  @Override
  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente("Teste", 5, 7.7, "Test street");
      Cliente second  = new Cliente();
      try {
        second = (Cliente) BeanUtils.cloneBean(cliente);
      }catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
