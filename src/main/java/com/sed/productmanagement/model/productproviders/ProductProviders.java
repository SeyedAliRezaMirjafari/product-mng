package com.sed.productmanagement.model.productproviders;

import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.provider.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "product_providers")
public class ProductProviders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
