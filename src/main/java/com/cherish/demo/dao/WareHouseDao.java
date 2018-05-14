package com.cherish.demo.dao;

import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseDao {

    void updateAddWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateReduceWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    WareHouseMaterial selectWareHouseMaterial(String materialId);

}
