package com.StudentLoginv01.StudentLoginv01.repository;

import com.StudentLoginv01.StudentLoginv01.model.Booking;
import java.util.List;

import com.StudentLoginv01.StudentLoginv01.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findAllByStudent_IdOrderByCreatedAtDesc(Long id);

	List<Booking> findAllByTutor_IdOrderByCreatedAtDesc(Long id);

	List<Booking> findAllByAccepted(boolean accepted);

	Long deleteByTutor(User tutor);
}
