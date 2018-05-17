package com.cherish.demo.api;

import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.service.FinanceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/finance")
public class FinanceApi {

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
    FinanceService financeService;

    @PostMapping(value = "/purchasePay")
    public Map<String, Object> purchasePay(@RequestParam("orderNumber") String orderNumber, HttpSession session) {
        String result = financeService.purchasePay(orderNumber, session);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/purchaseReceivable")
    public Map<String, Object> purchaseReceivable(@RequestParam("orderNumber") String orderNumber, HttpSession session) {
        String result = financeService.purchaseReceivable(orderNumber, session);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/saleReceivable")
    public Map<String, Object> saleReceivable(@RequestParam("orderNumber") String orderNumber, HttpSession session) {
        String result = financeService.saleReceivable(orderNumber, session);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/salePay")
    public Map<String, Object> salePay(@RequestParam("orderNumber") String orderNumber, HttpSession session) {
        String result = financeService.salePay(orderNumber, session);
        switch (result) {
            case FinanceService.RESULT_SUCCESS:
                return successResult();
            case FinanceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @GetMapping(value = "/purchasePayRecord")
    public PageInfo<PurchaseOrder> purchasePayRecord(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return financeService.purchasePayRecord(pageNum, pageSize);
    }

    @GetMapping(value = "/purchaseReceivableRecord")
    public PageInfo<PurchaseOrder> purchaseReceivableRecord(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return financeService.purchaseReceivableRecord(pageNum, pageSize);
    }

    @GetMapping(value = "/saleReceivableRecord")
    public PageInfo<SaleOrder> saleReceivableRecord(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return financeService.saleReceivableRecord(pageNum, pageSize);
    }

    @GetMapping(value = "/salePayRecord")
    public PageInfo<SaleOrder> salePayRecord(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return financeService.salePayRecord(pageNum, pageSize);
    }

}
