package com.nagarro.banking.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients()  // Specify the base package
//@EnableFeignClients(basePackages = "com.nagarro.banking.customer.client")  // Specify the base package
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
        System.out.println("Hello World!");
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Available Endpoints:");
//            ctx.getBean(RequestMappingHandlerMapping.class)
//                    .getHandlerMethods()
//                    .forEach((requestMapping, method) ->
//                            System.out.println(requestMapping + " -> " + method.getMethod()));
//        };
//    }

}
