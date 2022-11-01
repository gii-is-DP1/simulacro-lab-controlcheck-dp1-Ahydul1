package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.pet.Visit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
	
	private ProductRepository pr;
	
	@Autowired
	public ProductService(ProductRepository pr) {
		this.pr = pr;
	}
	
	@Transactional(readOnly = true)	
    public List<Product> getAllProducts(){
        return pr.findAll();
    }
	
	@Transactional(readOnly = true)	
    public List<Product> getProductsCheaperThan(double price) {
        return pr.findByPriceLessThan(price);
    }
    
	@Transactional(readOnly = true)	
    public ProductType getProductType(String typeName) {
        return pr.findProductTypeByName(typeName);
    }
	@Transactional(readOnly = true)	
    public List<ProductType> findAllProductTypes() {
        return pr.findAllProductTypes();
    }
	
	@Transactional
    public Product save(Product p) throws DataAccessException{
        return pr.save(p);       
    }    
}
