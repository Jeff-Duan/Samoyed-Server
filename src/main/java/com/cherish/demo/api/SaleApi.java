package com.cherish.demo.api;

import com.cherish.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sale")
public class SaleApi {

    @Autowired
    SaleService saleService;

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
    public Map<String, Object> plan(@RequestParam("data") String data, HttpSession session) {
        String result = saleService.apply(data,session);
        switch (result) {
            case SaleService.RESULT_SUCCESS:
                return successResult();
            case SaleService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }


}
