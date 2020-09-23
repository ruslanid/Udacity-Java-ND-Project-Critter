package com.udacity.jdnd.course3.critter.model.employee;

import com.udacity.jdnd.course3.critter.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee extends User {

  @ElementCollection
  private Set<EmployeeSkill> skills;

  @ElementCollection
  private Set<DayOfWeek> daysAvailable;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "schedule_employee",
      joinColumns = { @JoinColumn(name = "employee_id") },
      inverseJoinColumns = { @JoinColumn(name = "schedule_id") }
  )
  private List<Employee> schedules;

}
