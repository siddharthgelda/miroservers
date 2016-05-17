package app.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import app.location.Geolocation;
import app.model.Shop;
import app.repository.ShopRepository;
import app.validator.Requestvalidation;

@Service
public class ShopManager {
	@Autowired
	private ShopRepository shopRepository;

	private Requestvalidation requestValidation;
	
	public Map<String, Object> createShop(Shop shop) throws Exception {
requestValidation= new Requestvalidation();
		requestValidation.createShopRequestValidate(shop);
		Geolocation location=new Geolocation();
		try {
		Map geoLocations=location.findLocation(shop.getPostCode()+" "+shop.getCountry());
		shop.setLatitude(geoLocations.get("lat").toString());
		shop.setLongitude(geoLocations.get("long").toString());
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			shop.setLatitude("0");
			shop.setLongitude("0");
			
		}
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("status", "201");
		
		response.put("message", "Shop created successfully");
		response.put("shop", shopRepository.save(shop));
		return response;
	}


	public Shop getshopDetails(double latitude, double longitude) throws Exception {
		List <Shop> shopsList=shopRepository.findAll();
		ListIterator<Shop> iterator=shopsList.listIterator();
		Geolocation location=new Geolocation();
		 TreeMap<Shop,Double> shopdistance=new TreeMap<Shop,Double>();  
		while(iterator.hasNext())
		{
		Shop shop=iterator.next();
		Double distance;
		try {
			System.out.println("user current coordinate= "+latitude+""+longitude);
			System.out.println("shop coordinate="+ Double.parseDouble(shop.getLatitude())+""+Double.parseDouble(shop.getLongitude()));
			distance = location.distance(latitude,longitude, Double.parseDouble(shop.getLatitude()), Double.parseDouble(shop.getLongitude()));
			System.out.println(distance);
			shopdistance.put(shop, distance);
			
		} catch (NumberFormatException e) {
			System.out.println(e);
			e.printStackTrace();
		System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();	
		}
		}
		if(!shopdistance.isEmpty())
		{return shopdistance.firstKey();}
		else{
			throw new Exception();
			}
	}

}
