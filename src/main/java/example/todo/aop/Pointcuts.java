package example.todo.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* example.todo.service.impl.*.add*(..))")
    public void addAction() { }

    @Pointcut("execution(* example.todo.service.impl.*.del*(..))")
    public void delAction() { }

    @Pointcut("execution(* example.todo.service.impl.*.update*(..))")
    public void updateAction() { }
}
