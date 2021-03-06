package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query(value = "select * from bill where phone=?1", nativeQuery = true)
    List<Bill> getBillByPhone_user(String phone);

    @Modifying
    @Transactional
    @Query(value = "update bill set confirm=1 where bill_id=?1",nativeQuery = true)
    void updateConfirmBill(int id);
}