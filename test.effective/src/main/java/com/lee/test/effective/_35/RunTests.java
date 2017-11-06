package com.lee.test.effective._35;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by LIPAN on 2017/11/6.
 */
public class RunTests {
    public static void main(String[] args) throws ClassNotFoundException {
        sampleMethod2(args[0]);
    }

    private static void sampleMethod1(String args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName(args);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    method.invoke(null);
                    passed++;
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    System.out.println(method + " failed: " + exc);
                } catch (Exception e) {
                    System.out.println("INVALID @Test: " + method);
                }

            }
        }
        System.out.printf("Passed: %d, failed: %d%n", passed, tests - passed);
    }

    private static void sampleMethod2(String args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName(args);
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    method.invoke(null);
                    System.out.printf("Test %s failed: No Exception%n", method);
                } catch (InvocationTargetException e) {
                    Throwable exc = e.getCause();
                    Class<? extends Exception> excType = method.getAnnotation(ExceptionTest.class).value();
                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf("Test %s failed: expected %s, got %s%n", method, excType.getName(), exc);
                    }
                } catch (Exception e) {
                    System.out.println("INVALID @Test: " + method);
                }

            }
        }
        System.out.printf("Passed: %d, failed: %d%n", passed, tests - passed);
    }
}
