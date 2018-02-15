package com.concretepage.dao;

import java.util.List;
import java.util.UUID;

import com.concretepage.entity.Categories;

//import org.natha.pos.entity.Categories;

public interface ICategoriesDAO {
    List<Categories> getAllCategories();
    Categories getCategoriesById(String Id);
//    Categories getCategoriesById(UUID Id);
//    void addCategories(Categories categories);
    void addCategories(org.natha.pos.entity.Categories categories);
    void updateCategories(Categories categories);
    void deleteCategories(String Id);
//    void deleteCategories(UUID Id);
//    boolean categoriesExists(String title, String category);
    boolean categoriesExists(String Id);
//    boolean categoriesExists(UUID Id);
}
