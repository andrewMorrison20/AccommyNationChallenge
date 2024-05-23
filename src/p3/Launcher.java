package p3;
/**
 * p3MorrisonAndrew40108063
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Launcher {

	private static Scanner scanner = new Scanner(System.in);
	private static final int QUIT = 10;

	public static void main(String[] args) {
		launchMenu();
		scanner.close();// close scanner once menu system completes
	}

	/**
	 * Launches menu system which in turn calls appropriate methods based on user
	 */
	public static void launchMenu() {
		List<Accommodation> mainList = readRoomData("Rooms.csv");
		Map<String , Double > avPriceMap = new LinkedHashMap();

		System.out.println();
		int option;
		System.out.println("AccommyNation.com - Wherever you go, you'll need to stay");

		// repeat until quit chosen
		do {
			displayOptions();

			// get input
			option = getMenuChoice("Enter choice ...");
			System.out.println();

			try {
				// process choice - invoke relevant methods etc.
				switch (option) {

				case 1:
					mainList = readRoomData("Rooms.csv");
					break;
				case 2:
					System.out.println("Number of places to stay is " + mainList.size());
					break;
				case 3:
					displayAll(mainList);
					//TODO add required method calls etc
					break;
				case 4:
					//get all accom in LA
					List<Accommodation> cityList = searchBycity(mainList,"Los Angeles");
					//refine to only 4 stars
					List <Accommodation>starsList = refineByRating(cityList,4);
					//return a sublist of correct size (3) ordered by price
					List<Accommodation> searchResults = sortByAttribute(starsList,3,"price");
					//print details
					
					displayAll(searchResults);
					
					//TODO add required method calls etc
					break;
				case 5:
					ShowCountUniqueCityNames(mainList);
					break;
				case 6:
					//get all bnbs
					List<Accommodation> allBnbs = searchByType(mainList , Type.BNB);
					//get List of all dublin bnbs
					List<Accommodation> dublinList = searchBycity(allBnbs, "dublin");
					//sort all dublin bnbs by price
					List<Accommodation> searchList = sortByAttribute(dublinList, dublinList.size(),"price");
					
					displayAll(getSublist(searchList, 4));
					
					
					//TODO add required method calls etc
					break;
				case 7:
					
					System.out.println();
					List<Accommodation> allHotels = searchByType(mainList, Type.HOTEL);
					
					createAvPriceMap(allHotels,avPriceMap);
				
					System.out.printf("Average Price New York : £%.2f" ,avPriceMap.get("New York"));
					
				//	averavgePrice()
					break;
				case 8:
					
					removeByCity(mainList,"paris");
				    removeByOccupancy(mainList, 10);
					
					break;
				case 9:
					createAvPriceMap(sortByAttribute(searchByType(mainList, Type.HOTEL), mainList.size(), "city"),avPriceMap);
					callTowriter( avPriceMap);
					
					break;
				case QUIT:
					System.out.println("Quitting");
					break;
				default:
					System.out.println("Try options again...");
				}

			} catch (Exception e) {
				System.out.println("Exception caught");
				System.out.println(e.getMessage());
				System.out.println("please try again");
			}

		} while (option != QUIT);
	}
/**
 * creates a writer class and then creates a thread and starts the thread
 * @param avPriceMap
 */
	public static void callTowriter(Map<String, Double> avPriceMap) throws IllegalArgumentException{
		if(avPriceMap==null) {throw new IllegalArgumentException("INVALID MAP");}else {
		// TODO Auto-generated method stub
		WriteToFile writer = new WriteToFile(avPriceMap);
		Thread t = new Thread(writer);
		t.start();
	}}
/**
 * creates a map of the cities in the main list and their average price
 * @param mainList
 * @param avPriceMap
 * @throws IllegalArgumentException
 */
	public static void createAvPriceMap(List<Accommodation> mainList,Map<String,Double> avPriceMap) throws IllegalArgumentException{
	if(mainList == null ||avPriceMap==null) {
		throw new IllegalArgumentException("INVALID INPUTS");
	}else {
	
		for(Accommodation a : mainList) {
			
			int cityCount = getTotalOccurence(mainList,a.getCity());
			
			if(!avPriceMap.containsKey(a.getCity())) {
				
				avPriceMap.put(a.getCity(), a.getPrice()/cityCount);
				
			}else {
				
				double oldAv = avPriceMap.get(a.getCity());
				double newAv =((oldAv*cityCount) +a.getPrice())/(cityCount);
				avPriceMap.put(a.getCity(), newAv);
			}
			
		}}
		
	}

	/**
	 * returns the total occurences of the passed city in the passed list as an int, throws illegalArgumentException if any null entries
	 * @param mainList
	 * @param city
	 * @return count
	 * @throws IllegalArgumentException
	 */
	private static int getTotalOccurence( List<Accommodation> mainList,String city) throws IllegalArgumentException {
		if(mainList==null || city==null) {
			throw new IllegalArgumentException("INVALID ENTRIES");
		}else {
		int count=0;
		for(Accommodation a : mainList) {
			if (a.getCity().equalsIgnoreCase(city)) {
				count++;
			}
		}
		return count;}
	}

	/**
	 * removes the given city from the passed list. Throws illegal argument exception if either value is null
	 * @param mainList
	 * @param city
	 * @throws IllegalArgumentException
	 */
	private static void removeByCity(List<Accommodation> mainList, String city) throws IllegalArgumentException{
		if(mainList==null || city==null) {throw new IllegalArgumentException("INVALID ENTRIES");}
		else {
		
		List<Accommodation> copyList = new ArrayList<Accommodation>(mainList);
		mainList.clear();
		for(Accommodation a : copyList) {
		if(!a.getCity().equalsIgnoreCase("city")) {
			mainList.add(a);
			}
		}
		}
		
	}
	
/**
 * removes the accommodation in the current list that has a value of room less than the passed integer in the list
 * throws illegal Argument exception if i is less than 0 or list is null
 * @param mainList
 * @param i
 * @throws IllegalArgumentException
 */
	public static void removeByOccupancy(List<Accommodation> mainList, int i)  throws IllegalArgumentException{
	
		if(i<0 ||mainList==null) {throw new IllegalArgumentException("INVALID INPUT");}
	else {	List<Accommodation> copyList = new ArrayList<Accommodation>(mainList);
		mainList.clear();
		for(Accommodation a : copyList) {
		if(a.getRooms()>=i) {
			mainList.add(a);
			}
		}

	}
	}
/**
 * returns a sublist pf the searchlist of the given size
 * @param searchList
 * @param size
 * @return subList
 * @throws IllegalArgumentException
 */
	private static List<Accommodation> getSublist(List<Accommodation> searchList, int size) throws IllegalArgumentException{
		if(searchList==null || size<0 ||size>searchList.size()) {
			throw new IllegalArgumentException("INVALID INPUTS");
		}
	else {
		List<Accommodation> subList = new ArrayList<Accommodation>();
		Collections.reverse(searchList);
		for(int i = 0 ; i <size; i++) {
			subList.add(searchList.get(i));
		}
		return subList;
	}}

	
	/**
	 * reutrns a sublist of the passed list that matches the passed type, throws an illegalArgumentException if any null parameters
	 * @param mainList
	 * @param type
	 * @return results
	 * @throws IllegalArgumentException
	 */
	public static List<Accommodation> searchByType(List<Accommodation> mainList, Type type) throws IllegalArgumentException {
		List<Accommodation> results = new ArrayList<Accommodation>();
		if(mainList==null || type ==null ) {
			throw new IllegalArgumentException();
		}else
		{for(Accommodation a : mainList) {
			if(a.getType()==type) {
				results.add(a);
			}
		}}
		return results;
	}
/**
 * prints out the number of unique occurences of city for the given list, throws an illegalArgumentException if list is null
 * @param mainList
 * @throws IllegalArgumentException
 */
	private static void ShowCountUniqueCityNames(List<Accommodation> mainList)throws IllegalArgumentException {
		Set<Accommodation> uniqueNames = new TreeSet<>(new CompareByCityName());
		if(mainList==null) {
			throw new IllegalArgumentException("INVALID ENTRY");
		}else {
			uniqueNames.addAll(mainList);
		}
		System.out.println("Number of Cities: " + uniqueNames.size());
	}
/**
 * returns a list that is sorted depending on the passed stat, list will be a sublist of size "size" where size can be anything up to an including the orignal size
 * throws illegal argument exception if list passed is null, size is 0 or the size passed is greater than the original list
 * @param mainList
 * @param size
 * @param stat
 * @return results
 * @throws IllegalArgumentException
 */
	public static List<Accommodation> sortByAttribute(List<Accommodation> mainList, int size, String stat)throws IllegalArgumentException {
		
		List<Accommodation> copyList = new ArrayList<Accommodation>(mainList);
		List<Accommodation> results = new ArrayList<Accommodation>();
		
		
		if(mainList==null || size==0 || size>mainList.size() || stat ==null) {
			throw new IllegalArgumentException("INVALID INPUTS");}
			else {
				switch (stat.toLowerCase()) {
				case "city":
					copyList.sort(new CompareByCityName());
					break;
				case "price":
					copyList.sort(new CompareByPrice());
					break;
				default : 
					System.err.println("Invalid stat entry");
					
					
				}
				for(int i = 0 ; i <size ; i++) {
					results.add(copyList.get(i));
				}
				
				
			} return results;
		}
		// TODO Auto-generated method stub
		
	
/**
 * returns a sublist of the passed list (city List) that contains only objects that match the number of stars cirteria
 * @param cityList
 * @param stars
 * @return results
 */
	public static List<Accommodation> refineByRating(List<Accommodation> cityList, int stars) {
		List<Accommodation> results = new ArrayList<Accommodation>();
		for(Accommodation a : cityList) {
			if(a.getStars()==stars) {
				results.add(a);
			}
		}
		return results;
		
	}
/**
 * returns a sublist of the mainList passed that contains only objects that satisfy the city passed
 * @param mainList
 * @param city
 * @return results
 * @throws IllegalArgumentException
 */
	public static List<Accommodation> searchBycity(List<Accommodation> mainList, String city) throws IllegalArgumentException {
		List<Accommodation> results = new ArrayList<Accommodation>();
		
	if(mainList ==null || city==null) {
		throw new IllegalArgumentException("invalid inputs");
		}
	for(Accommodation a : mainList) {
		if(a.getCity().equalsIgnoreCase(city)) {
			results.add(a);
		}
	}
	
		return results;
	}
/**
 * displays the details of all objects in the current list passed
 * @param mainList
 */
	public static void displayAll(List<Accommodation> mainList) {
		// TODO Auto-generated method stub
		int count =1;
		for(Accommodation accom: mainList) {
			System.out.println("\n"+count+")");
			accom.printDetails();
			count++;
		}
		
	}

	/**
	 * Read data from a given filename for a formatted csv file of accommodation
	 * data
	 * 
	 * @param filename
	 * @return list of accommodation objects for each row of the file containing
	 *         valid data
	 */
	public static List<Accommodation> readRoomData(String fileName) {
		List<Accommodation> rooms = new ArrayList<Accommodation>();
		List<String> allLines = new ArrayList<String>();

		try {
			File myFile = new File(fileName);
			FileReader fr;
			fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			// check for header data
			if (line.contains("name,type,star_rating,city,price_per_night,available_rooms")) {
				// discard header
				line = br.readLine();
			}
			while(line!=null) {
				allLines.add(line.trim());
				line=br.readLine();
			}
			br.close();
			fr.close();
			processLines(allLines,rooms);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// TODO update method to read from formatted csv
		// TODO create an Accommodation object for each line of the file and add to
		// rooms list
		// TODO skip any lines which contain data which doesnt match business rules

		System.out.println(rooms.size() + " entries read successfully");
		return rooms;
	}
/**
 * processes the lines in the passed arraylist and creates corresponding objects and adds them to list of rooms
 * @param allLines
 * @param rooms
 */
	public static void processLines(List<String> allLines, List<Accommodation> rooms) {

		for(String line:allLines) {
			try {
				String[] parts = line.split(",");
				String name = parts[0];
				Type type = Type.valueOf(parts[1].toUpperCase());
				int stars= Integer.parseInt(parts[2]);
				String city = parts[3].trim();
				double price = Double.parseDouble(parts[4]);
				int room = Integer.parseInt(parts[5]);
				
				Accommodation accom = new Accommodation(name, type, stars, city, price, room);
				rooms.add(accom);
				
				
			}catch(Exception e ) {
				System.out.println(rooms.size()+"lines read and corresponding object created succesfully");
				System.out.println("Encountered bad data - skipping line");
				//e.printStackTrace();
			}		
		}
	}

	/**
	 * Display prompt and get int user input via static scanner repeat if invalid
	 * input type given - no need to modify this method
	 * 
	 * @return int value entered via scanner
	 */
	private static int getMenuChoice(String prompt) {
		try {
			System.out.println(prompt);
			int choice = scanner.nextInt();
			return choice;
		} catch (Exception e) {
			System.out.println("Invalid input try again");
			// clear buffer if required
			if (scanner.hasNext()) {
				scanner.next();// read and discard line break
			}
			return getMenuChoice(prompt);// return recursive call to method to recover
		}
	}

	/**
	 * Writes menu options to console - no need to modify this method
	 */
	private static void displayOptions() {
		System.out.println();
		System.out.println("Menu Options:");
		System.out.println("1. (Re)read Data From Original File (use to restore removed data for example)");
		System.out.println("2. Display the number of places to stay in the current list");
		System.out.println("3. Display details for all places to stay in the current list");
		System.out.println(
				"4. Display details of the 3 least expensive 4 Star places to stay in Los Angeles (low to high)");
		System.out.println("5. Display the number of cities in the current list");
		System.out.println("6. Display details of the 4 most expensive BnBs in Dublin (high to low)");
		System.out.println("7. Display the average price of a hotel in New York");
		System.out.println("8. Remove all entries with fewer than 10 rooms available for Paris from the current list");
		System.out.println("9. (Separate Thread) Write out to a formatted csv, "
				+ "\nthe name of each city and the average price of a hotel there (2 decimal places, alphabetcically by City name)");
		System.out.println("10. Quit");
	}

}
