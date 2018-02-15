package com.concretepage.controller;

import java.util.List;
import java.util.UUID;

//import org.natha.pos.entity.Categories;
//import org.natha.pos.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Categories;
import com.concretepage.service.ICategoriesService;

@Controller
@RequestMapping("user")
public class CategoriesController {
	@Autowired
	private ICategoriesService categoriesService;
	
	@GetMapping("categories/{id}")
	public ResponseEntity<Categories> getCategoriesById(@PathVariable("id") String id) {
//	public ResponseEntity<Categories> getCategoriesById(@PathVariable("id") UUID id) {
		Categories categories = categoriesService.getCategoriesById(id);
		return new ResponseEntity<Categories>(categories, HttpStatus.OK);
	}
	
	@GetMapping("categories")
	public ResponseEntity<List<Categories>> getAllCategories() {
		List<Categories> list = categoriesService.getAllCategories();
		return new ResponseEntity<List<Categories>>(list, HttpStatus.OK);
	}
	
	@PostMapping("addcategories")
	public ResponseEntity<Void> addCategories(@RequestBody org.natha.pos.entity.Categories categories, UriComponentsBuilder builder) {
        
		Categories x = new Categories(); 
		x.setCategoriesId(categories.getCategoriesId());
		x.setCatshowname(categories.getCatshowname());
		x.setName(categories.getName());
		x.setParentid(categories.getParentid());
		x.setTexttip(categories.getTexttip());
		
//		boolean flag = categoriesService.addCategories(x); //x
		boolean flag = categoriesService.addCategories(categories);	//cobain langsung..!!!
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/categories/{id}").buildAndExpand(categories.getCategoriesId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/**/
	@RequestMapping(value = "/addcategorieslist", method = RequestMethod.POST)
	public ResponseEntity<List<org.natha.pos.entity.Categories>> update(@RequestBody List<org.natha.pos.entity.Categories> catList) {

//	    cars.stream().forEach(c -> c.setMiles(c.getMiles() + 100));
		for (int i=0; i < catList.size(); i++){
			org.natha.pos.entity.Categories x = new org.natha.pos.entity.Categories(); 
			x.setCategoriesId(catList.get(i).getCategoriesId());
			x.setCatshowname(catList.get(i).getCatshowname());
			x.setName(catList.get(i).getName());
			x.setParentid(catList.get(i).getParentid());
			x.setTexttip(catList.get(i).getTexttip());
			
//			boolean flag = categoriesService.addCategories(categories); //x
			boolean flag = categoriesService.addCategories(x);
	        
		}
	    // TODO: call persistence layer to update
	    return new ResponseEntity<List<org.natha.pos.entity.Categories>>(catList, HttpStatus.OK);
	}
	/**/
	
	@PutMapping("categories")
	public ResponseEntity<Categories> updateCategories(@RequestBody Categories categories) {
		categoriesService.updateCategories(categories);
		return new ResponseEntity<Categories>(categories, HttpStatus.OK);
	}
	
	@DeleteMapping("categories/{id}")
	public ResponseEntity<Void> deleteCategories(@PathVariable("id") String id) {
//	public ResponseEntity<Void> deleteCategories(@PathVariable("id") UUID id) {
		
		categoriesService.deleteCategories(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
