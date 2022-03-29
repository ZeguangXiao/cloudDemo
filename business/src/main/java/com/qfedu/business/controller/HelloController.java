package com.qfedu.business.controller;

import com.qfedu.service.demo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author XZG
 * @creatTime 2022-03-28
 */
@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

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


    @GetMapping("/hello4")
    public void hello4(){
        System.out.println("======================GET====================");
        Book book = restTemplate.getForObject("http://storage/book/?id={1}", Book.class, 99);
        System.out.println("book = " + book);

        HashMap<String,Object> map = new HashMap();
        map.put("id",99);
        Book book1 = restTemplate.getForObject("http://storage/book/?id={id}", Book.class, map);
        System.out.println("book1 = " + book1);

        System.out.println("======================GetForEntity====================");
        ResponseEntity<Book> entity = restTemplate.getForEntity("http://storage/book/?id={1}", Book.class, 88);

        System.out.println("entity.getStatusCodeValue() = " + entity.getStatusCodeValue());
        System.out.println("entity.getStatusCode() = " + entity.getStatusCode());
        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            List<String> list = headers.get(s);
            System.out.println("list = " + list);
        }

        Book body = entity.getBody();
        System.out.println("body = " + body);

        System.out.println("======================POST====================");
        MultiValueMap<String, Object> req = new LinkedMultiValueMap<>();
        req.add("id",77);
        req.add("name","三国演义");
        req.add("author","罗贯中");
        Book book2 = restTemplate.postForObject("http://storage/book/", req, Book.class);
        System.out.println("book2 = " + book2);

        System.out.println("======================POST-forLocation====================");
        URI uri = restTemplate.postForLocation("http://storage/book/add",book1);
        System.out.println("uri = " + uri);

        System.out.println("======================PUT====================");
        restTemplate.put("http://storage/book/",book1);

        System.out.println("======================DELETE====================");
        restTemplate.delete("http://storage/book/{1}",66);
    }
}
