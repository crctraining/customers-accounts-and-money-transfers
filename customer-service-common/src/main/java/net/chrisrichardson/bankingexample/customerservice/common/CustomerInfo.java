package net.chrisrichardson.bankingexample.customerservice.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class CustomerInfo {

  private Name name;

  private String phoneNumber;

  private Address address;

  private String ssn;

  public CustomerInfo() {
  }

  public CustomerInfo(Name name, String phoneNumber, Address address, String ssn) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.ssn = ssn;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }
}
