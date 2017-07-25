package onther;

 
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // springContextUtil

	
	public SpringUtil() {
		if(applicationContext==null) {
	    	 applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static Object getBean(String name) throws BeansException {
		if(applicationContext==null) {
	    	 applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return applicationContext.getBean(name);
	}

 

}

