package com.cherish.demo.dao;

import com.cherish.demo.entity.basic.Produce;
import com.cherish.demo.entity.basic.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicDao {

    List<Supplier> selectAllSupplier();

    List<Produce> selectAllProduce();

}
