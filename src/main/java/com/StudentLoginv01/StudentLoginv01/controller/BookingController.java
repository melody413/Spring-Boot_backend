package com.StudentLoginv01.StudentLoginv01.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.StudentLoginv01.StudentLoginv01.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.StudentLoginv01.StudentLoginv01.model.Booking;
import com.StudentLoginv01.StudentLoginv01.model.User;
import com.StudentLoginv01.StudentLoginv01.repository.BookingRepository;
import com.StudentLoginv01.StudentLoginv01.repository.UserRepository;
import com.StudentLoginv01.StudentLoginv01.payload.*;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "*")
public class BookingController {

	//TODO:
	private static String FRONTEND_URL = "http://localhost:4200";
	private static String REDIRECT_URL = "http://localhost:8585/api/booking/update-checkout-session/";
	private static String STRIPE_KEY = "sk_test_51ID6OKBZahmyM2Zlh8omTrB7mZ7FghTr3XouGcKdtODhrqZ31TTmkWMC4XhYcNtbK94hgh8ubPrno5A6qA78Rjxg00aRHLw5ba";
	private static String STUDENT = "student";
	private static String TEACHER = "teacher";

	@PostConstruct
    public void init() {
        Stripe.apiKey = STRIPE_KEY;
    }
	
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VideoService videoService;

	@GetMapping("/bookinglist")
	public ResponseEntity<Object> getAllBooking() {
		List<Booking> user = bookingRepository.findAll();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@GetMapping("/transactionlist")
	public ResponseEntity<Object> getTransactionList() {
		List<Booking> user = bookingRepository.findAllByAccepted(true);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@GetMapping("/myRequests/{id}")
	public ResponseEntity<Object> myRequests(HttpServletRequest request, @PathVariable Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isPresent()){
			User user = userOpt.get();
			List<Booking> booking = new ArrayList<>();

			if(user.role.equals(TEACHER)){
				booking = bookingRepository.findAllByTutor_IdOrderByCreatedAtDesc(user.getId());
			}else if(user.role.equals(STUDENT)){
				booking = bookingRepository.findAllByStudent_IdOrderByCreatedAtDesc(user.getId());
			}
			return new ResponseEntity<>(booking, HttpStatus.OK);
		}

		return new ResponseEntity<>("Record Not found.", HttpStatus.OK);
	}
	@PostMapping("/bookNow")
	public ResponseEntity<Object> bookNow(HttpServletRequest request, @RequestBody BookingRequestDto requestDto) {
		try{
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy;HH:mm:ss");
			
			//SERVICE
			Optional<User> tutor = userRepository.findById(requestDto.getTutorId());
			if(user == null){
				Optional<User> student = userRepository.findById(requestDto.getStudentId());
				user = student.get();
			}

			Booking booking = new Booking();
			booking.setBookingDate(sf.parse(requestDto.getBookingDate()));
			booking.setBookingFrom(requestDto.getBookingFrom());
			booking.setBookingTo(requestDto.getBookingTo());
			booking.setStudent(user);
			booking.setTutor(tutor.get());
			bookingRepository.save(booking);

			return new ResponseEntity<>(booking, HttpStatus.OK);
		} catch (Exception e) {
			// log the error message and return an empty list
			String message = "Record Not Deleted.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping("/bookingAction")
	public ResponseEntity<Object> bookingAction(HttpServletRequest request, @RequestBody BookingRequestDto requestDto) {
		try{
			Optional<Booking> bookingOpt = bookingRepository.findById(requestDto.getBookingId());

			if(bookingOpt.isPresent()){
				Booking booking = bookingOpt.get();

				booking.setAccepted(requestDto.isAccept());
				bookingRepository.save(booking);

				return new ResponseEntity<>(booking, HttpStatus.OK);
			}else{
				String message = "Record Not found.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
				// log the error message and return an empty list
				String message = "Record Not found. IN EXCEPTION";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
			
	}


	@GetMapping("/update-checkout-session/{bookingId}/{status}")
	public ResponseEntity<Object>  updateCheckoutSession(@PathVariable Long bookingId,@PathVariable String status, Model model) {
		try {
			final String REDIRECT = FRONTEND_URL+"/myRequests";

			Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
			if(bookingOpt.isPresent()){
				Booking booking = bookingOpt.get();
				String stripeSessionId = booking.getStripeSessionId();
				if(stripeSessionId != null && !stripeSessionId.isBlank()){
					Session session = Session.retrieve(stripeSessionId);
					String paymentStatus = session.getStatus();

					booking.setPaymentStatus(paymentStatus);
					booking.setSessionId(videoService.createSession());
					booking.setTokenId(videoService.generateToken(booking.getSessionId()));
					bookingRepository.save(booking);
					return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(REDIRECT+"?paymentStatus="+(paymentStatus.equals("complete")?"success":"failed"))).build();
				}else{
					booking.setPaymentStatus(status);
					bookingRepository.save(booking);
					return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(REDIRECT+"?paymentStatus=failed")).build();
				}
			}else{
				String message = "Record Not found.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			String message = "EXCEPTION";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create-checkout-session/{bookingId}")
	public ResponseEntity<Object>  createCheckoutSession(@PathVariable Long bookingId, Model model) {
		try {
			Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
			if(bookingOpt.isPresent()){
				Booking booking = bookingOpt.get();
				
				SessionCreateParams params =
				SessionCreateParams.builder()
					.setMode(SessionCreateParams.Mode.PAYMENT)
					.setSuccessUrl(REDIRECT_URL+bookingId+"/success")
					.setCancelUrl(REDIRECT_URL+bookingId+"/failed")
					.addLineItem(
						SessionCreateParams.LineItem.builder()
							.setQuantity(1L)
							// Provide the exact Price ID (for example, pr_1234) of the product you want to sell
							.setPriceData(
								SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("inr")
									.setUnitAmount(booking.getTutor().getRate().longValue()*100)
									.setProductData(
									SessionCreateParams.LineItem.PriceData.ProductData.builder()
										.setName("BOOKING SESSION-"+(new Date().getTime())+"-"+booking.getId())
										.setDescription("Tution")
									.build())
								.build())
							//.setPrice("price_1MnHfJBZahmyM2ZlnWsGAvWa")
							.build())
					.build();
				Session session = Session.create(params);
				booking.setStripeSessionId(session.getId());
				bookingRepository.save(booking);

				return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(session.getUrl())).build();
			}else{
				String message = "Record Not found.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String message = "EXCEPTION";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/myRequests/booking/{id}")
	public ResponseEntity<Object> getById(HttpServletRequest request, @PathVariable Long id) {

		Optional<Booking> booking = bookingRepository.findById(id);
		if (booking.isPresent()){
			return new ResponseEntity<>(booking.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Record Not found.", HttpStatus.OK);
	}
}
