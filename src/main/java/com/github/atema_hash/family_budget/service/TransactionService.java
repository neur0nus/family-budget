package com.github.atema_hash.family_budget.service;

import com.github.atema_hash.family_budget.dto.SummaryDTO;
import com.github.atema_hash.family_budget.dto.TransactionDTO;
import com.github.atema_hash.family_budget.dto.TransactionResponseDTO;
import com.github.atema_hash.family_budget.entity.CategoryType;
import com.github.atema_hash.family_budget.entity.Transaction;
import com.github.atema_hash.family_budget.exception.ResourceNotFoundException;
import com.github.atema_hash.family_budget.repository.CategoryTypeRepository;
import com.github.atema_hash.family_budget.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryTypeRepository categoryTypeRepository;

    public List<TransactionResponseDTO> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TransactionResponseDTO findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
        return toResponseDTO(transaction);
    }

    public Transaction create(TransactionDTO dto) {
        CategoryType categoryType = categoryTypeRepository.findById(dto.getCategoryTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + dto.getCategoryTypeId()));

        Transaction transaction = new Transaction();
        transaction.setName(dto.getName());
        transaction.setAmount(dto.getAmount());
        transaction.setCurrency(dto.getCurrency());
        transaction.setOperationDate(dto.getOperationDate());
        transaction.setDescription(dto.getDescription());
        transaction.setCategoryType(categoryType);

        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    private TransactionResponseDTO toResponseDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());
        dto.setName(transaction.getName());
        dto.setAmount(transaction.getAmount());
        dto.setCurrency(transaction.getCurrency());
        dto.setOperationDate(transaction.getOperationDate());
        dto.setDescription(transaction.getDescription());
        dto.setCategoryTypeId(transaction.getCategoryType().getSType());
        dto.setCategoryName(transaction.getCategoryType().getCategoryName());
        return dto;
    }

    public List<SummaryDTO> getSummary(LocalDate from, LocalDate to) {
        return transactionRepository.findSummaryByCategory(from, to);
    }

 }
