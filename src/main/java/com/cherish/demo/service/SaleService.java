package com.cherish.demo.service;

import com.cherish.demo.dao.SaleDao;
import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.cherish.demo.util.OrderNumber;
import com.github.pagehelper.PageHelper;
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
public class SaleService {

    private static final Logger logger = Logger.getLogger(SaleService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    SaleDao saleDao;

    @Autowired
    Gson gson;

    @Transactional
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

    @Transactional
    public String audit(String orderNumber) {
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(getOne(orderNumber));
        if (saleOrderOptional.isPresent() && saleOrderOptional.get().getOrderStatusId() == 1) {
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setOrderNumber(saleOrderOptional.get().getOrderNumber());
            saleOrder.setOrderStatusId(2);
                saleDao.updateSaleOrderStatus(saleOrder);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String batchAudit(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = audit(orderNumber);
            if(RESULT_ERROR.equals(result)){
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    @Transactional
    public String delete(String orderNumber) {
        Optional<SaleOrder> saleOrderOptional = Optional.ofNullable(getOne(orderNumber));
        if (saleOrderOptional.isPresent() && saleOrderOptional.get().getOrderStatusId() == 1) {
            saleDao.deleteSaleOrder(orderNumber);
            saleDao.deleteSaleOrderDetail(orderNumber);
            return RESULT_SUCCESS;
        }
        return RESULT_ERROR;
    }

    public String batchDelete(String[] orderNumbers) {
        for (String orderNumber : orderNumbers) {
            String result = delete(orderNumber);
            if(RESULT_ERROR.equals(result)){
                return RESULT_ERROR;
            }
        }
        return RESULT_SUCCESS;
    }

    public SaleOrder getOne(String orderNumber){
        SaleOrder saleOrder = saleDao.selectSaleOrderByOrderNumber(orderNumber);
        saleOrder.setSaleOrderDetails(saleDao.selectSaleOrderDetailByOrderNumber(saleOrder.getOrderNumber()));
        return saleOrder;
    }

    public PageInfo<SaleOrder> getAll(String statusId, Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<SaleOrder> saleOrders = saleDao.selectAllSaleOrder(statusId);
        PageInfo<SaleOrder> pageInfo = new PageInfo<SaleOrder>(saleOrders);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setSaleOrderDetails(saleDao.selectSaleOrderDetailByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<SaleOrder> getIsPayDeposit(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<SaleOrder> saleOrders = saleDao.selectAllIsPayDepositSaleOrder();
        PageInfo<SaleOrder> pageInfo = new PageInfo<SaleOrder>(saleOrders);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setSaleOrderDetails(saleDao.selectSaleOrderDetailByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<SaleOrder> getIsPayFinal(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<SaleOrder> saleOrders = saleDao.selectAllIsPayFinalSaleOrder();
        PageInfo<SaleOrder> pageInfo = new PageInfo<SaleOrder>(saleOrders);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setSaleOrderDetails(saleDao.selectSaleOrderDetailByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

    public PageInfo<SaleOrder> getIsSuccess(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<SaleOrder> saleOrders = saleDao.selectAllIsSuccessSaleOrder();
        PageInfo<SaleOrder> pageInfo = new PageInfo<SaleOrder>(saleOrders);
        pageInfo.getList().stream().forEach(saleOrder -> {
            saleOrder.setSaleOrderDetails(saleDao.selectSaleOrderDetailByOrderNumber(saleOrder.getOrderNumber()));
        });
        return pageInfo;
    }

}
