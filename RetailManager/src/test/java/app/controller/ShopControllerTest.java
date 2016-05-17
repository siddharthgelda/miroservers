package app.controller;


import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.Application;
import app.model.Shop;
import app.repository.ShopRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ShopControllerTest {

  //Required to Generate JSON content from Java objects
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  //Required to delete the data added for tests.
  //Directly invoke the APIs interacting with the DB
  @Autowired
  private ShopRepository shopRepository;
  
  //Test RestTemplate to invoke the APIs.
  private RestTemplate restTemplate = new TestRestTemplate();
  
  @Test
  public void testCreateShopApi() throws JsonProcessingException{
    
    //Building the Request body data
    Map<String, Object> requestBody = new HashMap<String, Object>();
    requestBody.put("name", "shop1");
    requestBody.put("number", "1");
    requestBody.put("postCode", "457001");
    requestBody.put("country", "India");
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

    //Creating http entity object with request body and headers
    HttpEntity<String> httpEntity = 
        new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
    
    //Invoking the API
    Map<String, Object> apiResponse = 
        restTemplate.postForObject("http://localhost:8888/shop", httpEntity, Map.class, Collections.EMPTY_MAP);

    assertNotNull(apiResponse);
    
    //Asserting the response of the API.
    String message = apiResponse.get("message").toString();
    assertEquals("Shop created successfully", message);
    String shopId = ((Map<String, Object>)apiResponse.get("shop")).get("id").toString();
    
    assertNotNull(shopId);
    
  }
  
  @Test
  public void testGetClosetShopApi(){
  try{
	   Map<String, Object> requestBody = new HashMap<String, Object>();
	    requestBody.put("name", "shop1");
	    requestBody.put("number", "1");
	    requestBody.put("postCode", "560063");
	    requestBody.put("country", "India");
	    HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

	    //Creating http entity object with request body and headers
	    HttpEntity<String> httpEntity = 
	        new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
	    
	    //Invoking the API
	    Map<String, Object> apiResponse = 
	        restTemplate.postForObject("http://localhost:8888/shop", httpEntity, Map.class, Collections.EMPTY_MAP);

	    assertNotNull(apiResponse);
	    
	    //Asserting the response of the API.
	    String message = apiResponse.get("message").toString();
	    assertEquals("Shop created successfully", message);
	    String shopId = ((Map<String, Object>)apiResponse.get("shop")).get("id").toString();
	    String lat = ((Map<String, Object>)apiResponse.get("shop")).get("latitude").toString();
		 
	    String longi = ((Map<String, Object>)apiResponse.get("shop")).get("longitude").toString();
		 
	    assertNotNull(shopId);

		   Map<String, Object> requestBody1 = new HashMap<String, Object>();
		    requestBody1.put("name", "shop1");
		    requestBody1.put("number", "1");
		    requestBody1.put("postCode", "457001");
		    requestBody1.put("country", "India");
		    HttpHeaders requestHeaders1 = new HttpHeaders();
		    requestHeaders1.setContentType(MediaType.APPLICATION_JSON);

		    //Creating http entity object with request body and headers
		    HttpEntity<String> httpEntity1 = 
		        new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody1), requestHeaders1);
		    
		    //Invoking the API
		    Map<String, Object> apiResponse1 = 
		        restTemplate.postForObject("http://localhost:8888/shop", httpEntity1, Map.class, Collections.EMPTY_MAP);

		    assertNotNull(apiResponse1);
		    
		    //Asserting the response of the API.
		    String message1 = apiResponse1.get("message").toString();
		    assertEquals("Shop created successfully", message1);
		    String shopId1 = ((Map<String, Object>)apiResponse1.get("shop")).get("id").toString();
		       
		    assertNotNull(shopId1);
		    String url="http://localhost:8888/shop/"+lat+"/"+longi;
		    System.out.println(url);
		    Map<String, Object> apiResponse2 =restTemplate.getForObject(url,Map.class, Collections.EMPTY_MAP); 
		    System.out.println(apiResponse2.keySet().toString());
			String postcode = apiResponse2.get("postCode").toString();
		    System.out.println("================"+postcode+"=================");
		   assertEquals(postcode,"560063" );
			
		    
  }catch(Exception e)
  {
	  e.printStackTrace();
  }
  }
  
}
