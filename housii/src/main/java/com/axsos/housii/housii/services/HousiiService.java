package com.axsos.housii.housii.services;


import com.axsos.housii.housii.models.Category;
import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //User services
    public List<User> allUsers() {
        return userRepository.findAll();
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    //    Category services
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Category findCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }
    }

    //    House services
    public List<House> allHouses() {
        return houseRepository.findAll();
    }


    public House createHouse(House house) {
        return houseRepository.save(house);
    }


    public House findHouse(Long id) {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if (optionalHouse.isPresent()) {
            return optionalHouse.get();
        } else {
            return null;
        }
    }

    public House updateHouse(Long id, String name, String desc, String loc, double price) {
        House house = findHouse(id);
        if (house != null) {
            house.setName(name);
            house.setDescription(desc);
            house.setLocation(loc);
            house.setPrice(price);
            houseRepository.save(house);
            return house;
        } else {
            return null;
        }
    }

    public void deleteHouse(Long id) {
        House house = findHouse(id);
        if (house != null) {
            houseRepository.deleteById(id);
        }
    }

    public void updateHousi(House house) {
        House updateHousi = houseRepository.findById(house.getId()).orElse(null);
        assert updateHousi != null;
        updateHousi.setName(house.getName());
        updateHousi.setLocation(house.getLocation());
        updateHousi.setDescription(house.getDescription());
        updateHousi.setPrice(house.getPrice());
        houseRepository.save(updateHousi);
    }

    public List<House> rentedHousesForUser(User user) {
        return houseRepository.findAllByUser(user);
    }

    public List<House> allHousesByLocation(String location) {
        return houseRepository.findByLocation(location);
    }

    public List<House> allHousesByCategory(Category category) {
        return houseRepository.findAllByCategory(category);
    }

    public House rentHouse(long userId, Long houseId) {
        User user = userRepository.findById(userId).orElse(null);
        House house = houseRepository.findById(houseId).orElse(null);
        assert house != null;
        house.setUser(user);
        return houseRepository.save(house);
    }
}

