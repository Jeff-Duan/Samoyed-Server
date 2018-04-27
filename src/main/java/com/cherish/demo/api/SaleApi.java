package com.cherish.demo.api;

import com.cherish.demo.entity.sale.SaleOrder;
import com.cherish.demo.service.SaleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String result = saleService.apply(data, session);
        switch (result) {
            case SaleService.RESULT_SUCCESS:
                return successResult();
            case SaleService.RESULT_ERROR:
                return failResult();
            default:
                return failResult();
        }
    }

    @GetMapping(value = "/order/all")
    public PageInfo<SaleOrder> allOrder(@RequestParam(value = "statusId", required = false, defaultValue = "0") String statusId,
                                        @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getAll(statusId, pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/isPayDeposit")
    public PageInfo<SaleOrder> isPayDepositOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getIsPayDeposit(pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/isPayFinal")
    public PageInfo<SaleOrder> isPayFinalOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getIsPayFinal(pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping(value = "/order/isSuccess")
    public PageInfo<SaleOrder> isSuccessOrder(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        PageInfo<SaleOrder> pageInfo = saleService.getIsSuccess(pageNum, pageSize);
        return pageInfo;
    }

}
