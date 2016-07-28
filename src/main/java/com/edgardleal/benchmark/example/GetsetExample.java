package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgardleal on 24/07/16.
 */
public class GetsetExample implements  Runnable{


  public GetsetExample() {
  }

  public void run() {
    List<Cliente> list = new ArrayList<Cliente>();
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente("Teste", 5, 7.7, "Test street");
      Cliente second = new Cliente();
      second.setNome(cliente.getNome());
      second.setIdade(cliente.getIdade());
      second.setSaldo(cliente.getSaldo());
      second.setEndereco(cliente.getEndereco());

      list.add(second);
    }
  }
}
