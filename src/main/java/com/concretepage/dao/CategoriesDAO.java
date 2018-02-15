package com.concretepage.dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.natha.pos.entity.Categories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Article;
import com.concretepage.entity.Categories;

//import com.concretepage.dao.IArticleDAO;
//import com.concretepage.entity.Article;

@Transactional
@Repository
public class CategoriesDAO implements ICategoriesDAO{
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public List<Categories> getAllCategories() {
		String hql = "FROM Categories as cat ORDER BY cat.id";
		return (List<Categories>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Categories getCategoriesById(String id) {
//	public Categories getCategoriesById(UUID id) {
		return entityManager.find(Categories.class, id);
	}

	@Override
//	public void addCategories(Categories categories) {
	public void addCategories(org.natha.pos.entity.Categories categories) {
		//getCategoriesById(categories.getCategoriesId());
		
		String categoriesId = categories.getCategoriesId();
		String name			= categories.getName();
		String parentid		= categories.getParentid();
		String texttip		= categories.getTexttip();
		Boolean catshowname	= categories.getCatshowname();
		
		//Constructornya harus spt ini ya..!!!inget...:d
		Categories cat = new Categories(categoriesId, name, parentid, texttip, catshowname);
		cat.setCatshowname(catshowname);
		cat.setName(name);
		cat.setParentid(parentid);
		cat.setTexttip(texttip);
		cat.setCategoriesId(categoriesId);
//		entityManager.persist(categories);		
		entityManager.persist(cat);
	}

	@Override
	public void updateCategories(Categories categories) {
		Categories cat = getCategoriesById(categories.getCategoriesId());
		cat.setCatshowname(categories.getCatshowname());
		cat.setName(categories.getName());
		cat.setParentid(categories.getParentid());
		cat.setTexttip(categories.getTexttip());
		entityManager.flush();		
	}

	@Override
	public void deleteCategories(String id) {
//	public void deleteCategories(UUID id) {
		entityManager.remove(getCategoriesById(id));
	}

	@Override
	public boolean categoriesExists(String id) {
//	public boolean categoriesExists(UUID id) {
//		String hql = "FROM Categories as cat WHERE cat.categoriesId = ?";
		String hql = "SELECT u FROM Categories u WHERE categoriesId=:x";
		//tolong perhatikan betul field name nya ya!!! 
		int count = entityManager.createQuery(hql).setParameter("x", id)
		              .getResultList().size();
		return count > 0 ? true : false;
	}
	
}
