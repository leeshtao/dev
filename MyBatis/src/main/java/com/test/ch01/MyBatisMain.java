package com.test.ch01;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisMain {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("config/ch01/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Person person = (Person) session.selectOne("com.test.ch01.PersonMapper.getPersonByID", 11);
			if (person != null) {
				System.out.println("personid: "+person.getPersonid());
				System.out.println("name: "+person.getName());
				System.out.println("age: "+person.getAge());
				System.out.println("sex: "+person.getSex());

			}
		} finally {
			session.close();
		}
	}

}
