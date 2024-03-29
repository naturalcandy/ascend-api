package com.ascend.ascend.service;
import com.ascend.ascend.model.User;
import com.ascend.ascend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.ascend.ascend.dto.UserSignUpDto;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void signup(UserSignUpDto signupDto) throws Exception {
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(signupDto.getPassword());
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setBirthday(signupDto.getBirthday());
        user.setPhoneNumber(signupDto.getPhoneNumber());
        createUser(user);
    }

    public void savePlaidAccessToken(Long userId, String accessToken) {
        User u = getUser(userId);
        u.setPlaidAccessToken(accessToken);
    }
}
