package io.github.pinjie.reaspring.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

import javax.crypto.SecretKey;

public class JwtSecretGenerator {
	public static void main(String[] args) {
		// 產生安全的 HMAC-SHA256 金鑰
		SecretKey secretKey = Jwts.SIG.HS256.key().build();

		// 轉換成 Base64 編碼
		String base64Key = Encoders.BASE64.encode(secretKey.getEncoded());

		System.out.println("Generated JWT Secret Key: " + base64Key);
	}
}
