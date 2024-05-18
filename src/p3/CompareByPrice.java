package p3;
/**
 * p3MorrisonAndrew40108063
 */
import java.util.Comparator;

public class CompareByPrice implements Comparator<Accommodation> {

	@Override
	public int compare(Accommodation o1, Accommodation o2) {
		// TODO Auto-generated method stub
		if (o1.getPrice() > o2.getPrice()) {
			return 1;
		} else if (o2.getPrice() > o1.getPrice()) {
			return -1;
		} else {
			return 0;
		}
	}

}
