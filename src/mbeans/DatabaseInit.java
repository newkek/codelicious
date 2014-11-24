package mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "dbinit", eager = true)
@RequestScoped
public class DatabaseInit {
	
	public DatabaseInit(){
		
	}

}
