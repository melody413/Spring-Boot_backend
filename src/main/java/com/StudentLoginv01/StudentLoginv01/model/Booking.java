package com.StudentLoginv01.StudentLoginv01.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bookings")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "accepted")
  public Boolean accepted = false;

  @Column(name = "stripeSessionId")
  public String stripeSessionId;

  @Column(name = "paymentStatus")
  public String paymentStatus;

  @Basic(optional = false)
  @Column(name = "bookingDate")
  public Date  bookingDate;

  @Basic(optional = false)
  @Column(name = "bookingFrom")
  public String  bookingFrom;

  @Basic(optional = false)
  @Column(name = "bookingTo")
  public String  bookingTo;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "studentId")
  public User student;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "bookings")
  public User tutor;

  @CreationTimestamp
  public LocalDateTime createdAt;
  
  @UpdateTimestamp
  public LocalDateTime updatedAt;

  @Column(name = "sessionId",length = 2000)
  public String  sessionId;

  @Column(name = "tokenId",length = 2000)
  public String  tokenId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public LocalDateTime getCreatedAt() {
	  return createdAt;
  }
  
  public LocalDateTime getUpdatedAt() {
	  return updatedAt;
  }

  public Date getBookingDate() {
    return this.bookingDate;
  }

  public void setBookingDate(Date bookingDate) {
    this.bookingDate = bookingDate;
  }

  public User getStudent() {
    return this.student;
  }

  public void setStudent(User student) {
    this.student = student;
  }

  public User getTutor() {
    return this.tutor;
  }

  public void setTutor(User tutor) {
    this.tutor = tutor;
  }

  public Boolean isAccepted() {
    return this.accepted;
  }

  public Boolean getAccepted() {
    return this.accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }

  public String getStripeSessionId() {
    return this.stripeSessionId;
  }

  public void setStripeSessionId(String stripeSessionId) {
    this.stripeSessionId = stripeSessionId;
  }

  public String getPaymentStatus() {
    return this.paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }
  
  public String getBookingFrom() {
    return this.bookingFrom;
  }

  public void setBookingFrom(String bookingFrom) {
    this.bookingFrom = bookingFrom;
  }

  public String getBookingTo() {
    return this.bookingTo;
  }

  public void setBookingTo(String bookingTo) {
    this.bookingTo = bookingTo;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }
}
