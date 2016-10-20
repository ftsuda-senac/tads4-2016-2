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
package br.senac.tads4.lojinha.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fernando.tsuda
 */
public class AutorizacaoFilter implements PhaseListener {

  @Override
  public void afterPhase(PhaseEvent event) {
    FacesContext fc = FacesContext.getCurrentInstance();
    
    String paginaAtual = fc.getViewRoot().getViewId();
    
    HttpServletRequest req
	    = (HttpServletRequest) fc.getExternalContext()
		    .getRequest();
    System.out.println("Pagina atual: " + paginaAtual
	    + " ip:" + req.getRemoteAddr());    
    
//    if (paginaAtual == null || !paginaAtual.contains("login.xhtml")) {
//      NavigationHandler nh = fc
//	    .getApplication().getNavigationHandler();
//      nh.handleNavigation(fc, null, "/login.xhtml?faces-redirect=true");
//    }
  }

  @Override
  public void beforePhase(PhaseEvent event) {

  }

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }

}
