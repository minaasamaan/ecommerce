package de.rakuten.ecommerce.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.rakuten.ecommerce.base.context.ApplicationConfigurations;
import de.rakuten.ecommerce.security.manager.RoleManager;
import de.rakuten.ecommerce.security.manager.UserManager;
import de.rakuten.ecommerce.security.model.Role;
import de.rakuten.ecommerce.security.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM = "RAKUTEN_ECOMMERCE_REST_API";
	private static final String SYSTEM = "SYSTEM";

	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ApplicationConfigurations applicationConfigurations;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		List<User> users = getUserManager().readAll();
		for (User user : users) {
			auth.inMemoryAuthentication().passwordEncoder(getbCryptPasswordEncoder()).withUser(user.getUsername())
					.password(user.getPassword()).roles(user.getRole().getName());
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Just for the sake of easy testing
		if (getApplicationConfigurations().isCreateDefaultSuperUser()) {
			createDefaultSuperUser();
		}
		List<Role> roles = getRoleManager().readAll();
		for (Role role : roles) {
			http.csrf().disable().requiresChannel().anyRequest().requiresSecure().and().authorizeRequests()
					.antMatchers(role.getAccessPattern()).hasRole(role.getName()).and().httpBasic().realmName(REALM)
					.authenticationEntryPoint(getBasicAuthEntryPoint());
		}
	}

	/**
	 * 
	 */
	private void createDefaultSuperUser() {
		if (getRoleManager().readAll().isEmpty()) {
			Role role = new Role();
			role.setAccessPattern("**");
			role.setName("SUPER_USER");
			role.setLastModifiedBy(SYSTEM);
			role = getRoleManager().create(role);

			User user = new User();
			user.setRole(role);
			user.setUsername(getApplicationConfigurations().getSuperUserName().trim());
			user.setPassword(getApplicationConfigurations().getSuperUserPassword().trim());
			user.setLastModifiedBy(SYSTEM);
			getUserManager().create(user);
		}
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
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

	/**
	 * @return the applicationConfigurations
	 */
	public ApplicationConfigurations getApplicationConfigurations() {
		return applicationConfigurations;
	}

	/**
	 * @param applicationConfigurations
	 *            the applicationConfigurations to set
	 */
	public void setApplicationConfigurations(ApplicationConfigurations applicationConfigurations) {
		this.applicationConfigurations = applicationConfigurations;
	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager
	 *            the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @return the roleManager
	 */
	public RoleManager getRoleManager() {
		return roleManager;
	}

	/**
	 * @param roleManager
	 *            the roleManager to set
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
}
