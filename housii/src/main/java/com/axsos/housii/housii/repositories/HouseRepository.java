package com.axsos.housii.housii.repositories;

import com.axsos.housii.housii.models.Category;
import com.axsos.housii.housii.models.House;
import com.axsos.housii.housii.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends CrudRepository<House,Long> {
List<House> findAll();
List<House>findAllByUser(User user);
//List<House>findAllByLocation(String location);
List<House>findAllByCategory(Category category);
@Query(value = "SELECT location FROM houses where location like %:keyword%",nativeQuery = true)
 List<String> search(@Param("keyword") String keyword);
 @Query(value = "SELECT * FROM houses where location=?1",nativeQuery = true)
 List<House> findAllByLocation(@Param("keyword") String keyword);
}

