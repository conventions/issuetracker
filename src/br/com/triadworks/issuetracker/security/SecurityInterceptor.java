package br.com.triadworks.issuetracker.security;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.Interceptor;

import org.conventionsframework.qualifier.SecurityMethod;
import org.conventionsframework.security.SecurityMethodInterceptor;

import br.com.triadworks.issuetracker.qualifier.UserRole;


 
@Interceptor
@SecurityMethod
public class SecurityInterceptor extends SecurityMethodInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
     *
     * @param list of roles allowed to execute the method
     * @return true if user has permission to execute the method and false otherwise
     */
	@Inject
	@UserRole
	private String currentRole;

	public boolean checkUserPermissions(String[] rolesAllowed) {
		// user role(s) should be extracted from current logged user
		if (currentRole == null || "".endsWith(currentRole)) {
			return false;
		}
		for (String role : rolesAllowed) {
			if (currentRole.equals(role)) {
				return true;
			}
		}
		return false;
	}

}
