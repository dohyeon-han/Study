package test;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		//TransportationWalk trans = new TransportationWalk();
		//trans.move();
		//xml을 통해 객체 생성
		//resource컨테이너를 불러옴
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		//컨테이너의 bean
		TransportationWalk transportationWalk = 
		ctx.getBean("tWalk", TransportationWalk.class);
		
		transportationWalk.move();
		System.out.println(transportationWalk.getStart());
		System.out.println(transportationWalk.getEnd());
		ctx.close();
	}

}
