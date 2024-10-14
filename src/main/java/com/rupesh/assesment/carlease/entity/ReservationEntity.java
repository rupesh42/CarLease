package com.rupesh.assesment.carlease.run;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

@Entity
public class ReservationEntity {

  /**
   * The unique identifier for the rental record (auto-generated).
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer custId;

  private Integer carId;

  /**
   * The start date of the rental (can be present or future). Formatted as "yyyy-MM-dd".
   */
  @FutureOrPresent
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;


  /**
   * The end date of the rental (can be present or future). Formatted as "yyyy-MM-dd".
   */
  @Future
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;


  /**
   * The booking date of the rental. Formatted as "yyyy-MM-dd".
   */

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date bookingDate;


  /**
   * The total bill amount for the rental in EUR.
   */
  private double totalBill;

  private Integer duration;

  @Override
  public String toString() {
    return "ReservationEntity [rId=" + id + ", custId=" + custId + ", carId=" + carId
        + ", startDate=" + startDate + ", endDate=" + endDate + ", bookingDate=" + bookingDate
        + ", totalBill=" + totalBill + ", duration=" + duration + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookingDate, carId, custId, duration, endDate, id, startDate, totalBill);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ReservationEntity)) {
      return false;
    }
    ReservationEntity other = (ReservationEntity) obj;
    return Objects.equals(bookingDate, other.bookingDate) && Objects.equals(carId, other.carId)
        && Objects.equals(custId, other.custId) && Objects.equals(duration, other.duration)
        && Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
        && Objects.equals(startDate, other.startDate)
        && Double.doubleToLongBits(totalBill) == Double.doubleToLongBits(other.totalBill);
  }

  public ReservationEntity(Integer rId, Integer custId, Integer carId,
      @FutureOrPresent Date startDate, @Future Date endDate, Date bookingDate, double totalBill,
      Integer duration) {
    super();
    this.id = rId;
    this.custId = custId;
    this.carId = carId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.bookingDate = bookingDate;
    this.totalBill = totalBill;
    this.duration = duration;
  }

  public ReservationEntity() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCustId() {
    return custId;
  }

  public void setCustId(Integer custId) {
    this.custId = custId;
  }

  public Integer getCarId() {
    return carId;
  }

  public void setCarId(Integer carId) {
    this.carId = carId;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
  }

  public double getTotalBill() {
    return totalBill;
  }

  public void setTotalBill(double totalBill) {
    this.totalBill = totalBill;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }


}
