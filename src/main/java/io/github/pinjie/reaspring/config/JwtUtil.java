package io.github.pinjie.reaspring.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

	// 使用 @Value 簡單的注入 properties 裡面的 JWT 密鑰和過期時間
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(UserDetails userDetails) {
		// 可以加入想要放在 token 裡面的 claims
		Map<String, Object> claims = new HashMap<>();

		return createToken(claims, userDetails.getUsername());
	}

	// 產生 JWT Token，用使用者的 username 來當成 subject
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration * 1000))
				.signWith(getSigningKey())
				.compact();
	}

	// 驗證使用者傳來的 JWT 是不是合法的
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// 從 JWT 取出裡面的 Subject (使用者的 username)
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// 把 properties 裡面的 JWT 密鑰，轉換成 Java 的 SecretKey (簽名密鑰)
	private SecretKey getSigningKey() {
		byte[] keyBytes = secret.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// 從 JWT 取出裡面的 Expiration (過期時間)
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	// 取出 JWT 特定的 Claim
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// 取出 JWT 的所有 Claims
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	// JWT 有沒有過期
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}