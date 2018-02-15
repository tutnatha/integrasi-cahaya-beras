package org.natha.possync;

import org.idempiere.webservice.client.base.Field;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.QueryDataRequest;
import org.idempiere.webservice.client.response.WindowTabDataResponse;
import org.idempiere.wsclienttest.AbstractTestWS;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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

public class ProductCategoryQueryData extends AbstractTestWS{

	@Override
	public String getWebServiceType() {
		// TODO Auto-generated method stub
		return "QueryProductCatagory";
	}

	@Override
	public void testPerformed() {
		// TODO Auto-generated method stub
		QueryDataRequest ws = new QueryDataRequest();
		ws.setWebServiceType(getWebServiceType());
		ws.setLogin(getLogin());
//		ws.setLimit(3);
//		ws.setOffset(3);

		List<org.natha.pos.entity.Categories> catList = new ArrayList<org.natha.pos.entity.Categories>();
		WebServiceConnection client = getClient();

		try {
			WindowTabDataResponse response = client.sendRequest(ws);

			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else {
				System.out.println("Total rows: " + response.getTotalRows());
				System.out.println("Num rows: " + response.getNumRows());
				System.out.println("Start row: " + response.getStartRow());
				System.out.println();
				//Looping ini bisa dipakai untuk insert ke tabel
				for (int i = 0; i < response.getDataSet().getRowsCount(); i++) {
					System.out.println("Row: " + (i + 1));
					
					org.natha.pos.entity.Categories cat = new org.natha.pos.entity.Categories();
					
					for (int j = 0; j < response.getDataSet().getRow(i).getFieldsCount(); j++) {
						Field field = response.getDataSet().getRow(i).getFields().get(j);
						System.out.println("Column: " + field.getColumn() + " = " + field.getStringValue());
						//Column current row:
						if (field.getColumn().equals("Name")){
							cat.setName(field.getStringValue());
						}else if (field.getColumn().equals("M_Product_Category_ID")){
							cat.setCategoriesId(field.getStringValue());
						}else if (field.getColumn().equals("Value")){
							cat.setTexttip(field.getStringValue());
						}else if (field.getColumn().equals("M_Product_Category_Parent_ID")){
//							cat.setParentid(field.getStringValue());
							//update parent nya buatkan proses khusus ya..!!!
							cat.setParentid("000"); //Default biar ga error constraint..^konon^??
						}						
					
					}//for j loop
					cat.setCatshowname(true);	
					catList.add(cat);
					System.out.println("Inserted : "+cat.getCategoriesId());
				}//for i loop
				
				System.out.println("catList Size : "+catList.size());
				//call web services
				addCategoryDemo(catList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCategoryDemo(List<org.natha.pos.entity.Categories> objCat) {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	
        String url = "http://localhost:7070/user/addcategorieslist";

        HttpEntity<List<org.natha.pos.entity.Categories>> requestEntity = new HttpEntity<List<org.natha.pos.entity.Categories>>(objCat, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }

	
	public void addCategoryDemo(org.natha.pos.entity.Categories objCat) {
    	HttpHeaders headers = getHeaders();  
        RestTemplate restTemplate = new RestTemplate();
	
        String url = "http://localhost:7070/user/addcategories";
	
//        org.natha.pos.entity.Categories objCat = new org.natha.pos.entity.Categories();
	
//	objCat.setCategoriesId("Laptop XYZ");
//	UUID x = new UUID(1,1000000);
	
//	objCat.setCategoriesId(x);
//	objCat.setName("XYZ Laptop Lenovo G405");
//	objCat.setParentid("Laptop");
//	objCat.setTexttip("Laptop Lenovo G405");
	objCat.setCatshowname(true);
	
        HttpEntity<Categories> requestEntity = new HttpEntity<Categories>(objCat, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }

	private HttpHeaders getHeaders() {
    	String credential="mukesh:m123";
    	//String credential="tarun:t123";
    	String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     	headers.add("Authorization", "Basic " + encodedCredential);
    	return headers;
    }
}
