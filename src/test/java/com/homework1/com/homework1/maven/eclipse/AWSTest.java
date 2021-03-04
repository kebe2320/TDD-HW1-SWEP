package com.homework1.com.homework1.maven.eclipse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original={1, 2, 3};
	AWS originalAWS;
	
	@BeforeEach
	void setUp() throws Exception {
		 originalAWS = new AWS(this.original);
	}

	@Test
	void testGetValues() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		
		AWS aws = new AWS(x);
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
		assertEquals(actual[2], expected[2]);
	}

	@Test
	void testSetValues() {
		int[] first = {1, 2, 3};
		int[] arr1 = {1, 3, 3};
		int[] arr2 = {1, 1, 1, 1};
		
		AWS aws = new AWS(first);
		
		aws.setValues(arr1);
		int[] results = aws.getValues();
		
		assertEquals(arr1[0], results[0]);
		assertEquals(arr1[1], results[1]);
		assertEquals(arr1[2], results[2]);
		
		aws.setValues(arr2);
		int[] actual = aws.getValues();
		
		assertEquals(arr2[0], actual[0]);
		assertEquals(arr2[1], actual[1]);
		assertEquals(arr2[2], actual[2]);
		assertEquals(arr2[3], actual[3]);
	}

	@Test
	void testToString() {
		int[] first = {1, 2, 3};
		int[] arr1 = {1, 3, 3};
		int[] arr2 = {1, 1, 1, 1};
		
		AWS aws = new AWS(arr1);
		
		assertEquals("AWS [values=[1, 3, 3]]", aws.toString());
	}

	@Test
	void testAWS() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		x[1] = 100;
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}
	
	@Test
	void testRemove() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		 value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		value = aws.remove(0);
		assertEquals(x[0], value);
		
		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
		value = aws.remove(2);
		assertEquals(r[2], value);
		
		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
	}
	
	@Test
	void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
 		
		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);
		 
		
	
	}
	@Test
	void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
 		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		 
		assertEquals(first, result[0]);
	}
	
	@Test
	void testRemoveBiggerThan() {
		int threshold = 0;
		int expected = 3;
		int[] values = {1, 2, 3};
		
		AWS aws = new AWS(values);
		
		int result = aws.removeBiggerThan(threshold);
		int[] newValues = aws.getValues();
		
		assertEquals(expected, result);
		assertEquals(FILLER_VALUE, newValues[0]);
		assertEquals(FILLER_VALUE, newValues[1]);
		assertEquals(FILLER_VALUE, newValues[2]);
	}
}
