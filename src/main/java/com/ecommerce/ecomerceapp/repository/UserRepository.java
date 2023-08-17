package com.ecommerce.ecomerceapp.repository;

import com.ecommerce.ecomerceapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
