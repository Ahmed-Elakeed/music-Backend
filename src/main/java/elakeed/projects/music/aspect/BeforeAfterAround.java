package elakeed.projects.music.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
public class BeforeAfterAround {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before("execution(* elakeed.projects.music.service.*.*(..))")
    public void beforeExecute(JoinPoint joinPoint) {
        logger.info("Before service method {}", joinPoint);
    }

    @After("execution(* elakeed.projects.music.repository.*.*(..))")
    public void afterExecute(JoinPoint joinPoint) {
        logger.info("After repository method {}", joinPoint);
    }

    @AfterReturning(value = "execution(* elakeed.projects.music.api.*.*(..))", returning = "responseEntity")
    public void afterReturning(JoinPoint joinPoint, ResponseEntity responseEntity) {
        logger.info("{} return with value {}", joinPoint, responseEntity.getBody());
    }

    @Around(value = "execution(* elakeed.projects.music.api.*.*(..))")
    public void aroundExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        Long allTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken {}", allTime);
    }


    /////---------- using CommonJoinPointConfig ----------/////

    @Before("elakeed.projects.music.aspect.CommonJoinPointConfig.allBeansContainingService()")
    public void CommonBeforeExecute(JoinPoint joinPoint) {
        logger.info("Before service method {}", joinPoint);
    }

    @After("elakeed.projects.music.aspect.CommonJoinPointConfig.repositoryLayerExecution()")
    public void CommonAfterExecute(JoinPoint joinPoint) {
        logger.info("After repository method {}", joinPoint);
    }

    @AfterReturning(value = "elakeed.projects.music.aspect.CommonJoinPointConfig.apiLayerExecutionUsingWithin()", returning = "responseEntity")
    public void CommonAfterReturning(JoinPoint joinPoint, ResponseEntity responseEntity) {
        logger.info("{} return with value {}", joinPoint, responseEntity.getBody());
    }


    @Around(value = "elakeed.projects.music.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
    public void CommonAroundExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        Long allTime = System.currentTimeMillis() - startTime;
        logger.info("Time taken {}", allTime);
    }


}
