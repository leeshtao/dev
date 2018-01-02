package com.leeshtao.jwttoken;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwttToken {

	/**
	 * 公用密钥-保存在服务器,客户端是不会知道密钥的,以防被攻击
	 */
	public static String SECRET = "FreeeMaNong";

	public static String createToken(String name, String age, String org) throws Exception {
		// 签发时间
		Date iatDate = new Date();

		// 过期时间- 1分钟过期
		Calendar nowTIme = Calendar.getInstance();
		nowTIme.add(Calendar.MINUTE, 1);
		Date expiresDate = nowTIme.getTime();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "Hs256");
		map.put("typ", "JWT");
		String token = JWT.create().withHeader(map)// header
				.withClaim("name", name)// payload
				.withClaim("age", age).withClaim("org", org).withExpiresAt(expiresDate)// 设置过期时间-过期时间要大于签发时间
				.withIssuedAt(iatDate)// 设置签发时间
				.sign(Algorithm.HMAC256(SECRET));// 加密

		return token;
	}

	/**
	 * 解密token
	 */
	public static Map<String, Claim> verifyToken(String token) throws Exception {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			throw new RuntimeException("等级凭证已过期,请重新登录");
		}

		return jwt.getClaims();
	}
}
