package utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class SearchLogger {
	@Before("execution(* *..addContact(..))")
	public void searchLog (JoinPoint j){
		System.out.println("Firstname cherché : "+j.getArgs()[0]);
	}
}
