package com.StudentLoginv01.StudentLoginv01.payload;

import lombok.Data;

@Data
public class BookingRequestDto {
	private Long bookingId;
	private String bookingDate;
	private String bookingFrom;
	private String bookingTo;
	private Long studentId;
	private Long tutorId;
	private Boolean accept;

	public Boolean isAccept() {
		return this.accept;
	}

	public Boolean getAccept() {
		return this.accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public String getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Long getBookingId() {
		return this.bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getTutorId() {
		return this.tutorId;
	}

	public void setTutorId(Long tutorId) {
		this.tutorId = tutorId;
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


}