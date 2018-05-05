package com.cherish.demo.service;

import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.cherish.demo.util.OrderNumber;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private static final Logger logger = Logger.getLogger(PurchaseService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    Gson gson;

    public String apply(String data, HttpSession session) {
        User user;
        //获取登录用户
        try {
            Optional<String> optional = Optional.ofNullable((String) session.getAttribute("User"));
            user = gson.fromJson(optional.orElseThrow(NotFoundException::new), User.class);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        //获取随机订单号
        String orderNumber = OrderNumber.getOrderIdByTime();
        //数据转换
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

    public PurchaseOrder getOne(String orderNumber) {
        PurchaseOrder purchaseOrder = purchaseDao.selectPurchaseOrderByOrderNumber(orderNumber);
        purchaseOrder.setPurchaseOrderDetails(purchaseDao.selectPurchaseOrderDetailByOrderNumber(purchaseOrder.getOrderNumber()));
        return purchaseOrder;
    }

    public PageInfo<PurchaseOrder> getAll(String statusId, Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseOrder> purchaseOrders = purchaseDao.selectAllPurchaseOrder(statusId);
        PageInfo<PurchaseOrder> pageInfo = new PageInfo<PurchaseOrder>(purchaseOrders);
        pageInfo.getList().stream().forEach(purchaseOrder -> {
            purchaseOrder.setPurchaseOrderDetails(purchaseDao.selectPurchaseOrderDetailByOrderNumber(purchaseOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<PurchaseOrder> getToPay(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseOrder> purchaseOrders = purchaseDao.selectAllToPayPurchaseOrder();
        PageInfo<PurchaseOrder> pageInfo = new PageInfo<PurchaseOrder>(purchaseOrders);
        pageInfo.getList().stream().forEach(purchaseOrder -> {
            purchaseOrder.setPurchaseOrderDetails(purchaseDao.selectPurchaseOrderDetailByOrderNumber(purchaseOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<PurchaseOrder> getAlreadyPay(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseOrder> purchaseOrders = purchaseDao.selectAllAlreadyPayPurchaseOrder();
        PageInfo<PurchaseOrder> pageInfo = new PageInfo<PurchaseOrder>(purchaseOrders);
        pageInfo.getList().stream().forEach(purchaseOrder -> {
            purchaseOrder.setPurchaseOrderDetails(purchaseDao.selectPurchaseOrderDetailByOrderNumber(purchaseOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public String audit(String orderNumber) {
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(getOne(orderNumber));
        if (purchaseOrderOptional.isPresent() && purchaseOrderOptional.get().getOrderStatusId() == 1) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
            if (purchaseOrderOptional.get().getOrderPayType() == 1 || purchaseOrderOptional.get().getOrderPayType() == 3) {
                purchaseOrder.setOrderStatusId(2);
                purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            } else {
                purchaseOrder.setOrderStatusId(3);
                purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            }
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String batchAudit(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(getOne(orderNumber));
            if (purchaseOrderOptional.isPresent() && purchaseOrderOptional.get().getOrderStatusId() == 1) {
                PurchaseOrder purchaseOrder = new PurchaseOrder();
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                if (purchaseOrderOptional.get().getOrderPayType() == 1 || purchaseOrderOptional.get().getOrderPayType() == 3) {
                    purchaseOrder.setOrderStatusId(2);
                    purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
                } else {
                    purchaseOrder.setOrderStatusId(3);
                    purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
                }
            }
        }
        return RESULT_SUCCESS;
    }


}
