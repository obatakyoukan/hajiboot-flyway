package com.example.api;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
 @Autowired
 CustomerService customerService;

 /*
 @GetMapping
 List<Customer> getCustomers() {
  return customerService.findAll();
 }
 */
 
 
 @GetMapping
 Page<Customer> getCustomers( Pageable pageable ) {
  return customerService.findAll(pageable);
 }

 @GetMapping(path = "{id}" )
 Customer getCustomer(@PathVariable Integer id) {
  Customer customer = customerService.findOne(id);
  return customer;
 }

 /*
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 Customer postCustomers(@RequestBody Customer customer ) {
  return customerService.create(customer);
 }*/
 @PostMapping
 ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder ) {
  Customer created = customerService.create(customer);
  URI location = uriBuilder.path("api/customer/{id}")
   	.buildAndExpand(created.getId() ).toUri();
  return ResponseEntity.created(location).body(created);
 }

 @PutMapping(path = "{id}" )
 Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer ) {
  customer.setId(id);
  return customerService.create(customer);
 }

 @DeleteMapping(path = "{id}" )
 @ResponseStatus(HttpStatus.NO_CONTENT)
 void deleteCustomer(@PathVariable Integer id ) {
  customerService.delete(id);
 }
}
