/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads4.lojinha.managedbean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.servlet.http.Part;

/**
 *
 * @author Fernando
 */
public class UploadBean implements Serializable {

  private Part imagem;

  public String salvarImagem() {
    String nomeArquivo = obterNomeArquivo();
    if (nomeArquivo != null && nomeArquivo.trim().length() > 0) {
      salvarArquivo(nomeArquivo);
    }
    return nomeArquivo;

  }

  private String obterNomeArquivo() {
    if (imagem != null) {
      String partHeader = imagem.getHeader("content-disposition");
      for (String content : partHeader.split(";")) {
        if (content.trim().startsWith("filename")) {
          String nomeArquivo
                  = content.substring(content.indexOf('=') + 1)
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

  private void salvarArquivo(String nomeArquivo) {
    String diretorioDestino = "C:" + File.separator + "desenv"
            + File.separator + "imagens" + File.separator;
    File arquivo = new File(diretorioDestino + nomeArquivo);

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

  public Part getImagem() {
    return imagem;
  }

  public void setImagem(Part imagem) {
    this.imagem = imagem;
  }

}
