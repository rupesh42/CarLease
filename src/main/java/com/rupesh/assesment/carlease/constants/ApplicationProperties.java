package com.rupesh.assesment.carlease.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author Rupesh This file helps to fetch data from .properties file for the values which may or
 *         may not change in the future. As of now only InterestRate is applied to this file.
 */
@ConfigurationProperties(prefix = "api")
public class ApplicationProperties {

  private double interestRate;

  public double getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

}
