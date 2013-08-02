package test.main;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestCase {

	public MainTest(String methodName) {
		super(methodName);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHello() {
		//fail("Not yet implemented");
		Main main = new Main();
		assertEquals(main.hello(), "hello");
	}
	
	public void othertest() {
		Main main = new Main();
		assertEquals(main.hello(), "hello");
	}
	
//	 public static void main(String[] args) {
//	    junit.textui.TestRunner.run(MainTest.suite());
//	 }

	 public static Test suite() {
		 TestSuite suite = new TestSuite(MainTest.class);
	     suite.addTest(new MainTest("othertest"));
	     return suite;
	}
}
