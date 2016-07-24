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
      Cliente cliente = new Cliente();
      BeanUtils.createCache();
      try {
        BeanUtils.setProperty(cliente, "nome", "Teste");
        BeanUtils.setProperty(cliente, "idade", 5);
        BeanUtils.setProperty(cliente, "saldo", 7.7);
        BeanUtils.setProperty(cliente, "endereco", "Rua do teste");
      }catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
