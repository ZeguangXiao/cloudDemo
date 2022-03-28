package com.ze.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XZG
 * @creatTime 2022-03-28
 */
@RestController
public class HelloController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello(){
        return "hello resttemplate";
    }

    @GetMapping("/hello2")
    public String hello2(){
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances("storage");
            ServiceInstance serviceInstance = instances.get(1);

            String host = serviceInstance.getHost();
            int port = serviceInstance.getPort();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        URL url = new URL();
//        URLConnection urlConnection = url.openConnection();
//
//        urlConnection.connect();
        return "hello resttemplate";
    }

    @GetMapping("/hello3")
    public String hello3(){
        List<ServiceInstance> instances = discoveryClient.getInstances("storage");
        ServiceInstance serviceInstance = instances.get(1);

        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        return "hello resttemplate";
    }
}
