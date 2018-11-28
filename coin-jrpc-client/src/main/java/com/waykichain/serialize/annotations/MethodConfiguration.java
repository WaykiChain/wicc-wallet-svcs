package com.waykichain.serialize.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at 8/14/16, 10:22.
 *
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodConfiguration {

    /**
     * (Optional) Id.
     * @return
     */
    public String id();

    /**
     * (Optional) Method name, is required only for MethodType.OUT
     * @return
     */
    public String methodName();

    /**
     * Relation between the attribute class name and the solidity type argument. The order is very important.
     * @return Array of parameters to send.
     */
    public SolidityParameter[] inParameters() default {};

    public SolidityParameter[] outParameters() default {};

}
