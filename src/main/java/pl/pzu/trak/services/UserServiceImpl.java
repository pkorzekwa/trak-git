package pl.pzu.trak.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.Privilege;
import pl.pzu.trak.domain.Role;
import pl.pzu.trak.domain.User;
import pl.pzu.trak.domain.UserRegistrationDto;
import pl.pzu.trak.repositories.RoleRepository;
import pl.pzu.trak.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		User user = userRepository.findByLogin(login);
		if (user == null)
		{
			throw new UsernameNotFoundException("Błędna nazwa użytkownika, lub hasło.");
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles)
	{

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(final Collection<Role> roles)
	{
		List<String> privileges = new ArrayList<String>();
		List<Privilege> collection = new ArrayList<Privilege>();
		for (Role role : roles)
		{
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection)
		{
			privileges.add(item.getName());
		}

		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges)
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges)
		{
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	public User findByLogin(String login)
	{
		return userRepository.findByLogin(login);
	}

	public User save(UserRegistrationDto registration)
	{
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setLogin(registration.getLogin());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setEnabled(true);
		user.setRoles(Arrays.asList(roleRepository.findByName("TRAK_UZYTKOWNIK")));
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	
	public User findOne(Long id) {
		return userRepository.getOne(id);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	
}
