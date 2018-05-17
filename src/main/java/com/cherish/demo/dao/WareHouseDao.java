package com.cherish.demo.dao;

import com.cherish.demo.entity.warehouse.WareHouseMaterial;
import com.cherish.demo.entity.warehouse.WareHouseProduce;
import com.cherish.demo.entity.warehouse.WareHouseWaste;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WareHouseDao {

    void updateAddWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateReduceWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateWareHouseMaterial(WareHouseMaterial wareHouseMaterial);

    void updateAddWareHouseProduce(WareHouseProduce wareHouseProduce);

    void updateReduceWareHouseProduce(WareHouseProduce wareHouseProduce);

    void updateWareHouseProduce(WareHouseProduce wareHouseProduce);

    void updateAddWareHouseWaste(WareHouseWaste wareHouseWaste);

    void updateWareHouseWaste(WareHouseWaste wareHouseWaste);

    WareHouseMaterial selectWareHouseMaterial(String materialId);

    List<WareHouseMaterial> selectAllWareHouseMaterial();

    WareHouseProduce selectWareHouseProduce(WareHouseProduce wareHouseProduce);

    List<WareHouseProduce> selectAllWareHouseProduce();

    WareHouseWaste selectWareHouseWaste();

}
