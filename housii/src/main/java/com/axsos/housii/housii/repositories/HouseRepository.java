package com.axsos.housii.housii.repositories;

import com.axsos.housii.housii.models.Category;
import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends CrudRepository<House,Long> {
List<House> findAll();
List<House>findAllByUser(User user);
List<House>findByLocation(String location);
List<House>findAllByCategory(Category category);
}
