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
 * Copiado todo código do Conventions para o issuetracker por causa de um bug no Openwebbeans
 * o certo seria usar a classe IssueTrackerSecurityInterceptor e apenas definir a lógica do 
 * método checkUserPermissions. 
 *
 */


@Interceptor
@SecurityMethod
public class SecurityInterceptor  implements Serializable{
	
	@AroundInvoke
    public Object checkPermission(InvocationContext ic) throws Exception {
        try {
            String[] rolesAllowed = this.extractMethodRoles(ic.getMethod());
            if (rolesAllowed != null && rolesAllowed.length > 0) {
                if (!this.checkUserPermissions(rolesAllowed)) {
                    MessagesController.addFatal(ic.getMethod().getAnnotation(SecurityMethod.class).message());
                    return null;
                }
            }
            return ic.proceed();
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param list of roles allowed to execute the method
     * @return true if user has permission to execute the method and false otherwise
     */
	@Inject
	@UserRole
	private String currentRole;

	/**
	 * this method is responsible for deciding if current user has permition to
	 * execute a method
	 * 
	 * @param rolesAllowed
	 *            roles passed in the method
	 * @return true if user has permition, false otherwise
	 */
 
	@SuppressWarnings("unchecked")
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

    private String[] extractMethodRoles(Method m) {
        if (m.isAnnotationPresent(SecurityMethod.class)) {
            SecurityMethod securityMethod = m.getAnnotation(SecurityMethod.class);
            return securityMethod.rolesAllowed();
        }
        return null;
    }

}
