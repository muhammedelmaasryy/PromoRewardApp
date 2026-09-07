package com.masry.fawazer.repositories;

import com.masry.fawazer.models.Customer;
import com.masry.fawazer.models.PromoClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PromoClaimRepository extends JpaRepository<PromoClaim, Long> {

    long countByCustomerAndClaimedAtGreaterThanEqual(Customer customer, LocalDateTime claimedAt);

    List<PromoClaim> findByCustomer(Customer customer);
}
