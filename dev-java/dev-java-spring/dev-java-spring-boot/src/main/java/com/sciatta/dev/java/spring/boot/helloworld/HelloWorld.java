package com.sciatta.dev.java.spring.boot.helloworld;

import com.sciatta.dev.java.spring.boot.schoolstarter.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangxiaoyu on 2020/12/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * HelloWorld 若无法运行，需要确认首次引入spring boot时，与原有依赖jar的冲突
 */
@SpringBootApplication
@RestController
public class HelloWorld {
    @Autowired
    private School school;
    
    @RequestMapping("/")
    public String index() {
        return "Hello world!";
    }
    
    @RequestMapping("/school")
    public String school() {
        return school.getName() + " has " + school.getKlasses().size() + " classes!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class, args);
    }
}
