package com.cherish.demo.dao;

import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.produce.ProduceOrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceDao {

    void insertProduceOrder(ProduceOrder produceOrder);

    void insertProduceOrderDetail(ProduceOrderDetail produceOrderDetail);

}
