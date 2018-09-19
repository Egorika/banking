package blog.repository;

import blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "SELECT * FROM post ORDER BY date DESC LIMIT 5", nativeQuery = true)
	List<Post> findTop5OrderByDateDesc();
	
	@Query(value = "SELECT * FROM post ORDER BY date DESC LIMIT 10", nativeQuery = true)
	List<Post> findTop10OrderByDateDesc();
	
	@Query(value = "SELECT * FROM post ORDER BY date DESC", nativeQuery = true)
	List<Post> getAllOrderByDateDesc();
}
