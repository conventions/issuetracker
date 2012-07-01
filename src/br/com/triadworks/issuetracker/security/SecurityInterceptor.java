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


/**
 *  o @AroundInvike deveria estar definido apenas na superclasse mas por causa de um bug no Openwebbeans
 *  foi definido no projeto tbm.
 * o certo seria usar a classe IssueTrackerSecurityInterceptor e apenas definir a lógica do 
 * método checkUserPermissions. 
 *
 */
@Interceptor
@SecurityMethod
public class SecurityInterceptor extends SecurityMethodInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@AroundInvoke
    public Object checkPermission(InvocationContext ic) throws Exception {
       return super.checkPermission(ic);
    }

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
