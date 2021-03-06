package com.example.service;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class CustomerService {
 @Autowired
 CustomerRepository customerRepository;
 
 public List<Customer> findAll() {
  return customerRepository.findAllOrderByName();
 }

 public Page<Customer> findAll(Pageable pageable) {
  return customerRepository.findAllOrderByName(pageable);
 }

 public Customer findOne(Integer id){
  return customerRepository.findById(id).get();
  //return customerRepository.getOne(id);
 }

 public Customer create(Customer customer) {
  return customerRepository.save(customer);
 }
 public Customer update(Customer customer) {
  return customerRepository.save(customer);
 }
 
 public void delete(Integer id){
  customerRepository.deleteById(id);
 }


}
