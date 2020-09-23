package com.udacity.jdnd.course3.critter.model.customer;

import com.udacity.jdnd.course3.critter.model.pet.Pet;
import com.udacity.jdnd.course3.critter.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {

  private String phoneNumber;

  private String notes;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.PERSIST)
  private List<Pet> pets = new ArrayList<>();

  public void addPet(Pet pet) {
    pet.setCustomer(this);
    pets.add(pet);
  }

}
