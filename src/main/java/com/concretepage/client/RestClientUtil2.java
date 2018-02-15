package com.concretepage.client;

import java.net.URI;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.concretepage.entity.Article;
//import com.concretepage.entity.Categories;
import org.natha.pos.entity.Categories;

public class RestClientUtil2 {
    private HttpHeaders getHeaders() {
    	String credential="mukesh:m123";
    	//String credential="tarun:t123";
    	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     	headers.add("Authorization", "Basic " + encodedCredential);
    	return headers;
    }
    
    public void getArticleByIdDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article.class, 1);
        Article article = responseEntity.getBody();
        System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                 +", Category:"+article.getCategory());      
    }

    public void getAllArticlesDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/articles";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Article[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Article[].class);
        Article[] articles = responseEntity.getBody();
        for(Article article : articles) {
              System.out.println("Id:"+article.getArticleId()+", Title:"+article.getTitle()
                      +", Category: "+article.getCategory());
        }
    }
    
    public void addArticleDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
//	String url = "http://localhost:8080/user/article";
        String url = "http://localhost:7070/user/article";
	Article objArticle = new Article();
	objArticle.setTitle("Glass Fish Spring REST Security using Hibernate");
	objArticle.setCategory("GF Spring");
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    
    public void updateArticleDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article";
	Article objArticle = new Article();
	objArticle.setArticleId(1);
	objArticle.setTitle("Update:Java Concurrency");
	objArticle.setCategory("Java-Bali");
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteArticleDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<Article> requestEntity = new HttpEntity<Article>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    
    public void addCategoryDemo() {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	
        String url = "http://localhost:7070/user/addcategories";
	
        org.natha.pos.entity.Categories objCat = new org.natha.pos.entity.Categories();
	
	objCat.setCategoriesId("Laptop XYZ");
//	UUID x = new UUID(1,1000000);
	
//	objCat.setCategoriesId(x);
	objCat.setName("XYZ Laptop Lenovo G405");
	objCat.setParentid("Laptop");
	objCat.setTexttip("Laptop Lenovo G405");
	objCat.setCatshowname(true);
	
        HttpEntity<Categories> requestEntity = new HttpEntity<Categories>(objCat, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    
    public static void main(String args[]) {
    	RestClientUtil2 util = new RestClientUtil2();
        //util.getArticleByIdDemo();
//        util.getAllArticlesDemo();
//    	util.addArticleDemo();
//    	util.updateArticleDemo();
    	//util.deleteArticleDemo();
    	
    	//Category Product
    	util.addCategoryDemo();
    }    
} 