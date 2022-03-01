package elakeed.projects.music.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

    @Pointcut("execution(* elakeed.projects.music.api.*.*(..))")
    public void apiLayerExecution() {
    }

    @Pointcut("execution(* elakeed.projects.music.repository.*.*(..))")
    public void repositoryLayerExecution() {
    }

    @Pointcut("execution(* elakeed.projects.music.service.*.*(..)))")
    public void serviceLayerExecution() {
    }

    @Pointcut("bean(*Service*)")
    public void allBeansContainingService() {
    }

    //not need a star(*) like execution and ending with "..*" but do the same this as execution
    @Pointcut("within(elakeed.projects.music.api..*)")
    public void apiLayerExecutionUsingWithin() {
    }

    @Pointcut("@annotation(elakeed.projects.music.aspect.TrackTime)")
    public void trackTimeAnnotation() {
    }

    //Never tested
    @Pointcut("repositoryLayerExecution() && serviceLayerExecution() && apiLayerExecution()")
    public void allLayersExecution() {
    }
}
