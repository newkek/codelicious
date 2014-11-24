package utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class SearchLogger {
	@Before("execution(* *..DAOContact.getContact(..)) && args(firstName,*,*)")
	public void searchLog (String firstName){
		System.out.println("Firstname cherché : "+firstName);
	}
}
