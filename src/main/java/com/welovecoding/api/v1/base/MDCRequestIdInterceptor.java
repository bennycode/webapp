package com.welovecoding.api.v1.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

/**
 * This Interceptor is responsible for setting the MDC request id to track request in logfiles
 *
 * @author Michael Koppen
 */
@Component
@Aspect
public class MDCRequestIdInterceptor {
    
    private static final String REQUEST_ID_KEY = "reqid";

    public MDCRequestIdInterceptor() {
    }

    @Pointcut("@annotation(com.welovecoding.api.v1.base.Tracked)")
    public void onlyLoggedAnnotatedTypes() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    /**
     * Setting the request ID for methods which have HttpServletRequest in parameter list.
     *
     * @param pjp ProceedingJoinPoint for execution of intercepted method
     * @return result of the executed method
     * @throws Throwable Thrown exception of the executed method
     */
    @Around("onlyLoggedAnnotatedTypes()")
    public Object intercept(final ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        Logger LOG = LoggerFactory.getLogger(method.getDeclaringClass().getName());

        if (method.getParameterTypes() != null) {
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    if (pjp.getArgs()[i] instanceof HttpServletRequest) {
                        HttpServletRequest req = (HttpServletRequest)pjp.getArgs()[i];
                        LOG.debug(""+req.getContentLength());
                        MDC.put(REQUEST_ID_KEY, req.getContentLength()+"");
                    }
                }
            }
        }

        Object retVal;
        try {
            retVal = pjp.proceed(pjp.getArgs());
        } finally{
            MDC.remove(REQUEST_ID_KEY);
        }
        return retVal;
    }

}
