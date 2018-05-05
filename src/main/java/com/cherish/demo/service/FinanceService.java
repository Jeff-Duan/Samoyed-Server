package com.cherish.demo.service;

import com.cherish.demo.dao.FinanceDao;
import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class FinanceService {

    private static final Logger logger = Logger.getLogger(FinanceService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    FinanceDao financeDao;

    @Autowired
    Gson gson;

    /*
     * 财务付款-采购
     * */

    public String purchasePay(String orderNumber, HttpSession session) {
        User user;
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        PayRecord payRecord = new PayRecord();
        //获取登录用户
        try {
            Optional<String> optional = Optional.ofNullable((String) session.getAttribute("User"));
            user = gson.fromJson(optional.orElseThrow(NotFoundException::new), User.class);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            if (purchaseOrderOptional.get().getOrderPayType() == 1) {
                //直接付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderIsPayMoney(purchaseOrderOptional.get().getOrderPayMoney());
                purchaseOrder.setOrderToPayMoney(0.00);
                purchaseOrder.setOrderStatusId(3);
                payRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                payRecord.setRecordUserId(user.getUserId());
                payRecord.setRecordPayMoney(purchaseOrderOptional.get().getOrderPayMoney());
            } else if (purchaseOrderOptional.get().getOrderPayType() == 2) {
                //货到付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderIsPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
                purchaseOrder.setOrderToPayMoney(0.00);
                purchaseOrder.setOrderStatusId(6);
                payRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                payRecord.setRecordUserId(user.getUserId());
                payRecord.setRecordPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
            } else {
                if (purchaseOrderOptional.get().getOrderIsPayMoney() == 0.00) {
                    //支付定金
                    purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                    purchaseOrder.setOrderIsPayMoney(purchaseOrderOptional.get().getOrderPayMoney());
                    purchaseOrder.setOrderToPayMoney(purchaseOrderOptional.get().getOrderTotalMoney() - purchaseOrderOptional.get().getOrderPayMoney());
                    purchaseOrder.setOrderStatusId(3);
                    payRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                    payRecord.setRecordUserId(user.getUserId());
                    payRecord.setRecordPayMoney(purchaseOrderOptional.get().getOrderPayMoney());
                } else {
                    //支付尾款
                    purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                    purchaseOrder.setOrderIsPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
                    purchaseOrder.setOrderToPayMoney(0.00);
                    purchaseOrder.setOrderStatusId(6);
                    payRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                    payRecord.setRecordUserId(user.getUserId());
                    payRecord.setRecordPayMoney(purchaseOrderOptional.get().getOrderTotalMoney() - purchaseOrderOptional.get().getOrderPayMoney());
                }
            }
            purchaseDao.updatePurchaseOrderMoney(purchaseOrder);
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            financeDao.insertPayRecord(payRecord);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    /*
     * 财务收款-采购
     * */

    public String purchaseReceivable(String orderNumber, HttpSession session) {
        User user;
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        ReceivableRecord receivableRecord = new ReceivableRecord();
        //获取登录用户
        try {
            Optional<String> optional = Optional.ofNullable((String) session.getAttribute("User"));
            user = gson.fromJson(optional.orElseThrow(NotFoundException::new), User.class);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        Optional<PurchaseOrder> purchaseOrderOptional = Optional.ofNullable(purchaseService.getOne(orderNumber));
        if (purchaseOrderOptional.isPresent()) {
            if (purchaseOrderOptional.get().getOrderPayType() == 1) {
                //直接付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderIsPayMoney(0.00);
                purchaseOrder.setOrderToPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
                purchaseOrder.setOrderStatusId(8);
                receivableRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                receivableRecord.setRecordUserId(user.getUserId());
                receivableRecord.setRecordReceivableMoney(purchaseOrderOptional.get().getOrderPayMoney());
            } else if (purchaseOrderOptional.get().getOrderPayType() == 2) {
                //货到付款
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderIsPayMoney(0.00);
                purchaseOrder.setOrderToPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
                purchaseOrder.setOrderStatusId(8);
                receivableRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                receivableRecord.setRecordUserId(user.getUserId());
                receivableRecord.setRecordReceivableMoney(purchaseOrderOptional.get().getOrderPayMoney());
            } else {
                //支付定金
                purchaseOrder.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                purchaseOrder.setOrderIsPayMoney(0.00);
                purchaseOrder.setOrderToPayMoney(purchaseOrderOptional.get().getOrderTotalMoney());
                purchaseOrder.setOrderStatusId(8);
                receivableRecord.setOrderNumber(purchaseOrderOptional.get().getOrderNumber());
                receivableRecord.setRecordUserId(user.getUserId());
                receivableRecord.setRecordReceivableMoney(purchaseOrderOptional.get().getOrderPayMoney());
            }
            purchaseDao.updatePurchaseOrderMoney(purchaseOrder);
            purchaseDao.updatePurchaseOrderStatus(purchaseOrder);
            financeDao.insertReceivableRecord(receivableRecord);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }


}
