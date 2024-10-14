package com.rupesh.assesment.carlease.run;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarEntity {

  @Override
  public int hashCode() {
    return Objects.hash(carModel, co2, doors, grossPrice, id, maker, mileage, nettPrice, version);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CarEntity other = (CarEntity) obj;
    return Objects.equals(carModel, other.carModel) && Objects.equals(co2, other.co2)
        && doors == other.doors
        && Double.doubleToLongBits(grossPrice) == Double.doubleToLongBits(other.grossPrice)
        && Objects.equals(id, other.id) && Objects.equals(maker, other.maker)
        && Double.doubleToLongBits(mileage) == Double.doubleToLongBits(other.mileage)
        && Double.doubleToLongBits(nettPrice) == Double.doubleToLongBits(other.nettPrice)
        && Objects.equals(version, other.version);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private double mileage;
  private double nettPrice;

  private String maker;
  private String carModel;
  private String version;
  private int doors;
  private String co2;
  private double grossPrice;

  public CarEntity() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public double getMileage() {
    return mileage;
  }

  public void setMileage(double mileage) {
    this.mileage = mileage;
  }

  public double getNettPrice() {
    return nettPrice;
  }

  public void setNettPrice(double nettPrice) {
    this.nettPrice = nettPrice;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int getDoors() {
    return doors;
  }

  public void setDoors(int doors) {
    this.doors = doors;
  }

  public String getCo2() {
    return co2;
  }

  public void setCo2(String co2) {
    this.co2 = co2;
  }

  public double getGrossPrice() {
    return grossPrice;
  }

  public void setGrossPrice(double grossPrice) {
    this.grossPrice = grossPrice;
  }

  @Override
  public String toString() {
    return "CarEn [id=" + id + ", mileage=" + mileage + ", nettPrice=" + nettPrice + ", maker="
        + maker + ", carModel=" + carModel + ", version=" + version + ", doors=" + doors + ", co2="
        + co2 + ", grossPrice=" + grossPrice + "]";
  }

  public CarEntity(Integer id, double mileage, double nettPrice, String maker, String carModel,
      String version, int doors, String co2, double grossPrice) {
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

}
