package com.kh.springweb.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.Value;

@Configuration 
@EnableWebSecurity
public class SecurityConfigure {
	/**
	 * 
	 * 나중에는 이걸로 경로 넣기 지금은 테스트 용도이니 * 로 진행
	@Value("${instance.url}")
	private String instance;
	**/
	
	/**
	 * 기존에는 최신 z문법으로 사용했지만 요번에는 mz문법으로 진행~~
	 * @param http
	 * @return
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.httpBasic(basic -> basic.disable())
			.formLogin(form -> form.disable())
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // 오픈 용도니깐 permitAll();
		return http.build();
		
	}
	
	@Bean // react x
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("*")); // 이렇게 적으면, 계속 고쳐야되는 상황 -> 빌드 -> 붙이기 ...//=> 가변 벨류인데 하드코딩 => 분리하자.
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config); // 일단 다 열어놓기
		
		return source;
	}
	
	
}
