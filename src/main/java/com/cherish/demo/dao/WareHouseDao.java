package com.cherish.demo.dao;

import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseDao {

    void updateWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

}
