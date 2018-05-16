package com.cherish.demo.dao;

import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import com.cherish.demo.entity.warehouse.WareHouseProduce;
import com.cherish.demo.entity.warehouse.WareHouseWaste;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseDao {

    void updateAddWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateReduceWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateAddWareHouseProduce(WareHouseProduce wareHouseProduce);

    void updateAddWareHouseWaste(WareHouseWaste wareHouseWaste);

    WareHouseMaterial selectWareHouseMaterial(String materialId);

}
