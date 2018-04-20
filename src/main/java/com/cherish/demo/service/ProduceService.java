package com.cherish.demo.service;

import com.cherish.demo.dao.ProduceDao;
import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.cherish.demo.util.OrderNumber;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class ProduceService {

    private static final Logger logger = Logger.getLogger(ProduceService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    ProduceDao produceDao;

    public String plan(String data, HttpSession session) {
        User user;
        //获取登录用户
        try {
            Optional<User> optional = Optional.ofNullable((User) session.getAttribute("User"));
            user = optional.orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        //获取随机订单号
        String orderNumber = OrderNumber.getOrderIdByTime();
        //数据转换
        Gson gson = new Gson();
        ProduceOrder produceOrder = gson.fromJson(data, ProduceOrder.class);
        //填充订单编号-用户编号
        produceOrder.setOrderNumber(orderNumber);
        produceOrder.setOrderUserId(user.getUserId());
        //生成生产计划订单-订单明细
        produceDao.insertProduceOrder(produceOrder);
        produceOrder.getProduceOrderDetails().stream().forEach(produceOrderDetail -> {
            produceOrderDetail.setDetailOrderNumber(orderNumber);
            produceDao.insertProduceOrderDetail(produceOrderDetail);
        });
        return RESULT_SUCCESS;
    }

}
