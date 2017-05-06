package model;

import java.util.*;

public class Publisher {
  private String name;
  private ArrayList<String> addresses ;
  private ArrayList<String> phoneNumbers ;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(ArrayList<String> addresses) {
    this.addresses = addresses;
  }

  public ArrayList<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  
  public Publisher(String name){
    this.name = name;
    addresses = new ArrayList<String>();
    phoneNumbers = new ArrayList<String>();
  }

  public void addPhoneNumber(String phone){
    phoneNumbers.add(phone);
  }
  public void addAddress(String address){
    addresses.add(address);
  }
}
