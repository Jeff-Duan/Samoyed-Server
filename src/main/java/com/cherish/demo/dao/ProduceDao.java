package com.cherish.demo.dao;

import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.produce.ProduceOrderActualDetail;
import com.cherish.demo.entity.produce.ProduceOrderPlanDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduceDao {

    void insertProduceOrder(ProduceOrder produceOrder);

    void insertProduceOrderPlanDetail(ProduceOrderPlanDetail produceOrderPlanDetail);

    List<ProduceOrder> selectAllProduceOrder(String statusId);

    List<ProduceOrderPlanDetail> selectAllProduceOrderPlanDetailProduceByOrderNumber(String orderNumber);

    List<ProduceOrderPlanDetail> selectAllProduceOrderPlanDetailMaterialByOrderNumber(String orderNumber);

    List<ProduceOrderActualDetail> selectAllProduceOrderActualDetailByOrderNumber(String orderNumber);

}
