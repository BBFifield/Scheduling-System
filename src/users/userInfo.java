package users;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class userInfo {

	private Map properties;
	


	  public userInfo(Map properties) {
	    if (properties == null) {
	      this.properties = new HashMap();
	    } else {
	      this.properties = new HashMap(properties);
	    }
	  }

	  public Object getProperty(String propertyName) {
	    return properties.get(propertyName);
	  }

	  public Map getProperties() {
	    return properties;
	  }

	  public boolean matches(userInfo otherSpec) {
	    for (Iterator i = otherSpec.getProperties().keySet().iterator(); 
	         i.hasNext(); ) {
	      String propertyName = (String)i.next();
	      if (!properties.get(propertyName).equals(
	           otherSpec.getProperty(propertyName))) {
	        return false;
	      }
	    }
	    return true;
	  }
	}


