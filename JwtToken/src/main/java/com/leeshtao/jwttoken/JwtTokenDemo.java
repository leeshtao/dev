package com.leeshtao.jwttoken;

import java.util.Map;

import com.auth0.jwt.interfaces.Claim;

public class JwtTokenDemo {

	public static void main(String[] args) throws Exception {
		String token = JwttToken.createToken("jack", "22", "google company");

		System.out.println("Token: " + token);

		Map<String, Claim> claims = JwttToken.verifyToken(token);
		System.out.println(claims.get("name").asString());
		System.out.println(claims.get("age").asString());
		System.out.println(claims.get("org") == null ? null : claims.get("org").asString());

		// 使用过期后的token进行校验
		String tokenExpire = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiJnb29nbGUgY29tcGFueSIsIm5hbWUiOiJqYWNrIiwiZXhwIjoxNTE0ODU3Nzg2LCJpYXQiOjE1MTQ4NTc3MjYsImFnZSI6IjIyIn0.AIFn39QL-JRz4H49VbRKo8IAXe6dACVOCQOQ-2zD4EE";
		Map<String, Claim> claimsExpire = JwttToken.verifyToken(tokenExpire);
	}
}
