package com.jason.util;

public class FunctionHelper {

    public interface Invokable<T> {
        T invoke() throws Exception;

    }

    public static <T> T implicit(Invokable<T> invokable) {
        T result = null;
        try {
            result = invokable.invoke();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
