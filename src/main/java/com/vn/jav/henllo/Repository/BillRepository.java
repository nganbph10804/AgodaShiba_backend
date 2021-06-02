package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
