package com.example.webmvc.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CarServer {

    // TODO: 2017/7/5 0005  没有在配置文件中 配置加载这个类 
    //自动装备变量，spring会根据名字或者类型来装备这个变量，注解方式不需要set get方法了
    @Autowired
    private CarServerThread carServerThread;

    @PostConstruct
    public void serverStart() {
        Thread thread = new Thread(carServerThread);
        thread.start();
    }

}
