package com.axsos.housii.housii.repositories;

import com.axsos.housii.housii.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    List<Category>findAll();
    Category findCategoryByName(String name);
}
