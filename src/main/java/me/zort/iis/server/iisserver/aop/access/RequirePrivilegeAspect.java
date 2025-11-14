package me.zort.iis.server.iisserver.aop.access;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.PrivilegesResolver;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.access.exception.InsufficientPrivilegesException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * An aspect that checks for required privileges before method execution.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class RequirePrivilegeAspect {
    private final PrivilegesResolver privilegesResolver;

    @Before("@annotation(RequirePrivilege)")
    public void checkPrivilege(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();

        RequirePrivilege requirePrivilege = signature.getMethod().getAnnotation(RequirePrivilege.class);

        if (!(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User user)) {
            throw new SecurityException("Unauthorized access attempt.");
        }

        List<Privilege> grantedPrivileges = privilegesResolver.getGrantedPrivileges(user);
        if (!grantedPrivileges.contains(requirePrivilege.privilege())) {
            throw new InsufficientPrivilegesException(requirePrivilege.privilege());
        }
    }
}
