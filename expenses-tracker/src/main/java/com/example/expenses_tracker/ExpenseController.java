package com.example.expenses_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // Mostrar todos los gastos
    @GetMapping
    public String getAllExpenses(Model model) {
        model.addAttribute("expenses", expenseRepository.findAll());
        return "expenses"; // Retorna el nombre de la vista para listar
    }

    // Mostrar formulario para nuevo gasto
    @GetMapping("/new")
    public String createExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        model.addAttribute("title", "Agregar Nuevo Gasto");
        model.addAttribute("action", "/expenses");
        model.addAttribute("method", "POST");
        model.addAttribute("buttonText", "Guardar Gasto");
        return "expense_form"; // Retorna el nombre de la vista
    }

    // Crear un nuevo gasto
    @PostMapping
    public String createExpense(@ModelAttribute Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/expenses"; // Redirige a la lista de gastos
    }

    // Mostrar formulario para editar un gasto
    @GetMapping("/edit/{id}")
    public String editExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        model.addAttribute("expense", expense);
        model.addAttribute("title", "Editar Gasto");
        model.addAttribute("action", "/expenses/" + id);
        model.addAttribute("method", "PUT");
        model.addAttribute("buttonText", "Actualizar Gasto");
        return "expense_form"; // Retorna el nombre de la vista
    }

    // Actualizar un gasto
    @PutMapping("/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute Expense newExpense) {
        expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDescription(newExpense.getDescription());
                    expense.setAmount(newExpense.getAmount());
                    expense.setCategory(newExpense.getCategory());
                    return expenseRepository.save(expense);
                }).orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
        return "redirect:/expenses"; // Redirige a la lista de gastos
    }

    // Eliminar un gasto
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        return "redirect:/expenses"; // Redirige a la lista de gastos
    }
}
