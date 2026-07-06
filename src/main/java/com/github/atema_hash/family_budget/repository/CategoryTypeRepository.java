package com.github.atema_hash.family_budget.repository;

import com.github.atema_hash.family_budget.entity.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, Integer> {
}
