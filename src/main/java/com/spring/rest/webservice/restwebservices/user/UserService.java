package com.spring.rest.webservice.restwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.webservice.restwebservices.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	/*
	 * private static List<User> users = new ArrayList<>(); private static int
	 * userCount = 0; static { users.add(new User(++userCount, "Kapil",
	 * LocalDate.now().minusYears(30))); users.add(new User(++userCount, "Noni",
	 * LocalDate.now().minusYears(27))); users.add(new User(++userCount, "Rajat",
	 * LocalDate.now().minusYears(20))); }
	 */
	
	
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).get();
		/*
		 * return users.stream().filter(user -> user.getId() ==
		 * id).findFirst().orElse(null);
		 */
	}

	public User saveUser(User user) {
		return userRepository.save(user);
		/*
		 * user.setId(++userCount); users.add(user); return user;
		 */
	}

}
