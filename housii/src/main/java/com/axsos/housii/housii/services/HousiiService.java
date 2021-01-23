package com.axsos.housii.housii.services;


import com.axsos.housii.housii.models.Category;
import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.repositories.*;
import net.bytebuddy.utility.RandomString;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

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
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    // 2
    public void saveUserWithAdminRole(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //    Category services
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }


    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    public Category findCategory(String name) {
        return categoryRepository.findCategoryByName(name);
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

    // Email verification
    public void register(User user, String siteURL)
            throws UnsupportedEncodingException, MessagingException {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userRepository.save(user);
        saveWithUserRole(user);
        sendVerificationEmail(user, siteURL);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "rental.housii@gmail.com";
        String senderName = "Housii";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

        System.out.println("Email has been sent");
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }

    }

    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        }
        else{
//            String encodedPassword = passwordEncoder.encode(password);
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}

