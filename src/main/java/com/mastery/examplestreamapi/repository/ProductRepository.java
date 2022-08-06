package com.mastery.examplestreamapi.repository;

import com.mastery.examplestreamapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
