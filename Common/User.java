package Common;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 932554284774867157L;
	private String username;

	public User(String username) {
		if (username == null) {
			throw new IllegalStateException("Player must have a username!");
		}
		
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
