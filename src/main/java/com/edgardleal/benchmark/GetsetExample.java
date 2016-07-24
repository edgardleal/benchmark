package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 24/07/16.
 */
public class GetsetExample implements  Runnable{


  public GetsetExample() {
  }

  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente();
      try {
        cliente.setNome("Teste");
        cliente.setIdade(5);
        cliente.setSaldo(7.7);
        cliente.setEndereco("Rua do teste");
      }catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
