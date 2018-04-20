package com.cherish.demo.service;

import com.cherish.demo.dao.SaleDao;
import com.cherish.demo.entity.sale.SaleOrder;
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
public class SaleService {

    private static final Logger logger = Logger.getLogger(SaleService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    SaleDao saleDao;

    public String apply(String data, HttpSession session) {
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
        SaleOrder saleOrder = gson.fromJson(data, SaleOrder.class);
        //填充订单编号-用户编号
        saleOrder.setOrderNumber(orderNumber);
        saleOrder.setOrderUserId(user.getUserId());
        //生成销售订单-订单明细
        saleDao.insertSaleOrder(saleOrder);
        saleOrder.getSaleOrderDetails().stream().forEach(saleOrderDetail -> {
            saleOrderDetail.setDetailOrderNumber(orderNumber);
            saleDao.insertSaleOrderDetail(saleOrderDetail);
        });
        return RESULT_SUCCESS;
    }

}
