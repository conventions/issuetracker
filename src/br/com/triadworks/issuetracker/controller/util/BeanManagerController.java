/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.triadworks.issuetracker.controller.util;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.apache.myfaces.extensions.cdi.core.api.provider.BeanManagerProvider;

/**
 *
 * @author rpestano
 */
/**
 * CLasse responsável por buscar beans do CDI fora do contexto do CDI(onde @Inject não funciona) eg:filter, webservice etc...
 * @author rmpestano
 *
 */
public class BeanManagerController {

 

	
    @SuppressWarnings("rawtypes")
	public static Object getBeanByName(String name) {
    	try{
	        BeanManager bm = BeanManagerProvider.getInstance().getBeanManager();
	        Set<Bean<?>> beans = bm.getBeans(name);
	        if(beans != null && !beans.isEmpty()){
	        	Bean bean = beans.iterator().next();
	        	CreationalContext ctx = bm.createCreationalContext(bean);  
	        	Object o = bm.getReference(bean, bean.getClass(), ctx);  
	        	return o;
	        }
    	}catch (Exception e) {
			// TODO: handle exception
		}  
        return null;
    }
    
    @SuppressWarnings({"unchecked","rawtypes"})
	public static Object getBeanByTypeAndName(Class type,String name) {
    	return BeanManagerProvider.getInstance().getContextualReference(type, name);
    }
    	
}
