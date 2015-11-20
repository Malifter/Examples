package examples.projecteuler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CountingSundays {

	public static void main(String [] args) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.set(1901, 0, 1);
		to.set(2000, 11, 31);
		System.out.println("The number of Sundays that fell on the first of the month from " +
				LocalDate.of(from.get(Calendar.YEAR), from.get(Calendar.MONTH)+1, from.get(Calendar.DAY_OF_MONTH)).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
				+ " to " +
				LocalDate.of(to.get(Calendar.YEAR), to.get(Calendar.MONTH)+1, to.get(Calendar.DAY_OF_MONTH)).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
				+ " is: " +  countSundaysSimple(from, to));
	}
	
	public static int countSundaysSimple(Calendar from, Calendar to) {
		// get to first sunday
		while(from.get(Calendar.DAY_OF_WEEK) != 1) {
			from.set(Calendar.DATE, from.get(Calendar.DAY_OF_WEEK)+1);
		}
		
		int sundays = 0;
		// now we just keep count
		while(!to.before(from)) {
			if(from.get(Calendar.DAY_OF_MONTH) == 1 && from.get(Calendar.DAY_OF_WEEK) == 1) sundays++;
			from.set(Calendar.DATE, from.get(Calendar.DAY_OF_MONTH)+7);
		}
		return sundays;
	}
	
	
	private static enum Month {
		JANUARY,
		FEBRUARY,
		MARCH,
		APRIL,
		MAY,
		JUNE,
		JULY,
		AUGUST,
		SEPTEMBER,
		OCTOBER,
		NOVERMBER,
		DECEMBER;
		
		public static int numDays(int year, int month) {
			switch(values()[month]) {
				case FEBRUARY:
					return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
				case APRIL: case JUNE: case SEPTEMBER: case NOVERMBER:
					return 30;
				case JANUARY: case MARCH: case MAY: case JULY: case AUGUST: case OCTOBER: case DECEMBER:
					return 31;
				default: return -1;
			}
		}
	}
}
