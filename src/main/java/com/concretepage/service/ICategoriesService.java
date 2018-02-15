package com.concretepage.service;

import java.util.List;
import java.util.UUID;

//import org.natha.pos.entity.Categories;
import org.springframework.security.access.annotation.Secured;

import com.concretepage.entity.Categories;

public interface ICategoriesService {
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     List<Categories> getAllCategories();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	 Categories getCategoriesById(String id);
//	 Categories getCategoriesById(UUID id);
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
//     boolean addCategories(Categories cat);
	 boolean addCategories(org.natha.pos.entity.Categories cat);
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     void updateCategories(Categories cat);
	 @Secured ({"ROLE_ADMIN"})
     void deleteCategories(String id);
//	 void deleteCategories(UUID id);
}
