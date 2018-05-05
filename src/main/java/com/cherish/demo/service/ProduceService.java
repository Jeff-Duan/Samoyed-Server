package com.cherish.demo.service;

import com.cherish.demo.dao.ProduceDao;
import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.entity.produce.ProduceOrderPlanDetail;
import com.cherish.demo.entity.user.User;
import com.cherish.demo.exception.NotFoundException;
import com.cherish.demo.util.OrderNumber;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class ProduceService {

    private static final Logger logger = Logger.getLogger(ProduceService.class);
    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_ERROR = "ERROR";

    @Autowired
    ProduceDao produceDao;

    @Autowired
    Gson gson;

    public String plan(String data, HttpSession session) {
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
        ProduceOrder produceOrder = gson.fromJson(data, ProduceOrder.class);
        //填充订单编号-用户编号
        produceOrder.setOrderNumber(orderNumber);
        produceOrder.setOrderUserId(user.getUserId());
        //生成生产计划订单-订单明细
        produceDao.insertProduceOrder(produceOrder);
        produceOrder.getProduceOrderPlanDetails().stream().forEach(produceOrderPlanDetail -> {
            produceOrderPlanDetail.setDetailOrderNumber(orderNumber);
            produceDao.insertProduceOrderPlanDetail(produceOrderPlanDetail);
        });
        return RESULT_SUCCESS;
    }

    public PageInfo<ProduceOrder> getAll(String statusId, Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        //填充订单
        List<ProduceOrder> purchaseOrders = produceDao.selectAllProduceOrder(statusId);
        PageInfo<ProduceOrder> pageInfo = new PageInfo<ProduceOrder>(purchaseOrders);
        pageInfo.getList().stream().forEach(produceOrder -> {
            //填充计划明细
            List<ProduceOrderPlanDetail> planDetailList1 = produceDao.selectAllProduceOrderPlanDetailProduceByOrderNumber(produceOrder.getOrderNumber());
            List<ProduceOrderPlanDetail> planDetailList2 = produceDao.selectAllProduceOrderPlanDetailMaterialByOrderNumber(produceOrder.getOrderNumber());
            planDetailList1.stream().forEach(produceOrderPlanDetail1 -> {
                planDetailList2.stream().forEach(produceOrderPlanDetail2 -> {
                    if (!StringUtils.isEmpty(produceOrderPlanDetail1.getDetailOrderNumber()) && !StringUtils.isEmpty(produceOrderPlanDetail2.getDetailOrderNumber()) && produceOrderPlanDetail1.getDetailOrderNumber().equals(produceOrderPlanDetail2.getDetailOrderNumber())) {
                        produceOrderPlanDetail1.setMaterial(produceOrderPlanDetail2.getMaterial());
                        produceOrderPlanDetail1.setMaterialUnit(produceOrderPlanDetail2.getMaterialUnit());
                    }
                });
            });
            produceOrder.setProduceOrderPlanDetails(planDetailList1);
            //填充生产明细
            produceOrder.setProduceOrderActualDetails(produceDao.selectAllProduceOrderActualDetailByOrderNumber(produceOrder.getOrderNumber()));
        });
        return pageInfo;
    }

}
