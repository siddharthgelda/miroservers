package app.location;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
@Component
@RefreshScope
@EnableAutoConfiguration
public class Geolocation {
	
	public static String API_KEY;
	//@Value ("${apikey}")
	//public static String API_KEY;
	public Map findLocation(String address) throws Exception {
		System.out.println("APIKey "+API_KEY);
		GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);

		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		if (results.length > 0) {
			System.out.println(results[0].geometry.location);
			Map<String, String> location = new HashMap();
			location.put("lat", ""+results[0].geometry.location.lat);
			location.put("long", ""+results[0].geometry.location.lng);
			return location;
		} else {
			throw new Exception("Location not found");
		}

	}
public long findGoogleDistance(double sourceLat,double sourceLong,double destinationLat,double destinationLong) throws Exception
{
	GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);
	DistanceMatrix results = DistanceMatrixApi.newRequest(context)
    .origins(new LatLng(sourceLat,sourceLong))
         .destinations(new LatLng(destinationLat,destinationLong))
    .await();
	System.out.println("==========="+results.rows[0].elements[0].status);
	System.out.println(results.toString());
	
if((results.rows[0].elements[0].status.equalsIgnoreCase("OK")))
{	System.out.println("distance ="+results.rows[0].elements[0].distance.inMeters);
	return 	results.rows[0].elements[0].distance.inMeters;
}else{
	throw new Exception(); 
}

}

public  double distance(double sourceLat, double SourceLon, double destionationLat, double destionationLon) {
	double theta = SourceLon - destionationLon;
	double dist = Math.sin(deg2rad(sourceLat)) * Math.sin(deg2rad(destionationLat)) + Math.cos(deg2rad(sourceLat)) * Math.cos(deg2rad(SourceLon)) * Math.cos(deg2rad(theta));
	dist = Math.acos(dist);
	dist = rad2deg(dist);
	dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
	
	return (dist);
}

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::	This function converts decimal degrees to radians						 :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
private  double deg2rad(double deg) {
	return (deg * Math.PI / 180.0);
}

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::	This function converts radians to decimal degrees						 :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
private  double rad2deg(double rad) {
	return (rad * 180 / Math.PI);
}
}
