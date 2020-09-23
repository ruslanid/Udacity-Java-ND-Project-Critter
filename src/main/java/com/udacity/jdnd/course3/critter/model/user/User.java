package com.udacity.jdnd.course3.critter.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Nationalized
  private String name;
}
