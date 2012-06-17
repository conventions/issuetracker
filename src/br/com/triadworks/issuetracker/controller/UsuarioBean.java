package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIForm;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import com.jsf.conventions.bean.BaseMBean;
import com.jsf.conventions.qualifier.Service;

import br.com.triadworks.issuetracker.controller.util.FacesUtils;
import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.service.UsuarioService;

@Named
@ViewAccessScoped
@Service(name="usuarioService")
public class UsuarioBean extends BaseMBean<Usuario> implements Serializable {

	private Usuario usuario = new Usuario();
	private String confirmacaoDeSenha;


	@Inject
	private FacesUtils facesUtils;

	@Inject
	public void setService(UsuarioService usuarioService) {
		super.setBaseService(usuarioService);
		super.setFindState();
	}

	public UsuarioService getUsuarioService() {
		return (UsuarioService) super.getBaseService();
	}

	public void lista() {
		super.setFindState();
	}

	public void preparaParaAdicionar() {
		this.usuario = new Usuario();
		super.setInsertState();
	}

	public void adiciona() {

		boolean senhaInvalida = !confirmacaoDeSenha.equals(usuario.getSenha());
		if (senhaInvalida) {
			facesUtils
					.adicionaMensagemDeErro("Senha e confirmação de senha não conferem.");
			return;
		}

		getUsuarioService().salva(usuario);
		facesUtils
				.adicionaMensagemDeInformacao("Usuário adicionado com sucesso!");
		lista();
	}

	public void remove() {
		getUsuarioService().remove(usuario);
		facesUtils
				.adicionaMensagemDeInformacao("Usuário removido com sucesso!");
		lista();
	}

	public void preparaParaAlterar(Usuario usuario) {
		this.usuario = getUsuarioService().carrega(usuario.getId()); // evita
																		// LazyInitializationException
		setUpdateState();
	}

	public void altera() {

		boolean senhaInvalida = !confirmacaoDeSenha.equals(usuario.getSenha());
		if (senhaInvalida) {
			facesUtils
					.adicionaMensagemDeErro("Senha e confirmação de senha não conferem.");
			return;
		}

		getUsuarioService().atualiza(usuario);
		facesUtils
				.adicionaMensagemDeInformacao("Usuário atualizado com sucesso!");
		lista();
	}

	public void voltar() {
		this.usuario = new Usuario();
		lista();
	}

	public boolean isAdicionando() {
		return super.isInsertState();
	}
	public boolean isEditando() {
		return super.isUpdateState();
	}
	public boolean isPesquisando() {
		return super.isFindState();
	}

	 

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}

	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}

	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}


}
