package com.KurmOS.exceptions;

/**
 * Created by Lite on 02.11.2017.
 */
public class EnumException extends Exception {
    public EnumException() {
        super();
    }

    public EnumException(String message) {
        super(message);
    }

    public EnumException(Throwable e) {
        super(e);
    }
}
