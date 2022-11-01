package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findAll();
    
	@Query("SELECT pt FROM ProductType pt")
    List<ProductType> findAllProductTypes();
	
    Optional<Product> findById(int id);
    
	@Query("SELECT pt FROM ProductType pt WHERE pt.name = :name")
    ProductType findProductTypeByName( @Param("name") String name);

    Product findByName(String name);
    
    Product save(Product p) throws DataAccessException;
    
    @Query("SELECT p FROM Product p WHERE p.price < :coste")
    List<Product> findByPriceLessThan(@Param("coste") Double coste);
}
