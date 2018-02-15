package com.concretepage.service;

import java.util.List;
import java.util.UUID;

//import org.natha.pos.dao.ICategoriesDAO;
//import org.natha.pos.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.concretepage.dao.IArticleDAO;
import com.concretepage.dao.ICategoriesDAO;
//import com.concretepage.entity.Article;
import com.concretepage.entity.Categories;

@Service
public class CategoriesService implements ICategoriesService{
	@Autowired
	private ICategoriesDAO categoriesDAO;
	
	@Override
	public List<Categories> getAllCategories() {
		return categoriesDAO.getAllCategories();
	}

	@Override
	public Categories getCategoriesById(String id) {
//	public Categories getCategoriesById(UUID id) {
		Categories obj = categoriesDAO.getCategoriesById(id);
		return obj;
	}

	@Override
//	public boolean addCategories(Categories cat) {
	public boolean addCategories(org.natha.pos.entity.Categories cat) {
		/* coba remark dulu ya..*/
		String id = cat.getCategoriesId();
		if (categoriesDAO.categoriesExists(id)) {
	    	   return false;
	       } else {
			System.out.println("Debug : "+cat.getCategoriesId());
	    	   categoriesDAO.addCategories(cat);
	    	   return true;
	       }
	}

	@Override
	public void updateCategories(Categories cat) {
		categoriesDAO.updateCategories(cat);
	}

	@Override
	public void deleteCategories(String id) {
//	public void deleteCategories(UUID id) {
		categoriesDAO.deleteCategories(id);		
	}

	
}
