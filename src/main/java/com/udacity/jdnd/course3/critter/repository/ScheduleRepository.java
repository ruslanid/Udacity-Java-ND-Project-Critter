package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.model.employee.Employee;
import com.udacity.jdnd.course3.critter.model.pet.Pet;
import com.udacity.jdnd.course3.critter.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

  List<Schedule> findAllByPets(Pet pet);

  List<Schedule> findAllByEmployees(Employee employee);

  @Query("SELECT s FROM Schedule AS s JOIN s.pets AS p JOIN p.customer AS c WHERE c.id = :customerId")
  List<Schedule> findAllByCustomerId(Long customerId);

}
