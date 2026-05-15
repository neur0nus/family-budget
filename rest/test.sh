#!/bin/bash

# Получаем пустой список (если только одна тестовая)
curl -s http://localhost:8080/api/transactions | jq .

# Создаём расход
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"description":"Такси","amount":-400,"currency":"RUB","date":"2026-05-15"}'

# Получаем список снова – видим две записи
curl -s http://localhost:8080/api/transactions | jq .

# Удаляем по ID=2
curl -X DELETE http://localhost:8080/api/transactions/2 -v

# Проверяем, что запись удалена
curl -s http://localhost:8080/api/transactions | jq .
