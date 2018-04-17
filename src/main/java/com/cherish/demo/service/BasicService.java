package com.cherish.demo.service;

import com.cherish.demo.dao.BasicDao;
import com.cherish.demo.entity.basic.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {

    @Autowired
    BasicDao basicDao;

    public List<Supplier> allSupplier() {
        return basicDao.selectAllSupplier();
    }
}
