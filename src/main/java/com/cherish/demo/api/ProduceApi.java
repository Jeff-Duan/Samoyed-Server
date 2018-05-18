package com.cherish.demo.api;

import com.cherish.demo.entity.produce.ProduceOrder;
import com.cherish.demo.service.ProduceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/produce")
public class ProduceApi {

    @Autowired
    ProduceService produceService;

    private Map result(int code, String status, String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("status", status);
        result.put("message", message);
        return result;
    }

    private Map successResult() {
        return result(0, "success", "已提交,请耐心等待审核。");
    }

    private Map failResult() {
        return result(-1, "fail", "提交失败，请联系服务支持(Cherish-Hui)。");
    }

    @PostMapping(value = "/plan")
    public Map<String, Object> plan(@RequestParam("data") String data, HttpSession session) {
        String result = produceService.plan(data, session);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/actual")
    public Map<String, Object> actual(@RequestParam("data") String data) {
        String result = produceService.actual(data);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/audit")
    public Map audit(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.audit(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/batchAudit")
    public Map batchAudit(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = produceService.batchAudit(orderNumbers);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/income")
    public Map income(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.income(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/batchIncome")
    public Map batchIncome(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = produceService.batchIncome(orderNumbers);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/transfer")
    public Map transfer(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.transfer(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/batchTransfer")
    public Map batchTransfer(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = produceService.batchTransfer(orderNumbers);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/check")
    public Map check(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.check(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/batchCheck")
    public Map batchCheck(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = produceService.batchCheck(orderNumbers);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/delete")
    public Map delete(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.delete(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @PostMapping(value = "/batchDelete")
    public Map batchDelete(@RequestParam("orderNumbers[]") String[] orderNumbers) {
        String result = produceService.batchDelete(orderNumbers);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @GetMapping(value = "/order/actual/isExist")
    public Map actualDetailIsExist(@RequestParam("orderNumber") String orderNumber) {
        String result = produceService.actualDetailIsExist(orderNumber);
        switch (result) {
            case ProduceService.RESULT_SUCCESS:
                return successResult();
            case ProduceService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @GetMapping(value = "/order/all")
    public PageInfo<ProduceOrder> allOrder(@RequestParam(value = "statusId", required = false, defaultValue = "0") String statusId,
                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<ProduceOrder> pageInfo = produceService.getAll(statusId, pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/toIssue")
    public PageInfo<ProduceOrder> toIssueOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<ProduceOrder> pageInfo = produceService.getToIssue(pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/alreadyIssue")
    public PageInfo<ProduceOrder> alreadyIssueOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<ProduceOrder> pageInfo = produceService.getAlreadyIssue(pageNum, pageSize);
        return pageInfo;
    }


}
