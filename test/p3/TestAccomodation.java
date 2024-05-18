package p3;
/**
 * p3MorrisonAndrew40108063
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestAccomodation {
	String validName, validCity, invalidName, invalidCity;
	int validStarsMin, validStarsMid, validStarsMax, invalidStarsMin, invalidStarsMax;
	double validPrice, invalidPrice;
	int validRooms, invalidRooms;
	Accommodation accom;
	Type type;

	@BeforeEach
	void setUp() throws Exception {
	validName ="a";
	validCity="a";
	invalidName= "";
	invalidCity="";
	validStarsMin = 1;
	validStarsMid=3;
	validStarsMax=5;
	invalidStarsMin=0;
	invalidStarsMax=6;
    validPrice=0.01;
	invalidPrice=0.00;
	validRooms=1;
	invalidRooms=0;
	
	accom = new Accommodation();
	}

	
	
	@Test
	void testDefaultConstructor() {
		assertNotNull(accom);
	}

	@Test
	void testConstructorWithArgsValid() {
		accom = new Accommodation(validName, Type.HOTEL, validStarsMin,validCity, validPrice, validRooms);
		assertEquals(validName, accom.getName());
		assertEquals(Type.HOTEL, accom.getType());
		assertEquals(validStarsMin, accom.getStars());
		assertEquals(validCity, accom.getCity());
		assertEquals(validPrice, accom.getPrice(),0.01);
		assertEquals(validRooms, accom.getRooms());
	
	
	}

	@Test
	void testConstructorWithArgsInvalidExpecsException() {
		assertThrows(IllegalArgumentException.class, ()->{
			accom = new Accommodation(invalidName, Type.HOTEL, validStarsMin,validCity, validPrice, validRooms);
			
		});
		assertThrows(IllegalArgumentException.class, ()->{
			accom = new Accommodation(validName, Type.HOTEL, invalidStarsMin,validCity, validPrice, validRooms);
			
		});
		assertThrows(IllegalArgumentException.class, ()->{
			accom = new Accommodation(validName, Type.HOTEL, validStarsMin,invalidCity, validPrice, validRooms);
			
		});
		assertThrows(IllegalArgumentException.class, ()->{
			accom = new Accommodation(validName, Type.HOTEL, validStarsMin,validCity, invalidPrice, validRooms);
			
		});
		assertThrows(IllegalArgumentException.class, ()->{
			accom = new Accommodation(validName, Type.HOTEL, validStarsMin,validCity, validPrice, invalidRooms);
			
		});
		
	}
	
	
	@Test
	void testSetName() {
		accom.setName(validName);
		assertEquals(validName,accom.getName());
	}

	@Test
	void testSetType() {
		accom.setType(Type.BNB);
		assertEquals(Type.BNB, accom.getType());
		accom.setType(Type.HOSTEL);
		assertEquals(Type.HOSTEL, accom.getType());
		accom.setType(Type.HOTEL);
		assertEquals(Type.HOTEL, accom.getType());
	}

	@Test
	void testGetSetStarsValid() {
		
		int expected, actual;
		expected = validStarsMin;
		accom.setStars(validStarsMin);
		actual= accom.getStars();
		assertEquals(expected, actual);
		
		expected = validStarsMid;
		accom.setStars(validStarsMid);
		actual= accom.getStars();
		assertEquals(expected, actual);
		
		expected = validStarsMax;
		accom.setStars(validStarsMax);
		actual= accom.getStars();
		assertEquals(expected, actual);
	}

	
	@Test
	void testSetInvalidStarsExpectsException() {
		assertThrows(IllegalArgumentException.class, ()->{
			accom.setStars(invalidStarsMin);
			
		});
	
		assertThrows(IllegalArgumentException.class, ()->{
			accom.setStars(invalidStarsMax);
			
		});
	}
	@Test
	void testSetCity() {
		accom.setCity(validCity);
		assertEquals(validCity,accom.getCity());;
	}

	@Test
	void testSetPrice() {
		accom.setPrice(validPrice);
		assertEquals(validPrice,accom.getPrice(),0.1);
	}

	@Test
	void testSetRooms() {
		accom.setRooms(validRooms);
		assertEquals(validRooms, accom.getRooms());
	}

}
