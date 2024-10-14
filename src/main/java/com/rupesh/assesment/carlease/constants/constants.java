package com.rupesh.assesment.carlease.constants;

/**
 * 
 * @author Rupesh The constant file is used to store the values which are used across the
 *         application.
 */
public class constants {

  // In case the success message needs to be populated
  public static final String SUCCESS = "success";

  // In case the car or customer is not found while making reservation
  public static final String NO_CAR_OR_CUSTOMER_FOUND = "NO_CAR_OR_CUST_FOUND";

  // In case the end date is before the start date while making reservation
  public static final String END_DATE_BEFORE = "End date cannot be before start date";

  // In case the car requested to be reserved is already booked
  public static final String CAR_UNAVAILABLE = "Car is already booked for the selected dates.";
}
