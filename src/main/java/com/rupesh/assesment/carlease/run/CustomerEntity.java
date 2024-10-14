package com.rupesh.assesment.carlease.run;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class CustomerEntity {

  public CustomerEntity(Integer id, String name, String street, String house_No, String place,
      @Email String email, @Pattern(regexp = "(^$|[0-9]{10})") String ph_Number) {
    super();
    this.id = id;
    this.name = name;
    this.street = street;
    this.house_No = house_No;
    this.place = place;
    this.email = email;
    this.ph_Number = ph_Number;
  }

  public CustomerEntity() {}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String street;
  private String house_No;
  private String place;
  @Email
  private String email;
  @Pattern(regexp = "(^$|[0-9]{10})")
  private String ph_Number;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouse_No() {
    return house_No;
  }

  public void setHouse_No(String house_No) {
    this.house_No = house_No;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPh_Number() {
    return ph_Number;
  }

  public void setPh_Number(String ph_Number) {
    this.ph_Number = ph_Number;
  }

  @Override
  public String toString() {
    return "CustomerEntity [id=" + id + ", name=" + name + ", street=" + street + ", house_No="
        + house_No + ", place=" + place + ", email=" + email + ", ph_Number=" + ph_Number + "]";
  }
  
  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CustomerEntity customer = (CustomerEntity) o;
      return Objects.equals(id, customer.id) &&
             Objects.equals(name, customer.name) &&
             Objects.equals(street, customer.street) &&
             Objects.equals(house_No, customer.house_No) &&
             Objects.equals(place, customer.place) &&
             Objects.equals(email, customer.email) &&
             Objects.equals(ph_Number, customer.ph_Number);
  }

  @Override
  public int hashCode() {
      return Objects.hash(id, name, street, house_No, place, email, ph_Number);
  }

}
