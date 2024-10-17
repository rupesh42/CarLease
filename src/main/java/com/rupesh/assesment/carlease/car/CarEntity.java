package com.rupesh.assesment.carlease.car;

import java.math.BigDecimal;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * CarEntity represents a car in the system. This class is mapped to a database table using JPA
 * annotations.
 * 
 * <p>
 * Examples of usage:
 * </p>
 * 
 * <pre>
 * // Create a new car
 * CarEntity car = new CarEntity();
 * car.setMake("Toyota");
 * car.setModel("Corolla");
 * </pre>
 * 
 * @see javax.persistence.Entity
 * 
 * 
 * @author rupesh
 */

@Entity
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private double mileage;
  private BigDecimal nettPrice;

  private String maker;
  private String carModel;
  private String version;
  private Integer doors;
  private String co2;
  private BigDecimal grossPrice;

  /**
   * Constructor with all fields.
   * 
   * @param id the ID of the car
   * @param mileage the mileage of the car
   * @param nettPrice the net price of the car
   * @param maker the maker of the car
   * @param carModel the model of the car
   * @param version the version of the car
   * @param doors the number of doors
   * @param co2 the CO2 emissions
   * @param grossPrice the gross price
   */

  public CarEntity(Integer id, double mileage, BigDecimal nettPrice, String maker, String carModel,
      String version, Integer doors, String co2, BigDecimal grossPrice) {
    super();
    this.id = id;
    this.mileage = mileage;
    this.nettPrice = nettPrice;
    this.maker = maker;
    this.carModel = carModel;
    this.version = version;
    this.doors = doors;
    this.co2 = co2;
    this.grossPrice = grossPrice;
  }

  public CarEntity() {}

  // getters and setter
  /**
   * Gets the ID of the car.
   * 
   * @return the ID of the car
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the ID of the car.
   * 
   * @param id the new ID of the car
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the mileage of the car.
   * 
   * @return the mileage of the car
   */
  public double getMileage() {
    return mileage;
  }

  /**
   * Sets the mileage of the car.
   * 
   * @param mileage the new mileage of the car
   */
  public void setMileage(double mileage) {
    this.mileage = mileage;
  }

  /**
   * Gets the net price of the car.
   * 
   * @return the net price of the car
   */
  public BigDecimal getNettPrice() {
    return nettPrice;
  }

  /**
   * Sets the net price of the car.
   * 
   * @param nettPrice the new net price of the car
   */
  public void setNettPrice(BigDecimal nettPrice) {
    this.nettPrice = nettPrice;
  }

  /**
   * Gets the maker of the car.
   * 
   * @return the maker of the car
   */
  public String getMaker() {
    return maker;
  }

  /**
   * Sets the maker of the car.
   * 
   * @param maker the new maker of the car
   */
  public void setMaker(String maker) {
    this.maker = maker;
  }

  /**
   * Gets the model of the car.
   * 
   * @return the model of the car
   */
  public String getCarModel() {
    return carModel;
  }

  /**
   * Sets the model of the car.
   * 
   * @param carModel the new model of the car
   */
  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }

  /**
   * Gets the version of the car.
   * 
   * @return the version of the car
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the version of the car.
   * 
   * @param version the new version of the car
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * Gets the number of doors of the car.
   * 
   * @return the number of doors
   */
  public Integer getDoors() {
    return doors;
  }

  /**
   * Sets the number of doors of the car.
   * 
   * @param doors the new number of doors
   */
  public void setDoors(Integer doors) {
    this.doors = doors;
  }

  /**
   * Gets the CO2 emissions of the car.
   * 
   * @return the CO2 emissions
   */
  public String getCo2() {
    return co2;
  }

  /**
   * Sets the CO2 emissions of the car.
   * 
   * @param co2 the new CO2 emissions
   */
  public void setCo2(String co2) {
    this.co2 = co2;
  }

  /**
   * Gets the gross price of the car.
   * 
   * @return the gross price
   */
  public BigDecimal getGrossPrice() {
    return grossPrice;
  }

  /**
   * Sets the gross price of the car.
   * 
   * @param grossPrice the new gross price
   */
  public void setGrossPrice(BigDecimal grossPrice) {
    this.grossPrice = grossPrice;
  }

  /**
   * to convert String
   * 
   */

  @Override
  public String toString() {
    return "CarEn [id=" + id + ", mileage=" + mileage + ", nettPrice=" + nettPrice + ", maker="
        + maker + ", carModel=" + carModel + ", version=" + version + ", doors=" + doors + ", co2="
        + co2 + ", grossPrice=" + grossPrice + "]";
  }


  @Override
  public int hashCode() {
    return Objects.hash(id, mileage, nettPrice, maker, carModel, version, doors, co2, grossPrice);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CarEntity)) {
      return false;
    }
    CarEntity other = (CarEntity) obj;
    return Objects.equals(carModel, other.carModel) && Objects.equals(co2, other.co2)
        && doors == other.doors && Objects.equals(grossPrice, other.grossPrice)
        && Objects.equals(id, other.id) && Objects.equals(maker, other.maker)
        && Double.doubleToLongBits(mileage) == Double.doubleToLongBits(other.mileage)
        && Objects.equals(nettPrice, other.nettPrice) && Objects.equals(version, other.version);
  }



}
