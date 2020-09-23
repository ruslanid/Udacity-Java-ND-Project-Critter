package com.udacity.jdnd.course3.critter.model.pet;

import com.udacity.jdnd.course3.critter.model.customer.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pet {

  @Id
  @GeneratedValue
  private Long id;

  private PetType type;

  @Nationalized
  private String name;

  @ManyToOne
  private Customer customer;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "schedule_pet",
      joinColumns = { @JoinColumn(name = "pet_id") },
      inverseJoinColumns = { @JoinColumn(name="schedule_id") }
  )
  private List<Pet> schedules;

  private LocalDate birthDate;

  private String notes;

}
