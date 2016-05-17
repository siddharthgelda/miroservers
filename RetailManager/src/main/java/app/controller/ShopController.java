package app.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.model.Shop;
import app.repository.ShopRepository;
import app.location.Geolocation;
import app.manager.ShopManager;
@RefreshScope
@EnableAutoConfiguration
@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopManager shopManager;
	 @Value ("${apikey}")
	String apikey;
	    
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createShop(@RequestBody Shop shop,HttpServletRequest htttpRequest, HttpServletResponse httpResponse) {
		try {
			Geolocation.API_KEY=apikey;
			httpResponse.setStatus(201);
			return shopManager.createShop(shop);
		} catch (Exception e) {
			Map<String, Object> response = new LinkedHashMap<String, Object>();
			response.put("status", "503");
			response.put("message", "Internal error");
			httpResponse.setStatus(503);
			e.printStackTrace();
			return response;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{latitude}/{longitude}")
	public Shop getShopDetails(@PathVariable("latitude") double latitude,@PathVariable("longitude") double longitude,HttpServletRequest htttpRequest, HttpServletResponse httpResponse) {
		try {
			Geolocation.API_KEY=apikey;
			
			httpResponse.setStatus(200);
			return shopManager.getshopDetails(latitude,longitude);
		} catch (Exception e) {
			httpResponse.setStatus(404);
			e.printStackTrace();
			Shop shop=new Shop();
			return shop;
		}
	}

	
	
}
