package blog.service;

import blog.model.Product;
import blog.model.ProductType;
import blog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void save(Product product) {
		productRepository.save(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public List<Product> findAllByType(ProductType type) {
		return productRepository.findAllByType(type);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
