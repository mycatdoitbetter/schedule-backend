package com.mycatdoitbetter.schedule.models;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

  @Id // indicates a primary key
  @GeneratedValue(strategy = GenerationType.AUTO) // indicates a autoincremet field
  private long id; // declaring the field name

  @Column(name = "first_name") // Column name, and bellow, the field
  private String first;

  @Column(name = "last_name")
  private String last;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private long phone;

  @Column(name = "hobby")
  private String hobby;

  // The contructor default method
  public Contact() {
  }

  public Contact(String first, String last, String email, long phone, String hobby) {
    this.first = first;
    this.last = last;
    this.email = email;
    this.phone = phone;
    this.hobby = hobby;
  }

  // Access methods
  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public long getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getHobby() {
    return hobby;
  }

  // Set methods
  public void setFirst(String first) {
    this.first = first;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

}