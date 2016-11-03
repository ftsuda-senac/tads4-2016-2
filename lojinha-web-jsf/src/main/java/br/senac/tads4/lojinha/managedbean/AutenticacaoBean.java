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
package br.senac.tads4.lojinha.managedbean;

import br.senac.tads4.lojinha.entidade.Usuario;
import br.senac.tads4.lojinha.entidade.UsuarioSistema;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fernando.tsuda
 */
@Named
@SessionScoped
public class AutenticacaoBean implements Serializable {

  private UsuarioSistema usuario = null;

  private static final UsuarioSistema REFERENCIA
	  = new UsuarioSistema("fulano", "abcd1234",
		  new String[]{"COMUM"});

  private String nome;

  private String senha;

  public AutenticacaoBean() {
  }

  public String autenticar() {
    // Verifica se usuario/senha são validos
    // Se for valido, apresenta lista de produtos e dados do usuario

    boolean autenticado
	    = REFERENCIA.autenticar(this.nome, this.senha);

    if (autenticado) {
      this.usuario = REFERENCIA;
      return "/admin/pagina-admin.xhtml?faces-redirect=true";
    }
    // Em caso de erro
    FacesMessage msg
	    = new FacesMessage("Erro de login", "ERROR_MSG");
    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    FacesContext.getCurrentInstance().addMessage(null, msg);
    return "/login.xhtml";
  }

  public UsuarioSistema getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioSistema usuario) {
    this.usuario = usuario;
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

  public boolean isAutenticado() {
    return usuario != null;
  }

  public String sair() {
    usuario = null;
    HttpServletRequest req = (HttpServletRequest) 
	    FacesContext.getCurrentInstance()
		    .getExternalContext().getRequest();
    req.getSession().invalidate();
    return "/lista.xhtml?faces-redirect=true";
  }

}
