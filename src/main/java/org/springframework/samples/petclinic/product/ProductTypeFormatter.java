package org.springframework.samples.petclinic.product;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeFormatter implements Formatter<ProductType>{

	private final ProductService ps;
	
	public ProductTypeFormatter(ProductService ps) {
		this.ps = ps;
	}
	
    @Override
    public String print(ProductType object, Locale locale) {
        return object.getName();
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
    	ProductType res = this.ps.getProductType(text);
    	if(res == null) 
    		throw new ParseException("Type "+text + " not found", 0);
    	
    	return res;
    }
    
}
