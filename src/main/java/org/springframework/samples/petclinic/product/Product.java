package org.springframework.samples.petclinic.product;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends NamedEntity{
	// Extendiendo NamedEntity, id y name se implementan con las restricciones deseadas 
	
	@NotNull
	@DecimalMin("0.0")
	double price;
	
	@ManyToOne
	@JoinColumn(name= "product_type_id")
    ProductType productType;

}
