package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.model.employee.Employee;
import com.udacity.jdnd.course3.critter.model.employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee saveEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  public Employee getEmployeeById(Long id) {
    return getEmployee(id);
  }

  public void setEmployeeAvailability(Long id, Set<DayOfWeek> daysAvailable) {
    Employee employee = getEmployee(id);
    employee.setDaysAvailable(daysAvailable);
  }

  public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    List<Employee> employees = employeeRepository.findAll();
    Predicate<Employee> bySkillsAndDay =
        employee -> employee.getDaysAvailable().contains(dayOfWeek) && employee.getSkills().containsAll(skills);

    return employees.stream().filter(bySkillsAndDay).collect(Collectors.toList());
  }

  private Employee getEmployee(Long id) {
    Optional<Employee> result = employeeRepository.findById(id);
    if (result.isEmpty()) {
      throw new ResourceNotFoundException("Employee with id " + id + " not found.");
    }
    return result.get();
  }

}
