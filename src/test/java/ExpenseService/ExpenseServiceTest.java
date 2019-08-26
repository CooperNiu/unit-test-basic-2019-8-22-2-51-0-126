package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;

import static ExpenseService.Expense.ExpenseType.*;
import static ExpenseService.Project.ProjectType.INTERNAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
    	Project project = new Project(ProjectType.INTERNAL, "test");
    	// when
    	ExpenseType actual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(INTERNAL_PROJECT_EXPENSE, actual);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
    	Project project = new Project(ProjectType.EXTERNAL, "Project A");
        // when
    	ExpenseType actual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(EXPENSE_TYPE_A, actual);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
    	 // given
    	Project project = new Project(ProjectType.EXTERNAL, "Project B");
        // when
    	ExpenseType actual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(EXPENSE_TYPE_B, actual);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
    	 // given
    	Project project = new Project(ProjectType.EXTERNAL, "Project C");
        // when
    	ExpenseType actual = ExpenseService.getExpenseCodeByProjectTypeAndName(project);
        // then
    	assertEquals(OTHER_EXPENSE, actual);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid()  {
    	// given
    	Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project f");
        // when
    	UnexpectedProjectTypeException exception = assertThrows(UnexpectedProjectTypeException.class, ()->{
    		ExpenseService.getExpenseCodeByProjectTypeAndName(project);
    	});
        // then
    	assertEquals("You enter invalid project type", exception.getMessage());
    }
}