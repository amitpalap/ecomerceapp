package com.ecommerce.ecomerceapp.repository;

import com.ecommerce.ecomerceapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
