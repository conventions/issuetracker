package br.com.triadworks.issuetracker.controller.util;


public class FacesContextFactory {

	/**
	 * Obtem instancia atual do <code>facesContext</code> e permite que o mesmo
	 * seja injetado em beans com escopo maior que request.
	 */
	
//CODI já produz o facesContext	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
//	public FacesContext getInstance() {
//		return FacesContext.getCurrentInstance();
//	}
	
}
