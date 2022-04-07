import budget.ExpenseRepetition;
import budget.persistence.budget.Budget;
import budget.persistence.expense.PunctualExpense;
import budget.persistence.expense.RecurrentExpense;
import budget.persistence.expense.SpreadExpense;

import java.sql.Date;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("--------------- Test--------------- ");
        Budget budget1 = new Budget("Budget Test");
        PunctualExpense exp1 = new PunctualExpense("Test1",
                200.0f,
                budget1.getId(),
                new Date(System.currentTimeMillis()));
        RecurrentExpense expRec1 = new RecurrentExpense("RecExp1", 150.0f, budget1.getId(),
                new Date(System.currentTimeMillis()), ExpenseRepetition.DAILY);

        SpreadExpense sprExp1 = new SpreadExpense("SpreadExp1", 50.5f, budget1.getId(),
                new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 150000000));

        // Display
        System.out.println(budget1);
        System.out.println(exp1);
        System.out.println(expRec1);
        System.out.println(sprExp1);

        System.out.println("--------------- Test--------------- ");
    }
}