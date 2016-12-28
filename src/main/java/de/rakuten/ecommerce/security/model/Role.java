package de.rakuten.ecommerce.security.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import de.rakuten.ecommerce.base.model.AbstractEntity;

@Entity
public class Role extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String name;
	@NotNull
	private String accessPattern;
	@OneToMany(mappedBy = "role")
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @return the accessPattern
	 */
	public String getAccessPattern() {
		return accessPattern;
	}

	/**
	 * @param accessPattern
	 *            the accessPattern to set
	 */
	public void setAccessPattern(String accessPattern) {
		this.accessPattern = accessPattern;
	}
}
