package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 24/07/16.
 */
public class ReflectionExample implements Runnable{


  public ReflectionExample() {
  }

  @Override
  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente();
      try {
        Cliente.class.getDeclaredMethod("setNome", String.class).invoke(cliente, "Teste");
        Cliente.class.getDeclaredMethod("setIdade", int.class).invoke(cliente, 5);
        Cliente.class.getDeclaredMethod("setSaldo", double.class).invoke(cliente, 7.7);
        Cliente.class.getDeclaredMethod("setEndereco", String.class).invoke(cliente, "Rua do teste");
      }catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
