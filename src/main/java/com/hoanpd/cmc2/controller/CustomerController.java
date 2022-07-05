package com.hoanpd.cmc2.controller;

import com.hoanpd.cmc2.reponsitory.CustomerRepository;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class CustomerController {
    /**
     * sdhfsdh
     */
    @Value("${spring.datasource.url}")
    String url;

    String status = System.getenv("status");

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void initData(){
        System.out.println("__________Reset and init data________________");
    }

    @RequestMapping("/customer")
    public ResponseEntity<?> listCustomer(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort){
        System.out.println("spring.datasource.url"+url);
        System.out.println("environtment"+ status);
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);


        return ResponseEntity.ok(customerRepository.findCustomers(pageable).getContent());
    }


}
