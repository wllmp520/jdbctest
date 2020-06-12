package com.example.demo.exception;

/**
 * 自定义个一个异常去处理sqlexception 去解耦 jdbc 的 SQLException
 * @author wl
 * @date 2020-06-11 11:08:24
 */
public class JDBCException extends Exception {
    public static final long serialVersionUID = -914212712828534650L;

    private String message;

    private Throwable throwable;

    public JDBCException() {
        super();
    }

    public JDBCException(String message) {
        super(message);
        this.message = message;
    }

    public JDBCException(String message, Throwable cause) {
        this(message);
        this.throwable = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
