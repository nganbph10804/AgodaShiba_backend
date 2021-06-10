package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query(value = "select * from bill where phone=?1", nativeQuery = true)
    List<Bill> getBillByPhone_user(String phone);
}