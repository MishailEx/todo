package example.todo.aop;

import example.todo.entity.Item;
import example.todo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Aspects {

    @Before("Pointcuts.addAction()")
    public void beforeAddAction(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                User user = (User) arg;
                log.info("try add user - " + user.getName());
            }
            if (arg instanceof Item) {
                Item item = (Item) arg;
                log.info("try add item - " + item.getDescription()
                        + " author - " + item.getUser().getName());
            }
        }
    }

    @AfterReturning("Pointcuts.addAction()")
    public void afterAddAction(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info(methodSignature.getName() + " successfully");
    }

    @AfterThrowing(pointcut = "Pointcuts.addAction()", throwing = "exception")
    public void afterAddActionExIfException(Throwable exception) {
        log.info("catch exception - " + exception);
    }

    @Around("Pointcuts.delAction()")
    public Object delAction(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object result = null;
        if (signature.getName().equals("delUser")) {
            Object[] args = joinPoint.getArgs();
            log.info("try delete user id- " + args[0]);
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                log.info(throwable.getMessage());
            }
        }
        if (signature.getName().equals("delItem")) {
            Object[] args = joinPoint.getArgs();
            log.info("try delete item id - " + args[0]);
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                log.info(throwable.getMessage());
            }
        }
        log.info("delete successfully");
        return result;
    }
}
