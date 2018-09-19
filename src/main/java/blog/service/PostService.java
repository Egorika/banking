package blog.service;

import blog.model.Post;
import blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElse(null);
	}
	
	public List<Post> findTop5OrderByDate() {
		return postRepository.findTop5OrderByDateDesc();
	}
	
	public List<Post> findTop10OrderByDate() {
		return postRepository.findTop10OrderByDateDesc();
	}
	
	public List<Post> getAllOrderByDateDesc() {
		return postRepository.getAllOrderByDateDesc();
	}
	
	public void save(Post post) {
		postRepository.save(post);
	}
	
	public void delete(Post post) {
		postRepository.delete(post);
	}
}
