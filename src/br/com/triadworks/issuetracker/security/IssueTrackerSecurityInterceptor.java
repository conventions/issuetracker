package br.com.triadworks.issuetracker.security;

import com.jsf.conventions.security.SecurityMethodInterceptor;

//@Interceptor
//@SecurityMethod
public class IssueTrackerSecurityInterceptor extends SecurityMethodInterceptor {

//	@Inject
//	@UserRole
//	private String currentRole;
//
//	/**
//	 * this method is responsible for deciding if current user has permition to
//	 * execute a method
//	 * 
//	 * @param rolesAllowed
//	 *            roles passed in the method
//	 * @return true if user has permition, false otherwise
//	 */
//	@Override
//	@SuppressWarnings("unchecked")
//	public boolean checkUserPermissions(String[] rolesAllowed) {
//		// user role(s) should be extracted from current logged user
//		if (currentRole == null || "".endsWith(currentRole)) {
//			return false;
//		}
//		for (String role : rolesAllowed) {
//			if (currentRole.equals(role)) {
//				return true;
//			}
//		}
//		return false;
//	}

}
