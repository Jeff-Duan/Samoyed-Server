package com.cherish.demo.service;

import com.cherish.demo.dao.FinanceDao;
import com.cherish.demo.dao.PurchaseDao;
import com.cherish.demo.dao.SaleDao;
import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceService {

    private static final Logger logger = Logger.getLogger(FinanceService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    SaleService saleService;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    SaleDao saleDao;

    @Autowired
    FinanceDao financeDao;

    @Autowired
    Gson gson;

    /*
     * 财务付款-采购
     * */

    @Transactional
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

    @Transactional
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

    /*
     * 财务收款-销售
     * */

    @Transactional
    public String saleReceivable(String orderNumber, HttpSession session) {
        User user;
        SaleOrder saleOrder = new SaleOrder();
        ReceivableRecord receivableRecord = new ReceivableRecord();
        //获取登录用户
        try {
            Optional<String> optional = Optional.ofNullable((String) session.getAttribute("User"));
            user = gson.fromJson(optional.orElseThrow(NotFoundException::new), User.class);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(saleService.getOne(orderNumber));
        if (saleOrderOptional.isPresent()) {
            if (saleOrderOptional.get().getOrderStatusId() == 2) {
                //收取定金
                saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
                saleOrder.setOrderIsReceivableMoney(saleOrderOptional.get().getOrderDepositMoney());
                saleOrder.setOrderToReceivableMoney(saleOrderOptional.get().getOrderTotalMoney() - saleOrderOptional.get().getOrderDepositMoney());
                saleOrder.setOrderStatusId(3);
                receivableRecord.setOrderNumber(saleOrderOptional.get().getOrderNumber());
                receivableRecord.setRecordUserId(user.getUserId());
                receivableRecord.setRecordReceivableMoney(saleOrderOptional.get().getOrderDepositMoney());
            }
            if (saleOrderOptional.get().getOrderStatusId() == 4) {
                //支付尾款
                saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
                saleOrder.setOrderIsReceivableMoney(saleOrderOptional.get().getOrderTotalMoney());
                saleOrder.setOrderToReceivableMoney(0.00);
                saleOrder.setOrderStatusId(6);
                receivableRecord.setOrderNumber(saleOrderOptional.get().getOrderNumber());
                receivableRecord.setRecordUserId(user.getUserId());
                receivableRecord.setRecordReceivableMoney(saleOrderOptional.get().getOrderTotalMoney() - saleOrderOptional.get().getOrderDepositMoney());
            }
            saleDao.updateSaleOrderMoney(saleOrder);
            saleDao.updateSaleOrderStatus(saleOrder);
            financeDao.insertReceivableRecord(receivableRecord);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    /*
     * 财务付款-销售
     * */

    @Transactional
    public String salePay(String orderNumber, HttpSession session) {
        User user;
        SaleOrder saleOrder = new SaleOrder();
        PayRecord payRecord = new PayRecord();
        //获取登录用户
        try {
            Optional<String> optional = Optional.ofNullable((String) session.getAttribute("User"));
            user = gson.fromJson(optional.orElseThrow(NotFoundException::new), User.class);
        } catch (NotFoundException e) {
            logger.error("无法从Session获取登录用户.", e);
            return RESULT_ERROR;
        }
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(saleService.getOne(orderNumber));
        if (saleOrderOptional.isPresent()) {
            saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
            saleOrder.setOrderIsReceivableMoney(0.00);
            saleOrder.setOrderToReceivableMoney(saleOrderOptional.get().getOrderTotalMoney());
            saleOrder.setOrderStatusId(7);
            payRecord.setOrderNumber(saleOrderOptional.get().getOrderNumber());
            payRecord.setRecordUserId(user.getUserId());
            payRecord.setRecordPayMoney(saleOrderOptional.get().getOrderDepositMoney());
            saleDao.updateSaleOrderMoney(saleOrder);
            saleDao.updateSaleOrderStatus(saleOrder);
            financeDao.insertPayRecord(payRecord);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    /*
     * 财务对账
     * */

    public PageInfo<PurchaseOrder> purchasePayRecord(Integer pageNum, Integer pageSize) {
        PageInfo<PurchaseOrder> pageInfo = purchaseService.getAll("7", pageNum, pageSize);
        pageInfo.getList().stream().forEach(purchaseOrder -> {
            purchaseOrder.setPayRecords(financeDao.selectPayRecordByOrderNumber(purchaseOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<PurchaseOrder> purchaseReceivableRecord(Integer pageNum, Integer pageSize) {
        PageInfo<PurchaseOrder> pageInfo = purchaseService.getAll("8", pageNum, pageSize);
        pageInfo.getList().stream().forEach(purchaseOrder -> {
            purchaseOrder.setPayRecords(financeDao.selectPayRecordByOrderNumber(purchaseOrder.getOrderNumber()));
            purchaseOrder.setReceivableRecords(financeDao.selectReceivableRecordByOrderNumber(purchaseOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<SaleOrder> saleReceivableRecord(Integer pageNum, Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getAll("6", pageNum, pageSize);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setReceivableRecords(financeDao.selectReceivableRecordByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<SaleOrder> salePayRecord(Integer pageNum, Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getAll("7", pageNum, pageSize);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setReceivableRecords(financeDao.selectReceivableRecordByOrderNumber(saleOrder.getOrderNumber()));
            saleOrder.setPayRecords(financeDao.selectPayRecordByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public List<PayRecord> getPayRecordByDate(String date){
        return financeDao.selectPayRecordByDate(date);
    }

    public List<ReceivableRecord> getReceivableRecordByDate(String date){
        return financeDao.selectReceivableRecordByDate(date);
    }

}
