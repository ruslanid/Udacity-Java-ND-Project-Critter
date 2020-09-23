package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.model.customer.Customer;
import com.udacity.jdnd.course3.critter.model.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PetRepository petRepository;

  public Customer saveCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerByPet(Long petId) {
    Optional<Pet> petResult = petRepository.findById(petId);
    if (petResult.isEmpty()) {
      throw new ResourceNotFoundException("Pet with id " + petId + " not found.");
    }

    Optional<Customer> customerResult = customerRepository.findCustomerByPets(petResult.get());
    if (customerResult.isEmpty()) {
      throw new ResourceNotFoundException("Customer for pet id " + petId + " not found.");
    }

    return customerResult.get();
  }

}
