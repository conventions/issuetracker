
package com.injection.test;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, FIELD, METHOD, PARAMETER})
public @interface ClassParam {
    
        @Nonbinding
	Class value();
        
}
