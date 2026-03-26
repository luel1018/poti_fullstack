package org.example.poti_fullstack.portfolio;

import org.example.poti_fullstack.portfolio.model.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    Page<Portfolio> findByUserIdx(Long userIdx, Pageable pageable);

    List<Portfolio> findAllByUserIdx(Long userIdx);
}
