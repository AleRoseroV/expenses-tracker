package com.example.expenses_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    // Obtener todos los gastos
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Obtener un gasto por ID
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
    }

    // Registrar un nuevo gasto
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    // Actualizar un gasto
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense newExpense) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setDescription(newExpense.getDescription());
            expense.setAmount(newExpense.getAmount());
            expense.setCategory(newExpense.getCategory());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
    }

    // Eliminar un gasto
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }
}
