package schedule;

import java.util.Calendar;
import java.util.Comparator;

public class dayOfWeekComparator implements Comparator<Calendar> {

	@Override
	public int compare(Calendar c1, Calendar c2) {
		if(c1.get(Calendar.DAY_OF_WEEK) < c2.get(Calendar.DAY_OF_WEEK)) {
			return -1;
		}
		else if(c1.get(Calendar.DAY_OF_WEEK) > c2.get(Calendar.DAY_OF_WEEK)) {
			return 1;
		}
		return 0;
	}

}
