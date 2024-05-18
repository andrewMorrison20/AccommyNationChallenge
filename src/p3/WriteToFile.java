package p3;
/**
 * p3MorrisonAndrew40108063
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteToFile implements Runnable {

	private Map<String, Double> avPriceMap;

	public WriteToFile(Map<String, Double> avPriceMap) {
		this.setAvPriceMap(avPriceMap);
	}
/**
 * assigns avPriceMap with passed arg if valid throws illegal argument exceptioon otherwise
 * @param avPriceMap
 * @throws IllegalArgumentException
 */
	private void setAvPriceMap(Map<String, Double> avPriceMap) throws IllegalArgumentException {
		if(avPriceMap==null) {
			
			throw new IllegalArgumentException("INVALID ENTRY");}
			else
			{this.avPriceMap=avPriceMap;}
		}
		
	

	@Override
	public void run() {
		WriteToFile();
		
			
		
	}
/**
 * write avPriceMap to new file
 */
	private void WriteToFile() {
		// TODO Auto-generated method stub
		
		try {
			
			File newFile = new File("averageCosts.csv");
			FileWriter fw = new FileWriter(newFile);
			BufferedWriter bw = new BufferedWriter(fw);
		if(!newFile.exists()) {
			newFile.createNewFile();
		}
			
			//header
		
			String lineToWrite = "City,Average Price";
			//write header
			bw.write(lineToWrite);
			bw.newLine();
			
			//write each line of the map
			for(String s : avPriceMap.keySet()) {
				lineToWrite = String.format("%s,%.2f",s,avPriceMap.get(s) );
				bw.write(lineToWrite);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
