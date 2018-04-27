package com.cherish.demo.api;

import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.service.PurchaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseApi {

    @Autowired
    PurchaseService purchaseService;

    private Map newResult(int code, String status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("status", status);
        result.put("message", message);
        return result;
    }

    private Map successResult() {
        return newResult(0, "success", "已提交,请耐心等待审核。");
    }

    private Map failResult() {
        return newResult(-1, "fail", "提交失败，请联系服务支持(Cherish-Hui)。");
    }

    @PostMapping(value = "/apply")
    public Map<String, Object> apply(@RequestParam("data") String data, HttpSession session) {
        String result = purchaseService.apply(data, session);
        switch (result) {
            case PurchaseService.RESULT_SUCCESS:
                return successResult();
            case PurchaseService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @GetMapping(value = "/order/all")
    public PageInfo<PurchaseOrder> allOrder(@RequestParam(value = "statusId", required = false, defaultValue = "0") String statusId,
                                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<PurchaseOrder> pageInfo = purchaseService.getAll(statusId, pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/toPay")
    public PageInfo<PurchaseOrder> toPayOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<PurchaseOrder> pageInfo = purchaseService.getToPay(pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/alreadyPay")
    public PageInfo<PurchaseOrder> alreadyPay(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<PurchaseOrder> pageInfo = purchaseService.getAlreadyPay(pageNum, pageSize);
        return pageInfo;
    }


}
