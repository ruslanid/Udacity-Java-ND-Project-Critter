package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.model.customer.Customer;
import com.udacity.jdnd.course3.critter.model.employee.Employee;
import com.udacity.jdnd.course3.critter.model.pet.Pet;
import com.udacity.jdnd.course3.critter.model.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private PetRepository petRepository;

  @Autowired
  private CustomerRepository customerRepository;

  public Schedule saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
    List<Employee> employees = employeeRepository.findAllById(employeeIds);
    List<Pet> pets = petRepository.findAllById(petIds);
    schedule.setEmployees(employees);
    schedule.setPets(pets);
    return scheduleRepository.save(schedule);
  }

  public List<Schedule> getSchedules() {
    return scheduleRepository.findAll();
  }

  public List<Schedule> getSchedulesByPet(Long petId) {
    Optional<Pet> petResult = petRepository.findById(petId);
    if (petResult.isEmpty()) {
      throw new ResourceNotFoundException("Pet with id " + petId + " not found.");
    }
    return scheduleRepository.findAllByPets(petResult.get());
  }

  public List<Schedule> getSchedulesByEmployee(Long employeeId) {
    Optional<Employee> employeeResult = employeeRepository.findById(employeeId);
    if (employeeResult.isEmpty()) {
      throw new ResourceNotFoundException("Employee with id " + employeeId + " not found.");
    }
    return scheduleRepository.findAllByEmployees(employeeResult.get());
  }

  public List<Schedule> getSchedulesByCustomer(Long customerId) {
    Optional<Customer> customerResult = customerRepository.findById(customerId);
    if (customerResult.isEmpty()) {
      throw new ResourceNotFoundException("Customer with id " + customerId + " not found.");
    }

    return scheduleRepository.findAllByCustomerId(customerId);
  }

}
