package com.example.wangweijun.behavior;


import android.util.Log;

import com.example.wangweijun.behavior.annotation.Behavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class BehaviorTrace {

    @Pointcut("execution(@com.example.wangweijun.behavior.annotation.Behavior * *(..)) && @annotation(behavior)")
    public void behaviorRecord(Behavior behavior) {

    }

    @Around("behaviorRecord(behavior)")
    public void aroundJointPoint(final ProceedingJoinPoint joinPoint, Behavior behavior) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        String name = method.getName();
        Class[] parameterTypes = signature.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class parameterType = parameterTypes[i];
        }


        String[] value = behavior.value();

        long start = System.currentTimeMillis();
        joinPoint.proceed();
        Log.i("BehaviorTrace", name + " spend time :" + (System.currentTimeMillis() - start));
    }

    /*@Pointcut("execution(@com.example.wangweijun.behavior.annotation.Behavior * *(..))")
    public void behaviorRecord(){

    }

    @Around("behaviorRecord()")
    public void aroundJointPoint(final ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        Log.i("BehaviorTrace", "spend time :"+(System.currentTimeMillis()-start));
    }*/

}
