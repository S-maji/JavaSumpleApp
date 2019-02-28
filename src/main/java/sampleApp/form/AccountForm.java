package sampleApp.form;

import javax.validation.constraints.NotBlank;

public class AccountForm {

	@NotBlank
	private String name;

	@NotBlank
	private String password;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
