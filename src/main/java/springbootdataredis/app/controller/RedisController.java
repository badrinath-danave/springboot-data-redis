package springbootdataredis.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootdataredis.app.entity.Product;
import springbootdataredis.app.repository.ProductDao;

@RestController
@RequestMapping("/product")
public class RedisController {

	@Autowired
	private ProductDao productdao;
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productdao.save(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productdao.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(key="#id", value="Product", unless = "#result.price >5000")
	public Product findProduct(@PathVariable int id) {
		return productdao.findProductById(id);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key="#id", value="Product")
	public String deleteProduct(@PathVariable int id) {
		return productdao.deleteProduct(id);
	}
}
