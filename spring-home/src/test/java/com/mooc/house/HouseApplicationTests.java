package com.mooc.house;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mooc.house.mapper.UserMapper;
import com.mooc.house.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseApplicationTests {
	
	private ApplicationContext applicationContext;

	@Before
	public void init() throws Exception {
        //初始化spring容器,加战配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
    }

	
	@Test
	public void contextLoads() {
		//UserDao userDao = applicationContext.getBean(UserDao.class);
		UserMapper userDao = applicationContext.getBean(UserMapper.class);
        List<User> list = userDao.selectUsers();
        for (User user : list) {
            System.out.println(user.getName());
        }
        
        
        /*
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserDAO dao = ac.getBean(UserDAO.class);
        User u1 = dao.find("hehe");
        User u2 = dao.login("hehe", "123");
        System.out.println(u1.getName().equals(u2.getName()));
        */

	}

}
