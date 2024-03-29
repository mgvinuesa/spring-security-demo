package com.example.demo.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.error.UserAlreadyExistException;
import com.example.demo.user.persistence.Privilege;
import com.example.demo.user.persistence.Role;
import com.example.demo.user.persistence.RoleRepository;
import com.example.demo.user.persistence.User;
import com.example.demo.user.persistence.UserRepository;
import com.example.demo.user.persistence.VerificationToken;
import com.example.demo.user.persistence.VerificationTokenRepository;

@Service
public class CustomUserService implements UserDetailsService, UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private VerificationTokenRepository verificationTokenRepository;

	public CustomUserService(UserRepository userRepository, RoleRepository roleRepository,
			VerificationTokenRepository verificationTokenRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.verificationTokenRepository = verificationTokenRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("No user found with email: " + email);
		}
		// boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword().toLowerCase(),
				user.isEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(final Collection<Role> roles) {
		final List<String> privileges = new ArrayList<>();
		final List<Privilege> collection = new ArrayList<>();
		for (final Role role : roles) {
			privileges.add(role.getName());
			collection.addAll(role.getPrivileges());
		}
		for (final Privilege item : collection) {
			privileges.add(item.getName());
		}

		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	@Override
	@Transactional
	public User registerNewUserAccount(@Valid UserDto userDto) {
		if (emailExists(userDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
		}

		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

		return userRepository.save(user);
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
	@Transactional
	public void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		verificationTokenRepository.save(myToken);
	}

	@Override
	@Transactional
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

	@Override
	@Transactional
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

}
