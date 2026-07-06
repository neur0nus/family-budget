package com.github.atema_hash.family_budget.repository;

import com.github.atema_hash.family_budget.dto.SummaryDTO;
import com.github.atema_hash.family_budget.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(attributePaths = {"categoryType"})
    List<Transaction> findAll();

    @Query("SELECT new com.github.atema_hash.family_budget.dto.SummaryDTO(" +
            "t.categoryType.categoryName, SUM(t.amount)) " +
            "FROM Transaction t " +
            "WHERE t.operationDate BETWEEN :from AND :to " +
            "GROUP BY t.categoryType.categoryName")
    List<SummaryDTO> findSummaryByCategory(
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

}