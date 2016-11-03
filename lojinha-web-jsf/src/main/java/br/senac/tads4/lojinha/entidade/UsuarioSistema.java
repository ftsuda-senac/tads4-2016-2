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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Fernando
 */
public class UsuarioSistema implements Serializable {

  private String nome;

  private char[] hashSenha;

  private String[] papeis; //ROLES

  public UsuarioSistema() {

  }

  public UsuarioSistema(String nome, String senha, String[] papeis) {
    this.nome = nome;
    try {
      this.hashSenha = gerarHashSenhaPBKDF2(senha);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      Logger.getLogger(UsuarioSistema.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.papeis = papeis;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public char[] getHashSenha() {
    return hashSenha;
  }

  public String[] getPapeis() {
    return papeis;
  }

  public void setPapeis(String[] papeis) {
    this.papeis = papeis;
  }

  /**
   * Geração do hash da senha usando algoritmo MD5 (mais comum). Não usado neste
   * exemplo
   *
   * @param senha
   * @return
   */
  private char[] gerarHashSenhaMD5(String senha) {
    try {
      // SALT (EM SITUACOES REAIS, DEVEM SER DIFERENTES PARA CADA USUARIO)
      String salt = "abcdefghij";

      MessageDigest md = MessageDigest.getInstance("MD5");
      md.reset();
      byte[] digested = md.digest((salt + senha).getBytes());
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < digested.length; i++) {
        sb.append(Integer.toHexString(0xff & digested[i]));
      }
      return sb.toString().toCharArray();
    } catch (NoSuchAlgorithmException ex) {
      //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * Geração do hash da senha usando algoritmo PBKDF2WithHmacSHA1 (mais seguro)
   * Exemplo obtido em
   * http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
   *
   * Depois que o hash é gerado, não é possível recuperar a senha original.
   *
   * @param senha
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  public static char[] gerarHashSenhaPBKDF2(String senha) throws NoSuchAlgorithmException, InvalidKeySpecException {
    // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
    // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
    String algorithm = "PBKDF2WithHmacSHA1";
    // SHA-1 generates 160 bit hashes, so that's what makes sense here
    int derivedKeyLength = 160;
    // Pick an iteration count that works for you. The NIST recommends at
    // least 1,000 iterations:
    // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
    // iOS 4.x reportedly uses 10,000:
    // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
    int iterations = 2000;

    // SALT (EM SITUACOES REAIS, DEVEM SER DIFERENTES PARA CADA USUARIO)
    // Normalmente, deve ser alguma informação que, após cadastrado, não pode mais ser alterado.
    String salt = "abcdefghij";

    KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt.getBytes(), iterations, derivedKeyLength);
    SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

    byte[] code = f.generateSecret(spec).getEncoded();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < code.length; i++) {
      sb.append(Integer.toHexString(0xff & code[i]));
    }
    System.out.println(sb.toString());
    return sb.toString().toCharArray();
  }

  public boolean autenticar(String nome, String senha) {
    if (this.nome != null) {
      try {
        return this.nome.equals(nome) && Arrays.equals(this.hashSenha, gerarHashSenhaPBKDF2(senha));
      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        Logger.getLogger(UsuarioSistema.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return false;
  }

  /**
   * Verifica se usuário possui autorização de acesso baseado nos papeis que
   * possui.
   *
   * @param papelNecessario
   * @return
   */
  public boolean autorizado(String papelNecessario) {
    List<String> papeisUsuario = Arrays.asList(this.papeis);
    return papeisUsuario.contains(papelNecessario);
  }
}
