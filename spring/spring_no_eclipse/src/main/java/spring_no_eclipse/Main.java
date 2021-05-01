package spring_no_eclipse;

import org.springframework.context.support.GenericXmlApplicationContext;
//이클립스 없이 spring pjt 생성
//pjt 폴더 - src, pom.xml - main - java, resources
public class Main {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		TransportationWalk t = ctx.getBean("tWalk", TransportationWalk.class);
		t.move();
		
		ctx.close();
	}

}
