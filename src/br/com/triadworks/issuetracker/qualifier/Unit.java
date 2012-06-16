package br.com.triadworks.issuetracker.qualifier;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Unit {
	@Nonbinding
	String name() default "issueTrackerPU";
}
