package blog.service;

import blog.model.Credit;
import blog.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

	@Autowired
	private CreditRepository creditRepository;

	public List<Credit> findAll() {
		return creditRepository.findAll();
	}
}
