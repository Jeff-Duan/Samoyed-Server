package com.cherish.demo.api;

import com.cherish.demo.service.FinanceService;
import com.cherish.demo.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
public class WareHouseApi {

    private Map newResult(int code, String status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("status", status);
        result.put("message", message);
        return result;
    }

    private Map successResult() {
        return newResult(0, "success", "操作成功,请耐心等待。");
    }

    private Map failResult() {
        return newResult(-1, "fail", "操作失败，请联系服务支持(Cherish-Hui)。");
    }

    @Autowired
    WareHouseService wareHouseService;

    @PostMapping(value = "/purchaseDelivery")
    public Map<String, Object> purchaseDelivery(@RequestParam("orderNumber") String orderNumber) {
        String result = wareHouseService.purchaseDelivery(orderNumber);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseBatchDelivery")
    public Map<String, Object> purchaseBatchDelivery(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = wareHouseService.purchaseBatchDelivery(orderNumbers);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseCheck")
    public Map<String, Object> purchaseCheck(@RequestParam("orderNumber") String orderNumber) {
        String result = wareHouseService.purchaseCheck(orderNumber);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseBatchCheck")
    public Map<String, Object> purchaseBatchCheck(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = wareHouseService.purchaseBatchCheck(orderNumbers);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseReturn")
    public Map<String, Object> purchaseReturn(@RequestParam("orderNumber") String orderNumber) {
        String result = wareHouseService.purchaseReturn(orderNumber);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseBatchReturn")
    public Map<String, Object> purchaseBatchReturn(@RequestParam("orderNumbers") String[] orderNumbers) {
        String result = wareHouseService.purchaseBatchReturn(orderNumbers);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseStorage")
    public Map<String, Object> purchaseStorage(@RequestParam("orderNumber") String orderNumber) {
        String result = wareHouseService.purchaseStorage(orderNumber);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseBatchStorage")
    public Map<String, Object> purchaseBatchStorage(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = wareHouseService.purchaseBatchStorage(orderNumbers);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

}
