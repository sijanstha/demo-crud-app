package com.demo.controller;

import java.util.List;

import com.demo.DemoProjectApplication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.model.Customer;
import com.demo.repo.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final static Logger LOG =
            Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;
	
    @GetMapping(value = "")
    public List<Customer> showAllCustomers() { 
        return customerRepository.findAll(); // select * from tbl_customers;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> processCustomerData(@RequestBody Customer customer) {
    	
    	if(customer.getName() == null || customer.getName().isEmpty()){
    	    LOG.error("Name Not provided. Name empty");
            return new ResponseEntity<>("Name is necessary", HttpStatus.FORBIDDEN);
        }
    	
        customerRepository.save(customer); // insert into tbl_customer()v/...
        return new ResponseEntity<>(customer, HttpStatus.CREATED); // error server httpstatus 200 201 400 404 500
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> showSpecificCustomerDetail(@PathVariable int id) {
        Customer customerDetails = customerRepository.findById(id).get(); // select * from tbl_customer where id = :id
        
        if(customerDetails == null)
        	return new ResponseEntity<>("No customer found at id: " + id, HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(customerDetails, HttpStatus.OK);
    }
    
    @PutMapping(value = "")
    public ResponseEntity<?> editCustomerDetails(@RequestBody Customer customer) {
    	Customer existingCustomer = customerRepository.findById(customer.getId()).get();
        
        if(existingCustomer == null)
        	return new ResponseEntity<>("No customer found at id: " + customer.getId(), HttpStatus.NOT_FOUND);
        
        
       // update
        existingCustomer.setName(customer.getName());
        existingCustomer.setAddress(customer.getAddress());
        
        customerRepository.save(existingCustomer); // update
        
        return new ResponseEntity<>(existingCustomer, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> processDeleteCustomer(@PathVariable int id){
        Customer customerDetails = customerRepository.findById(id).get();
        
        if(customerDetails == null)
        	return new ResponseEntity<>("No customer found at id: " + id, HttpStatus.NOT_FOUND);
        
        customerRepository.deleteById(id); // delete from tbl_customer where id = :id
        return new ResponseEntity<>("Customer deleted!", HttpStatus.OK); 
    }
    
}