/**
 * 
 */
package seema.rpn.main;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Seema R.Patel
 *
 */
public class RPNCalculatorTest {
	InputStream inputStream, inputStream2, inputStream3, inputStream4, inputStream5;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * Test method for {@link seema.rpn.main.SeemaRPN#main(java.lang.String[])}.
	 */
	@Test
	public final void testMain() {
		try{
		inputStream = new FileInputStream("resources\\test_data.properties");
		System.setIn(inputStream);
		RPNCalculator.main(null);
		assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
		testMainException();
		testMainException2();
		testMainException3();
		testMainException4();
	}

	public final void testMainException() {
		try {
			inputStream2 = new FileInputStream(
					"resources\\test_data_exception.properties");
			System.setIn(inputStream2);
			RPNCalculator.main(null);
			assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	public final void testMainException2() {
		try {
			inputStream3 = new FileInputStream(
					"resources\\test_data_exception2.properties");
			System.setIn(inputStream3);
			RPNCalculator.main(null);
			assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}
	public final void testMainException3() {
		try {
			inputStream4 = new FileInputStream(
					"resources\\test_data_exception3.properties");
			System.setIn(inputStream4);
			RPNCalculator.main(null);
			assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}
	public final void testMainException4() {
		try {
			inputStream5 = new FileInputStream(
					"resources\\test_data_exception4.properties");
			System.setIn(inputStream5);
			RPNCalculator.main(null);
			assertEquals(true, true);
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals(true, false);
		}
	}

	@After
	public void tearDown() throws Exception {
		System.in.close();
		if (inputStream != null)
			inputStream.close();
		if (inputStream2 != null)
			inputStream2.close();
		if (inputStream3 != null)
			inputStream3.close();
		if (inputStream4 != null)
			inputStream4.close();
	}
}
