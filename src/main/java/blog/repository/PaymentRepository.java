package blog.repository;

import blog.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query(value = "SELECT * FROM payment WHERE favourite IS TRUE LIMIT 10", nativeQuery = true)
	List<Payment> findTop10ByFavouriteIsTrue();
}
