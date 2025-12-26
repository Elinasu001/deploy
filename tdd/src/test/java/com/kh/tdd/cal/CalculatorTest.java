package com.kh.tdd.cal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kh.tdd.cal.Calculator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculatorTest {
	
	private Calculator c = new Calculator();
	
	@Test
	public void returnHi() {
		assertEquals("Hi", c.returnHi(), "호출결과 hi가 아닙니다.");
		// 첫번째 매개변수 : 기대하는 값
		// 두번째 매개변수 : 실제 값
		// 세번째 매개변수 : 실패 시 출력할 메시지
	}
	
	@Test
	@DisplayName("두 수를 더하면 합을 반환")
	public void shouldAddTwoNumber() {
		assertEquals(10, c.add(5, 5));
	}
	
	@Test
	@DisplayName("두 수를 빼면 차를 반환")
	public void shouldSubstractTwoNumber() {
		assertEquals(5, c.substract(10, 5));
	}
	
	@Test
	@DisplayName("0으로 나누면 예외발생")
	public void shouldThrowExceptionDivideByZero() {
		assertThrows(ArithmeticException.class, () -> c.divide(10, 0));
	}
	
}
