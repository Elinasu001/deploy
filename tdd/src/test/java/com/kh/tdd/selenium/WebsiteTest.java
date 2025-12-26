package com.kh.tdd.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class WebsiteTest {
	
	private WebDriver driver;
	
	// 중복코드 뺄 수 있는 방법
	@BeforeEach
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	void 네이버() {
		//WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver();
		driver.get("http://www.naver.com");
		WebElement searchInput = driver.findElement(By.id("query"));
		searchInput.sendKeys("KH 정보교육원");
		// 티켓팅에 유리...
		searchInput.submit();
		
		// 다음 수행 전 잠시 대기하고 싶을 경우
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 검색 결과 개수
		int count = driver.findElements(By.cssSelector("div.sc_new_slog_visible")).size();
		log.info("검색 결과 개수 : " + count);
		
		if(count > 0) {
			String result = driver.findElements(By.cssSelector("div.sc_new_slog_visible")).getFirst().getText();
			log.info("검색 결과 중 첫 번째 결과 : {} " , result);
		}
		
		String title = driver.getTitle();
		assertThat(title).contains("KH 정보교육원");
		//driver.quit(); // 원래는 닫아 주는 게 맞다. => 이 경우도 동일하게 닫아야 하니 AfterEach~
	}
	
	@Test
	void 구글() {
		//WebDriverManager.chromiumdriver().setup();
		//WebDriver driver = new ChromeDriver();
		driver.get("http:www.google.com");
		assertThat(driver.getTitle()).contains("Google");
	}
	
	
	
	@Test
	void gogoKH() {
		driver.get("http://kh-academy.co.kr/login/login.kh");
		// 로그인 버튼이 나올때까지 대기
		List<WebElement> els = driver.findElements(By.cssSelector("[style=\"background:#222;\"]"));
		WebElement loginBtn = els.get(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		WebElement id = driver.findElement(By.id("id"));
		WebElement pwd= driver.findElement(By.id("password"));
		id.sendKeys("sueline123");
		pwd.sendKeys("");
		btn.click();
		
		//단위테스트
		//슬라이스테스트(컨트롤러)
		//UI테스트
		
		
		//포트폴리오에 추가할 내용 실습
		// 세미 프로젝트 결과물
		// -> 자기 서비스 클래스 하나 선택하기
		// -> 서비스단 메소드들 단위테스트 코드 작성하기 하나만 !!
		// -> 컨트롤러 하나 선택하기
		// -> WebMvcTest 응답 코드 테스트코드 작성하기 하나만 !!
		
		// -> 리액트 돌려가지고 
		// -> 셀레니움으로 UI테스트 코드 하나 작성하기 !!
		
		// -> 코드랑 테스트 결과 캡쳐해서 포트폴리오에 첨부하기
		;
		log.info("a 목록 : {}", els);
	}
	
	
	
	
	@AfterEach
	public void quit() {
		if(driver != null) {
			//driver.quit();
		}
	}
		
	
}
