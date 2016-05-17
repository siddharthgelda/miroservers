package app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shop implements Comparable<Shop>{

	@Id
    @GeneratedValue
  private String id;
  private String name;
  private String number;
  private String postCode;
  private String longitude;
  private String latitude;
  private String country;
  
  
  
  public Shop()
  {
	  super();
  }
public Shop( String name, String number, String postCode,String country) {
	super();
	
	this.name = name;
	this.number = number;
	this.postCode = postCode;
	this.country=country;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getPostCode() {
	return postCode;
}
public void setPostCode(String postCode) {
	this.postCode = postCode;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
@Override
public String toString() {
	return "Shop [id=" + id + ", name=" + name + ", number=" + number + ", postCode=" + postCode + ", longitude="
			+ longitude + ", latitude=" + latitude + ", country=" + country + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((country == null) ? 0 : country.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
	result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Shop other = (Shop) obj;
	if (country == null) {
		if (other.country != null)
			return false;
	} else if (!country.equals(other.country))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (latitude == null) {
		if (other.latitude != null)
			return false;
	} else if (!latitude.equals(other.latitude))
		return false;
	if (longitude == null) {
		if (other.longitude != null)
			return false;
	} else if (!longitude.equals(other.longitude))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (number == null) {
		if (other.number != null)
			return false;
	} else if (!number.equals(other.number))
		return false;
	if (postCode == null) {
		if (other.postCode != null)
			return false;
	} else if (!postCode.equals(other.postCode))
		return false;
	return true;
}

public int compareTo(Shop shop) {
	if(this.id== shop.id)
		return 0;
	else
		return 1;
		
}

}
