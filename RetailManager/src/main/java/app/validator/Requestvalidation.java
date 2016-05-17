package app.validator;

import app.model.Shop;

public class Requestvalidation {
	public void createShopRequestValidate(Shop shop)throws Exception
	{
		if(shop.getName()== null || shop.getName().length() < 1 )
		{
			throw new Exception(); 
		}
		if(shop.getNumber()== null || shop.getNumber().length() <1)
		{
			throw new Exception(); 
				
		}
		if(shop.getCountry()== null || shop.getCountry().length() <1)
		{
			throw new Exception(); 
				
		}
		
	}
	public void findShopRequestValidate()
	{
		
	}
}
