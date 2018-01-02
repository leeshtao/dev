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
	 * ������Կ-�����ڷ�����,�ͻ����ǲ���֪����Կ��,�Է�������
	 */
	public static String SECRET = "FreeeMaNong";

	public static String createToken(String name, String age, String org) throws Exception {
		// ǩ��ʱ��
		Date iatDate = new Date();

		// ����ʱ��- 1���ӹ���
		Calendar nowTIme = Calendar.getInstance();
		nowTIme.add(Calendar.MINUTE, 1);
		Date expiresDate = nowTIme.getTime();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "Hs256");
		map.put("typ", "JWT");
		String token = JWT.create().withHeader(map)// header
				.withClaim("name", name)// payload
				.withClaim("age", age).withClaim("org", org).withExpiresAt(expiresDate)// ���ù���ʱ��-����ʱ��Ҫ����ǩ��ʱ��
				.withIssuedAt(iatDate)// ����ǩ��ʱ��
				.sign(Algorithm.HMAC256(SECRET));// ����

		return token;
	}

	/**
	 * ����token
	 */
	public static Map<String, Claim> verifyToken(String token) throws Exception {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
		} catch (Exception e) {
			throw new RuntimeException("�ȼ�ƾ֤�ѹ���,�����µ�¼");
		}

		return jwt.getClaims();
	}
}
