package org.springframework.samples.petclinic.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final String PRODUCT_FORM = "products/createOrUpdateProductForm";
	private static final String WELCOME = "welcome";
	private ProductService ps;

	@Autowired
	public ProductController(ProductService ps) {
		this.ps = ps;
	}
	
	@GetMapping("/create")
	public ModelAndView añadeProducto() {
		ModelAndView res = new ModelAndView(PRODUCT_FORM);
		Product product = new Product();
		res.addObject("product", product);
		res.addObject("productTypes", ps.findAllProductTypes());

		return res;
	}
	@PostMapping("/create")
	public ModelAndView añadeProducto(@Valid Product producto, BindingResult result) {
		ModelAndView res = new ModelAndView(PRODUCT_FORM);
		res.addObject("productTypes", ps.findAllProductTypes());
		res.addObject("product", producto);
		
		if (result.hasErrors()) {
			res.addObject("error", "Error de validacion");
		}
		else {
            try{
            	ps.save(producto);
    			return new ModelAndView(WELCOME);
            }catch(DataAccessException e){
    			res.addObject("error", "Error guardando producto");
                return res;
            }
		}
		
		return res;
	}

}
