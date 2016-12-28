package de.rakuten.ecommerce.security.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.rakuten.ecommerce.base.model.AbstractEntity;

@Entity
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;
	@NotNull
	private String password;

	@ManyToOne(optional = false)
	private Role role;

	public String getUsername() {
		return username;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
