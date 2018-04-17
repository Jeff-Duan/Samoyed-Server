package com.cherish.demo.service;

import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.cherish.demo.util.OrderNumber;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private static final Logger logger = Logger.getLogger(PurchaseService.class);
    private static final String RESULT_SUCCESS = "SUCCESS";
    private static final String RESULT_ERROR = "ERROR";

    @Autowired
    PurchaseDao purchaseDao;

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
        PurchaseOrder purchaseOrder = gson.fromJson(data, PurchaseOrder.class);
        //填充订单编号-用户编号-待支付金额
        purchaseOrder.setOrderNumber(orderNumber);
        purchaseOrder.setOrderUserId(user.getUserId());
        purchaseOrder.setOrderToPayMoney(purchaseOrder.getOrderTotalMoney());
        //生成采购订单-订单明细
        purchaseDao.insertPurchaseOrder(purchaseOrder);
        purchaseOrder.getPurchaseOrderDetails().stream().forEach(purchaseOrderDetail -> {
            purchaseOrderDetail.setDetailOrderNumber(orderNumber);
            purchaseDao.insertPurchaseOrderDetail(purchaseOrderDetail);
        });
        return RESULT_SUCCESS;
    }

    public List<PurchaseOrder> getAll() {
        return purchaseDao.selectAllPurchaseOrder();
    }

}
