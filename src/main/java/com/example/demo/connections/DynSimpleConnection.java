package com.example.demo.connections;


import com.example.demo.datasource.DynSimpleDataSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * 动态代理数据连接类
 * @author wl
 * @date 2020-06-11 17:43:38
 */
public class DynSimpleConnection implements InvocationHandler {
    private Connection realConnection = null;
    private Connection wrapConnection = null;
    private DynSimpleDataSource dataSource = null;

    public DynSimpleConnection(DynSimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection bind(Connection obj){
        this.realConnection = obj;
        this.wrapConnection = (Connection)Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
        return wrapConnection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName  = method.getName();
        Object object = null;
        if ("close".equals(methodName )){
            dataSource.closeConnection(wrapConnection);
        }else{
            object =method.invoke(realConnection,args);
        }
        return object;
    }
}
