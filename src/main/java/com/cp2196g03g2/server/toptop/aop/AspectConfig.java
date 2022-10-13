package com.cp2196g03g2.server.toptop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.cp2196g03g2.server.toptop.exception.InternalServerException;

@Aspect
@Configuration
public class AspectConfig {

	private Logger logger = LoggerFactory.getLogger(AspectConfig.class);
	
	@Before(value = "execution(* com.cp2196g03g2.server.toptop.controller.*.*(..))")
	public void logStatmentControllerBefore(JoinPoint joinpoint) {
		logger.info("Excuting " + joinpoint);
	}
	
	@Before(value = "execution(* com.cp2196g03g2.server.toptop..service.impl.*.*(..))")
	public void logStatmentServiceBefore(JoinPoint joinpoint) {
		logger.info("Excuting " + joinpoint);
	}
	
	@After(value = "execution(* com.cp2196g03g2.server.toptop.controller.*.*(..))")
	public void logStatmentControllerAfter(JoinPoint joinpoint) {
		logger.info("Complete Execution " + joinpoint);
	}
	
	@After(value = "execution(* com.cp2196g03g2.server.toptop..service.impl.*.*(..))")
	public void logStatmentServiceAfter(JoinPoint joinpoint) {
		logger.info("Complete Execution " + joinpoint);
	}
	
	

	/*
	 * @Around(value =
	 * "execution(* com.cp2196g03g2.server.toptop..service.impl.*.*(..)) || execution(* com.cp2196g03g2.server.toptop.controller.*.*(..))"
	 * ) public Object profileStrategyMethods(ProceedingJoinPoint pjp) throws
	 * Throwable {
	 * 
	 * long start = System.currentTimeMillis(); Object output = null;
	 * logger.info("Class:"+pjp.getTarget().getClass()+" entry -> method ->"+pjp.
	 * getSignature().getName()); try{ output = pjp.proceed(); long elapsedTime =
	 * System.currentTimeMillis() - start; logger.info("Method execution time: " +
	 * elapsedTime + " milliseconds.");
	 * logger.info("Class:"+pjp.getTarget().getClass()+" exit -> method ->"+pjp.
	 * getSignature().getName()); }catch(Throwable t){ throw new
	 * InternalServerException(t.getMessage()); }
	 * 
	 * return output; }
	 */

    @AfterThrowing(pointcut="execution(* com.cp2196g03g2.server.toptop..service.impl.*.*(..))",throwing = "ex")
    public void doRecoveryActions(JoinPoint joinPoint, Throwable ex) {

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.error("Exception Occur in method: "
                + methodName + " with arguments "
                + arguments + "\nand the full toString: " + stuff + "\nThe exception is: "
                + ex.getMessage());
		/* logger.error(ex.getCause().toString()); */
    }
}
