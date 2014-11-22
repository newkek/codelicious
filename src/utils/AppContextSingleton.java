package utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextSingleton implements ApplicationContextAware {
    private static ApplicationContext context = null;
   
    public static ApplicationContext getContext(){
        return context;
    }

    public void setApplicationContext(ApplicationContext newContext)
            throws BeansException {
        context = newContext;
    }
}
