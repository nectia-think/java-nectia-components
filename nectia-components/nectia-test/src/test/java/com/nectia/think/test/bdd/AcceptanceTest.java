/**
 * Sistema de Control Casinos
 * (c) 2011-2015 Productos Fernandes
 * 
 * Desarrollo v2.0 Nectia Software
 * Equipo: arquitectura@nectia.com
 */
package com.nectia.think.test.bdd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author <a href="mailto:wtorres@nectia.com">Williams Torres</a>
 * @version 1.0
 */
@ContextConfiguration(classes = AcceptanceTestsConfiguration.class)
@ActiveProfiles("tests")
@DirtiesContext
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AcceptanceTest {

}
