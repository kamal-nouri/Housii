package com.axsos.housii.housii.services;


import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HousiiService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RatingRepository ratingRepository;
    private final HouseRepository houseRepository;
    private final CategoryRepository categoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public HousiiService(UserRepository userRepository, RoleRepository roleRepository, RatingRepository ratingRepository, HouseRepository houseRepository, CategoryRepository categoryRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.ratingRepository = ratingRepository;
        this.houseRepository = houseRepository;
        this.categoryRepository = categoryRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    // 1
    public void saveWithUserRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    // 2
    public void saveUserWithAdminRole(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_ADMIN"));
        userRepository.save(user);
    }

    // 3
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }
}

