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
	void contextLoads() {}

	@Autowired
	private UserRepository userRepository;
}
