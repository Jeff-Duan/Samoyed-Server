package com.cherish.demo.dao;

import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceDao {

    void insertPayRecord(PayRecord payRecord);

    void insertReceivableRecord(ReceivableRecord receivableRecord);

}
