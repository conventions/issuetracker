package br.com.triadworks.issuetracker.controller;

import java.io.Serializable;
import java.net.URLDecoder;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.conventionsframework.filter.ConventionsFilter;
import org.conventionsframework.qualifier.Config;
import org.conventionsframework.security.DefaultSecurityContext;
import org.conventionsframework.util.MessagesController;
import org.conventionsframework.util.ResourceBundle;

import br.com.triadworks.issuetracker.model.Usuario;
import br.com.triadworks.issuetracker.qualifier.UserRole;
import br.com.triadworks.issuetracker.qualifier.UsuarioLogado;

@SessionScoped
@Specializes
@Named("usuarioWeb")
public class UsuarioWeb  extends DefaultSecurityContext implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	@Inject
    @Config
    transient Instance<ExternalContext> externalContext;

    @Inject
    @Config
    transient Instance<FacesContext> facesContext;
    
    @Inject
    ResourceBundle bundle;
	
	@Produces @UserRole
	private String userRole;
	
    private String pageRecovery;


	public void loga(Usuario usuario) {
		if(usuario.getLogin().equalsIgnoreCase("admin")){
			userRole = "godlike";
		}
		else{
			userRole = "";
		}
		this.usuario = usuario;
		
		 if (facesContext.get() != null) {
             restorePageOnLogon();
             MessagesController.addInfo(bundle.getString("logon.info.successful"));
         }

	}
	
	private void restorePageOnLogon() {
        ExternalContext ec = externalContext.get();
        if (getPageRecovery() != null) {
            try {
                ec.redirect(ec.getRequestContextPath() + URLDecoder.decode(pageRecovery, "UTF-8"));
                MessagesController.addWarn(bundle.getString("logon.info.successful"));
                facesContext.get().responseComplete();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                setPageRecovery(null);
            }

        }
	}

	public void logout() {
		this.usuario = null;
	}
	
	  public void init() {
	        if (!facesContext.get().isPostback()) {
	            if (getPageRecovery() != null) {
	                MessagesController.addWarn(bundle.getString("expire.message"));
	            }
	        }
	    }
	
	/**
	 * used by convention filter
	 * @see ConventionsFilter
	 */
	@Override
	public Boolean loggedIn() {
		return isLogado();
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}

	@Produces
	@UsuarioLogado
	@Named("usuarioLogado")
	public Usuario getUsuario() {
		return usuario;
	}

	public String getPageRecovery() {
		return pageRecovery;
	}

	public void setPageRecovery(String pageRecovery) {
		this.pageRecovery = pageRecovery;
	}
	
	
	
}
