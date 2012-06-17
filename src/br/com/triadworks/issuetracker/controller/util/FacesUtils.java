package br.com.triadworks.issuetracker.controller.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


public class FacesUtils implements Serializable{
	
	private FacesContext facesContext;
	
	@Inject
	public FacesUtils(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public void adicionaMensagemDeErro(String mensagem) {
		FacesMessage facesMessage 
			= new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}

	public void adicionaMensagemDeInformacao(String mensagem) {
		FacesMessage facesMessage 
			= new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		facesContext.addMessage(null, facesMessage);
	}
	
	 
	
}
