package org.example.poti_fullstack.orders;

import org.example.poti_fullstack.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    boolean existsByPgPaymentId(String pgPaymentId);

    Optional<Orders> findFirstByUserIdxAndPaidTrueOrderByIdxDesc(Long userIdx);
}