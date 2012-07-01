package br.com.triadworks.issuetracker.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface UserRole {
	@Nonbinding
	String name() default "issueTrackerPU";
}
