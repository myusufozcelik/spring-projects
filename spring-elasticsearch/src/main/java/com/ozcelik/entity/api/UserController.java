package com.ozcelik.entity.api;

import com.ozcelik.entity.User;
import com.ozcelik.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    
    @PostConstruct
    public void init() {
        User user = new User();
        user.setId("0001");
        user.setName("Yusuf");
        user.setSurname("Ozcelik");
        user.setAddress("Ankara");
        user.setBirthDate(Calendar.getInstance().getTime());
        userRepository.save(user);
    }

    @GetMapping("/{search]")
    public ResponseEntity<List<User>> getUser(@PathVariable String search) {
        List<User> users = userRepository.getByCustomQuery(search);
        return ResponseEntity.ok(users);
    }

}
