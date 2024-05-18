package p3;
/**
 * p3MorrisonAndrew40108063
 */
import java.util.Objects;

public class Accommodation {
	
	/**
	 * various constants used to define ranges of variables  related to business rules
	 */

	private static final int MIN_NAME_LENGTH = 1;

	private static final int MIN_STARS = 1;

	private static final int MAX_STARS = 5;

	private static final int MIN_CITY_NAME_LENGTH = 1;

	private static final double MIN_PRICE = 0.01;

	private static final int MIN_ROOMS = 1;

	/**
	 * variable that relates to the current accomodations name;
	 */
	private String name;

	/**
	 * variable that relates to the current accomodations type;
	 */
	private Type type;
	/**
	 * variable that relates to the current accomodations rating in number of stars;
	 */
	private int stars;
	/**
	 * variable that relates to the current accomodations city (location);
	 */
	private String city;
	/**
	 * variable that relates to the price of the current accomodations ;
	 */
	private double price;
	/**
	 * variable that relates to the number of rooms current accomodations name;
	 */
	private int rooms;

	/**
	 * default constructor takes no args
	 */

	public Accommodation() {

	}

	/**
	 * constructor with args , calls revelvant setters
	 * 
	 * @param name
	 * @param type
	 * @param stars
	 * @param city
	 * @param price
	 * @param rooms
	 */

	public Accommodation(String name, Type type, int stars, String city, double price, int rooms) {

		this.setName(name);
		this.setType(type);
		this.setStars(stars);
		this.setCity(city);
		this.setPrice(price);
		this.setRooms(rooms);
	}

	/**
	 * returns the current value of name as a string
	 * @return name
	 */
	public String getName() {
		return name;
	}

/**
 * Sets the variable name to the argument passed if within legal range. throws an illegal argument exception otherwise
 * @param name
 * @throws IllegalArgumentException
 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || name.length() < MIN_NAME_LENGTH) {
			throw new IllegalArgumentException(
					"Invalid name enterd- must be a non empty string with at least one character");
		} else {
			this.name = name;
		}
	}
/**
 * returns the current type of the accomodation
 * @return type
 */
	public Type getType() {
		return type;
	}
/**
 * assigns the type of th accommodation with the argument passed
 * @param type
 */
	public void setType(Type type) {
		this.type = type;
	}
/**
 * returns the rating of the current accommodation in stars, as an int
 * @return stars
 */
	public int getStars() {
		return stars;
	}
/**
 * assigns stars with the argument passed if a int within the defined range. otherwise throws an illegal Argument exception
 * @param stars
 * @throws IllegalArgumentException
 */
	public void setStars(int stars) throws IllegalArgumentException {
		if (stars < MIN_STARS || stars > MAX_STARS) {
			throw new IllegalArgumentException("Invalid number of stars - must be between 1 and 5 inclusive ");
		} else {
			this.stars = stars;
		}
	}
	/**
	 * returns the city of the current accomodation as a string
	 * @return city
	 */
public String getCity() {
	return city;
}

@Override
	public int hashCode() {
		return Objects.hash(city, name, price, rooms, stars, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accommodation other = (Accommodation) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && rooms == other.rooms
				&& stars == other.stars && type == other.type;
	}

/**
 * Sets the variable City to the argument passed if within legal range. throws an illegal argument exception otherwise
 * @param City
 * @throws IllegalArgumentException
 */
public void setCity(String city)throws IllegalArgumentException {
	if(city==null|| city.length()<MIN_CITY_NAME_LENGTH) {
		throw new IllegalArgumentException("INVALID CITY NAME");
	}else {
	this.city = city;}
}
/**
 * returns the current value of the variable price as a double
 * @return price
 */
public double getPrice() {
	return price;
}
/**
 * Sets the variable price to the argument passed if within legal range. throws an illegal argument exception otherwise
 * @param price
 * @throws IllegalArgumentException
 */

public void setPrice(double price) throws IllegalArgumentException {
	if (price < MIN_PRICE) {
		throw new IllegalArgumentException("Invalid price - outside legal range");
	} else {
		this.price = price;
	}
}
/**
 * returns the current value of the variable room as an int
 * @return room
 */
public int getRooms() {
	return rooms;
}
/**
 * Sets the variable room to the argument passed if within legal range. throws an illegal argument exception otherwise
 * @param room
 * @throws IllegalArgumentException
 */
public void setRooms(int rooms)throws IllegalArgumentException {
	if(rooms<MIN_ROOMS) {
		throw new IllegalArgumentException("Rooms must be a non negative whole number");
	}else {
	this.rooms = rooms;
}
	}
/**
 * prints to the console all details of the current room
 */
public void printDetails() {
	String starString = "*".repeat(stars);
	System.out.printf("%-10s %s", "Name:",name);
	System.out.printf("\n%-10s %s", "City:",city);
	System.out.printf("\n%-10s %s", "Type:",type);
	System.out.printf("\n%-10s %d Rooms @ £%.2f" , "Capacity:",rooms,price);
	System.out.printf("\n%-10s %s", "Rating:",starString);
	System.out.println();
}
}
