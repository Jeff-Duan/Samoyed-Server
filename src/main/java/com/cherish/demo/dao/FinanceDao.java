package com.cherish.demo.dao;

import com.cherish.demo.entity.finance.PayRecord;
import com.cherish.demo.entity.finance.ReceivableRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceDao {

    void insertPayRecord(PayRecord payRecord);

    void insertReceivableRecord(ReceivableRecord receivableRecord);

    List<PayRecord> selectPayRecordByOrderNumber(String orderNumber);

    List<PayRecord> selectPayRecordByDate(String recordInsertTime);

    List<ReceivableRecord> selectReceivableRecordByOrderNumber(String orderNumber);

    List<ReceivableRecord> selectReceivableRecordByDate(String recordInsertTime);

}
