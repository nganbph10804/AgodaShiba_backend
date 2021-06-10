package com.vn.jav.henllo.Service;


import com.vn.jav.henllo.Model.Bill;
import com.vn.jav.henllo.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRes;
    @Autowired
    public BillService(BillRepository billRes){
        this.billRes=billRes;
    }


    public List<Bill> getAllBill(){
        return billRes.findAll();
    }

    public Bill addBill(@RequestBody Bill bill){
        return billRes.save(bill);
    }

    public List<Bill> getBillByUser(@PathVariable String phone){
        return billRes.getBillByPhone_user(phone);
    }
}
