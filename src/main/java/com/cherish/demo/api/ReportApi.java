package com.cherish.demo.api;

import com.cherish.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportApi {

    @Autowired
    ReportService reportService;

    @GetMapping("/purchase/number")
    public List<Map<String, Object>> purchaseNumber(){
        return reportService.purchaseNumber();
    }

    @GetMapping("/produce/number")
    public List<Map<String, Object>> produceNumber(){
        return reportService.produceNumber();
    }

    @GetMapping("/sale/number")
    public List<Map<String, Object>> saleNumber(){
        return reportService.saleNumber();
    }

    @GetMapping("/finance/number")
    public Map<String, Object> financeNumber(){
        return reportService.financeNumber();
    }

    @GetMapping("/warehouse/number")
    public Map<String, List> warehouseNumber(){
        return reportService.warehouseNumber();
    }

}
