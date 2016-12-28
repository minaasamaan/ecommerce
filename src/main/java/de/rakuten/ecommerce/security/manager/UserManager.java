/**
 * 
 */
package de.rakuten.ecommerce.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
import de.rakuten.ecommerce.security.model.User;
import de.rakuten.ecommerce.security.repository.UserRepository;

/**
 * @author Mina
 *
 */
@Service
public class UserManager extends AbstractBusinessEntityManager<User> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void doBeforePersist(User user, boolean isNew) {
		user.setPassword(getbCryptPasswordEncoder().encode(user.getPassword()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityRepository()
	 */
	@Override
	public JpaRepository<User, Long> getEntityRepository() {
		return userRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityClass()
	 */
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	/**
	 * @return the bCryptPasswordEncoder
	 */
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	/**
	 * @param bCryptPasswordEncoder
	 *            the bCryptPasswordEncoder to set
	 */
	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

}
