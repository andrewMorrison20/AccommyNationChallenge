package p3;
/**
 * p3MorrisonAndrew40108063
 */
import java.util.Comparator;

public class CompareByCityName implements Comparator<Accommodation> {

	@Override
	public int compare(Accommodation o1, Accommodation o2) {
		// TODO Auto-generated method stub
		return o1.getCity().compareTo(o2.getCity());
	}

	

}
