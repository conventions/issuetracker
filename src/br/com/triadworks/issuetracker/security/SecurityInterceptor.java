package br.com.triadworks.issuetracker.security;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.triadworks.issuetracker.qualifier.UserRole;

import com.jsf.conventions.qualifier.SecurityMethod;
import com.jsf.conventions.security.SecurityMethodInterceptor;
import com.jsf.conventions.util.MessagesController;


 
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
