package com.frost.temp.timer;

import com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer;
import com.frost.temp.encrypt.matrix.Matrix;
import com.frost.temp.logging.Logger;
import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Aspect
public class Timer {

    @Pointcut("String execution(com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer.encryptDoubleMatrix(..))")
    @Around(value = "encryptDoubleMatrix(firstMatrix, secondMatrix, message)", argNames = "pjp,firstMatrix,secondMatrix,message")
    public String measure(ProceedingJoinPoint pjp, Matrix firstMatrix, Matrix secondMatrix, String message) {
        try {
            Stopwatch stopwatch = Stopwatch.createStarted();
            String answer = (String) pjp.proceed(new Object[] {
                    firstMatrix, secondMatrix, message
            });
            Logger.log(
                    Level.INFO, "Code was generated successfully. Time: "
                    + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms", Cryptographer.class
            );
            return answer;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return "Invalid code";
        }
    }
}
