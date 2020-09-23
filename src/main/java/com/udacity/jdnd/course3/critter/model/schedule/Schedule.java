package com.udacity.jdnd.course3.critter.model.schedule;

import com.udacity.jdnd.course3.critter.model.employee.Employee;
import com.udacity.jdnd.course3.critter.model.employee.EmployeeSkill;
import com.udacity.jdnd.course3.critter.model.pet.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "schedule_employee",
      joinColumns = { @JoinColumn(name = "schedule_id") },
      inverseJoinColumns = { @JoinColumn(name = "employee_id") }
  )
  private List<Employee> employees;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "schedule_pet",
      joinColumns = { @JoinColumn(name = "schedule_id") },
      inverseJoinColumns = { @JoinColumn(name="pet_id") }
  )
  private List<Pet> pets;

  private LocalDate date;

  @ElementCollection
  private Set<EmployeeSkill> activities;

}
