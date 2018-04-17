package com.cherish.demo.api;

import com.cherish.demo.entity.basic.Supplier;
import com.cherish.demo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/basic")
public class BasicApi {

    @Autowired
    BasicService basicService;

    @GetMapping(value = "/supplier/all")
    public List<Supplier> allSupplier() {
        List<Supplier> suppliers = basicService.allSupplier();
        return suppliers;
    }

}
