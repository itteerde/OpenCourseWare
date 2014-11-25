package de.itter.euler;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Problem19 {

	public static void main(String[] args) {
		int sundays = 0;
		GregorianCalendar cal = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		end.set(2000, 12,30);
		cal.set(1901, 1,1);
		for(;;){
			if(end.before(cal)){
				break;
			}
			if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				sundays++;
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		System.out.println(sundays);
	}

}
