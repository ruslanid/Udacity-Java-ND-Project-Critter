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
public class PetService {

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private CustomerRepository customerRepository;

  public Pet savePet(Pet pet, Long customerId) {
    Pet savedPet = petRepository.save(pet);
    Customer customer = getCustomer(customerId);
    customer.addPet(savedPet);
    customerRepository.save(customer);
    return savedPet;
  }

  public Pet getPetById(Long id) {
    Optional<Pet> result = petRepository.findById(id);
    if (result.isEmpty()) {
      throw new ResourceNotFoundException("Pet with id " + id +  " not found");
    }
    return result.get();
  }

  public List<Pet> getAllPets() {
    return petRepository.findAll();
  }

  public List<Pet> getPetsByCustomer(Long customerId) {
    Customer customer = getCustomer(customerId);
    return petRepository.findPetsByCustomer(customer);
  }

  private Customer getCustomer(Long customerId) {
    Optional<Customer> result = customerRepository.findById(customerId);
    if (result.isEmpty()) {
      throw new ResourceNotFoundException("Customer with id " + customerId + " not found");
    }
    return result.get();
  }

}
