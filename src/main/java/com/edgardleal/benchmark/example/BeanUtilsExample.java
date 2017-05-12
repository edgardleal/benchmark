package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgardleal on 24/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class BeanUtilsExample {

  /**
   * <p>Constructor for BeanUtilsExample.</p>
   */
  public BeanUtilsExample() {

  }

  /**
   * <p>timeGetAndSet.</p>
   */
  public void timeGetAndSet() {
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

  /**
   * <p>timeBeanutils.</p>
   */
  public void timeBeanutils() {
    List<Cliente> list = new ArrayList<Cliente>();
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      Cliente cliente = new Cliente("Teste", 5, 7.7, "Test street");
      Cliente second = new Cliente();
      try {
        second = (Cliente) BeanUtils.cloneBean(cliente);
        list.add(second);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * <p>timeReflection.</p>
   */
  public void timeReflection() {
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
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
