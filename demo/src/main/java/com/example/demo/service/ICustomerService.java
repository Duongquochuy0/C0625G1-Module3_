package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;

import java.util.List;

public interface ICustomerService {
    boolean createCustomer(Customer customer);

    Customer getCustomerById(int id);

    Customer getCustomerByCitizenNumber(String citizenNumber);

    Customer findByPhoneOrCCCD(String value);

    List<Customer> getAllCustomers();
    Customer findById(int id);
}
