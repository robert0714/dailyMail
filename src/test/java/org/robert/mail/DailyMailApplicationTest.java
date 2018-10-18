package org.robert.mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("production")
public class DailyMailApplicationTest {
	
	@Autowired
	private DailyMailApplication application;

	@Before
	public void setUp() throws Exception {
//		ReflectionUtils.setField(DailyMailApplication.class.getDeclaredField("folder"), application, "/home/robert0714/Desktop/ec.txt");
		//-Dorg.robert.mail.attachment=/home/robert0714/Desktop/ec.txt
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() throws Exception {
		
		
//		application.run(null);
		
	}

}
