package com.ascend.ascend.service;
import com.ascend.ascend.model.User;
import com.ascend.ascend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ascend.ascend.dto.LoginDto;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void login(LoginDto loginDto) throws Exception {               
        Optional<User> user = userRepository.findByemail(loginDto.getEmail());
        if (user.isEmpty() || !user.get().getPassword().equals(loginDto.getPassword())) {
            throw new Exception("Invalid email/password."); 
        }
        return;
    }
}