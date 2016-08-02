package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgardleal on 24/07/16.
 */
public class ReflectionExample implements Runnable {


  public ReflectionExample() {
  }

  @Override
  public void run() {
    List<Cliente> list = new ArrayList<Cliente>();
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente("Teste", 5, 7.7, "Test street");
      Cliente second = new Cliente();
      try {
        Field fields[] = Cliente.class.getDeclaredFields();
        for (Field field : fields) {
          String capitalizedName = StringUtils.capitalize(field.getName());
          Cliente.class.getDeclaredMethod("set" + capitalizedName,
              field.getType()).invoke(second,
              Cliente.class.getDeclaredMethod("get" + capitalizedName).invoke(cliente, new Class<?>[0])
          );
          list.add(second);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
