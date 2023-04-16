package springbootdataredis.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import springbootdataredis.app.entity.Product;

@Repository
public class ProductDao {

	private static final Object HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate template;
	
	
	public Product save(Product product) {
		 template.opsForHash().put(HASH_KEY, product.getId(), product);
		 return product;
	}
	
	public List<Product> findAll(){
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Product findProductById(int id){
		System.out.println(" called this findProductById method from DB ");
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}
	
	public String deleteProduct(int id){
		System.out.println("deleted from db");
		template.opsForHash().delete(HASH_KEY, id);
		return "Product removed";
	}
}
