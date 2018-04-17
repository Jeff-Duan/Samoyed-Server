package com.cherish.demo.api;

import com.cherish.demo.entity.purchase.PurchaseOrder;
import com.cherish.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
        return newResult(0, "success", "采购申请已提交,请耐心等待审核。");
    }

    private Map failResult() {
        return newResult(-1, "fail", "采购申请提交失败，请联系1191837698@qq.com。");
    }

    @PostMapping(value = "/apply")
    public Map<String, Object> apply(@RequestParam("data") String data, HttpSession session) {
        purchaseService.apply(data, session);
        return successResult();
    }

    @GetMapping(value = "/order/all")
    public List<PurchaseOrder> allOrder() {
        return purchaseService.getAll();
    }


}
