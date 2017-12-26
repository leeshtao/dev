package com.test.pagehelp;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pagehelp.Person;

public class MyBatisMain {

	private static ApplicationContext ctx;

	static {
		ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}

	public static void main(String[] args) {
		PersonPageMapper personPageMapper = (PersonPageMapper) ctx.getBean("personPageMaper");
		//通过PageHelper插件设置分页的信息，下边是查询第一页，每一页2条数据
		PageHelper.startPage(1, 2);
		//插件下边的查询就是就能拦截进行分页了，可以看出对原来的查询方法没有增加任何额外代码
		List<Person> persons = personPageMapper.getPersons();
		 System.out.println("本次查询的person个数：" + persons.size());
		for (Person person : persons) {
			System.out.println("person info start ========================");
			System.out.println("person name: "+person.getPersonname() );
			System.out.println("person age: "+person.getAge() );
			System.out.println("person sex: "+person.getSex() );
			System.out.println("person info end  +++++++++++++++++++++++++");
		}
		
		// 取分页信息
        PageInfo<Person> pageInfo = new PageInfo<Person>(persons);
        //获取总记录数
        long total = pageInfo.getTotal(); 
        System.out.println("数据库中person的总数：" + total);
		
	}
}


