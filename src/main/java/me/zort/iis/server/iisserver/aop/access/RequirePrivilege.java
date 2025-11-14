package me.zort.iis.server.iisserver.aop.access;

import me.zort.iis.server.iisserver.domain.access.Privilege;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePrivilege {

    /**
     * The privilege required to access the annotated method.
     *
     * @return the required privilege
     */
    Privilege privilege();
}
