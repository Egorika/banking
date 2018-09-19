package blog.service;

import blog.model.User;
import blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
