package blog.service;

import blog.model.Payment;
import blog.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public List<Payment> findTop10ByFavouriteIsTrue() {
		return paymentRepository.findTop10ByFavouriteIsTrue();
	}
}
