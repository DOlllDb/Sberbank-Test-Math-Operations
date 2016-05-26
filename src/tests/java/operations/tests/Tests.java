package operations.tests;
/**
 * @author Nikelman Maksim
 */

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import operations.service.ParseCsvService;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Parameter;

@Features("Basic math operations")
@RunWith(Parameterized.class)
public class Tests {

	@Parameters(name = "{index}: does {0} {2} {1} evaluates to {3}?")
	public static Collection<Object[]> getParams() {
		return ParseCsvService.parseCSV("operations.csv", true); // с использованием файла из ресурсов
//		return ParseCsvService.parseCSV("<path_to_a_file>", false); // С указанием файла csv вне ресурсов проекта
	}

	@Parameter("Operand 1")
	public String operand1;

	@Parameter("Operand 2")
	public String operand2;

	@Parameter("Operation")
	public String operation;

	@Parameter("Result")
	public String expectedResult;
	
	public Tests(String operand1, String operand2, String operation, String expectedResult) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
		this.expectedResult = expectedResult;
	}
	
	@Test
	public void test1() { 
		double op1 = Double.parseDouble(operand1);
		double op2 = Double.parseDouble(operand2);
		double exResult = Double.parseDouble(expectedResult);
		double result;
		switch (operation) {
		case "+":
			result = op1 + op2;
			break;
		case "-":
			result = op1 - op2;
			break;
		case "*":
			result = op1 * op2;
			break;
		case "/":
			result = op1 / op2;
			break;
		default:
			throw new RuntimeException(operation + "не является операцией");
		}
		assertEquals(result, exResult, 0);
	}
}