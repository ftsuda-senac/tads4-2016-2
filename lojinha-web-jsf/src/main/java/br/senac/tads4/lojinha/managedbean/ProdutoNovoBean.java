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

import br.senac.tads4.lojinha.entidade.Categoria;
import br.senac.tads4.lojinha.entidade.ImagemProduto;
import br.senac.tads4.lojinha.entidade.Produto;
import br.senac.tads4.lojinha.service.CategoriaService;
import br.senac.tads4.lojinha.service.ProdutoService;
import br.senac.tads4.lojinha.service.jpaimpl.CategoriaServiceJPAImpl;
import br.senac.tads4.lojinha.service.jpaimpl.ProdutoServiceJPAImpl;
import br.senac.tads4.lojinha.util.Mensagem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author fernando.tsuda
 */
@Named
@ViewScoped
public class ProdutoNovoBean implements Serializable {

  private String nome;
  private String descricao;
  private List<Integer> idsCategorias;
  private BigDecimal preco;
  private Part imagem;

  public ProdutoNovoBean() {
  }

  public String salvar() {
    Produto p = new Produto();
    CategoriaService cServ = new CategoriaServiceJPAImpl();

    p.setNome(nome);
    p.setDescricao(descricao);
    List<Categoria> listaCategorias = new ArrayList<Categoria>();
    for (int idCat : idsCategorias) {
      Categoria cat = cServ.obter(idCat);
      listaCategorias.add(cat);
      cat.setProdutos(Arrays.asList(p));
    }
    p.setCategorias(listaCategorias);
    p.setPreco(preco);
    p.setDtCadastro(new Date());

    ImagemProduto img = new ImagemProduto();
    String nomeArquivo = obterNomeArquivo();
    if (nomeArquivo != null && nomeArquivo.trim().length() > 0) {
      salvarImagem(nomeArquivo);
      img.setNomeArquivo(nomeArquivo);
      img.setProduto(p);
      p.setImagens(Arrays.asList(img));
    }

    ProdutoService produtoService = new ProdutoServiceJPAImpl();
    produtoService.incluir(p);

    Flash flash = FacesContext.getCurrentInstance()
            .getExternalContext().getFlash();
    flash.put("mensagem", new Mensagem("Produto '" 
            + p.getNome() 
            + "' cadastrado com sucesso", "success"));
    return "/lista.xhtml?faces-redirect=true";
  }

  private String obterNomeArquivo() {
    if (imagem != null) {
      String partHeader = imagem.getHeader("content-disposition");
      for (String content : partHeader.split(";")) {
        if (content.trim().startsWith("filename")) {
          String nomeArquivo = 
                  content.substring(content.indexOf('=') + 1)
                  .trim().replace("\"", "");
          int lastFilePartIndex = nomeArquivo.lastIndexOf("\\");
          if (lastFilePartIndex > 0) {
            return nomeArquivo.substring(lastFilePartIndex, 
                    nomeArquivo.length());
          }
          return nomeArquivo;
        }
      }
    }
    return null;
  }

  private void salvarImagem(String nomeImagem) {
    String diretorioDestino = "C:" + File.separator + "desenv"
            + File.separator + "imagens" + File.separator;
    File arquivo = new File(diretorioDestino + nomeImagem);

    InputStream inputStream = null;
    OutputStream outputStream = null;

    try {
      inputStream = getImagem().getInputStream();
      outputStream = new FileOutputStream(arquivo);

      int read = 0;
      final byte[] bytes = new byte[1024];
      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }
    } catch (IOException e) {
      //TODO: LOGAR ERRO
    } finally {
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
          //TODO: LOGAR ERRO
        }
      }
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          //TODO: LOGAR ERRO
        }
      }
    }
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public List<Integer> getIdsCategorias() {
    return idsCategorias;
  }

  public void setIdsCategorias(List<Integer> idsCategorias) {
    this.idsCategorias = idsCategorias;
  }

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public Part getImagem() {
    return imagem;
  }

  public void setImagem(Part imagem) {
    this.imagem = imagem;
  }
}
