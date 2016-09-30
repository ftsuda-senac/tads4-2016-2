/*
 * The MIT License
 *
 * Copyright 2016 fernando.tsuda.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.senac.tads4.lojinha.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fernando.tsuda
 */
public class Usuario implements Serializable {
  
  private Long id;
  private String nome;
  private String senha; // OBS: Criar classe separada com informações de autenticação no sistema.
  private Sexo sexo;
  private List<String> interesses;
  private boolean receberOfertas = true;
  private Date dtNascimento;
  private String email;
  private String telefone;
  private Integer cpf;
  private Short cpfDv;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Sexo getSexo() {
    return sexo;
  }

  public void setSexo(Sexo sexo) {
    this.sexo = sexo;
  }

  public List<String> getInteresses() {
    return interesses;
  }

  public void setInteresses(List<String> interesses) {
    this.interesses = interesses;
  }

  public boolean isReceberOfertas() {
    return receberOfertas;
  }

  public void setReceberOfertas(boolean receberOfertas) {
    this.receberOfertas = receberOfertas;
  }

  public Date getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(Date dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Integer getCpf() {
    return cpf;
  }

  public void setCpf(Integer cpf) {
    this.cpf = cpf;
  }

  public Short getCpfDv() {
    return cpfDv;
  }

  public void setCpfDv(Short cpfDv) {
    this.cpfDv = cpfDv;
  }
  
  public static enum Sexo {
    FEMININO, MASCULINO;
  }

  @Override
  public String toString() {
    return "Usuario{" + "id=" + id + ", nome=" + nome + ", senha=" + senha + ", sexo=" + sexo + ", interesses=" + interesses + ", receberOfertas=" + receberOfertas + ", dtNascimento=" + dtNascimento + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf + ", cpfDv=" + cpfDv + '}';
  }
 
  
}
