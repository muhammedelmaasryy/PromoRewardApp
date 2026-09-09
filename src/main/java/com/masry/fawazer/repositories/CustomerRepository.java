package com.masry.fawazer.repositories;

import com.masry.fawazer.models.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional <Customer> findByPhoneNumber(String phoneNumber);
}
