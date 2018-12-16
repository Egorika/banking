package blog.service;

import blog.model.Card;
import blog.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	public List<Card> findAll() {
		return cardRepository.findAll();
	}

	public Card findById(Integer id) {
		return cardRepository.findById(id).orElse(null);
	}
}
