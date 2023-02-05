package com.jahongir.mini_transaction.mappers.config;


import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author jahongir
 * @created 05/02/23 - 23:59
 * @project Mini_transaction/IntelliJ IDEA
 */
@Qualifier
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface EncodedMapping {
}
