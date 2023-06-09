package com.ascend.ascend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ascend.ascend.model.User;
import com.ascend.ascend.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class AscendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testGetUserId() {
		Optional<User> user1 = userRepository.findByemail("example@gmail.com");
		Optional<User> user2 = userRepository.findByemail("test@gmail.com");
		assert(user1.isPresent());
		assert(user2.isPresent());
	}
}
