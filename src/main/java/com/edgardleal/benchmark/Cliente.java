package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 24/07/16.
 */
public class Cliente {
  private String nome;
  private int idade;
  private double saldo;
  private String endereco;

  public Cliente(String nome, int idade, double saldo, String endereco) {
    this.nome = nome;
    this.idade = idade;
    this.saldo = saldo;
    this.endereco = endereco;
  }

  public Cliente() {

  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
}
