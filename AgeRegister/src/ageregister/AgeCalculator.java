/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageregister;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Oshan
 */
public class AgeCalculator {
    
	int prev_month;
	int diff_days;
	int diff_months;
	int diff_years;
        Date date;
        
	public AgeCalculator(Date today){
            this.date=today;
        }
        
	
	private static Boolean isleapyear(int year) {
	    if(year%400==0) {
	        return true;
	    }
	    else if(year%4==0 && year%100!=0) {
	        return true;
	    }
	    else {
	        return false;
	    }
	}
	
	public Boolean calc(String d2) {
                SimpleDateFormat sm= new SimpleDateFormat("yyyy-MM-dd");
                String d1=sm.format(date);
                
		Integer year1=Integer.parseInt(d1.split("-")[0]);
		Integer month1=Integer.parseInt(d1.split("-")[1]);
		Integer day1=Integer.parseInt(d1.split("-")[2]);
				
		
		Integer year2=Integer.parseInt(d2.split("-")[0]);
		Integer month2=Integer.parseInt(d2.split("-")[1]);
		Integer day2=Integer.parseInt(d2.split("-")[2]);
		
		if(day1<day2) {
			switch(month1){
			case 3:
				if(AgeCalculator.isleapyear(year1)) {
					prev_month=29;
				}
				else {
				prev_month=28;
				}
				break;
			case 5:
				prev_month=30;
				break;
			case 7:
				prev_month=30;
				break;
			case 10:
				prev_month=30;
				break;
			case 12:
				prev_month=30;
				break;
			default:
				prev_month=31;
				
			}
			day1+=prev_month;
			month1-=1;
		}
		if(month1<month2) {
			month1+=12;
			year1-=1;
		}
		
		diff_days=day1-day2;
		diff_months=month1-month2;
		diff_years=year1-year2;
		
//		System.out.println(diff_years+" years and "+diff_months+" months "+diff_days+" days");

                /* Chane age criteria
*/
                if(diff_years>=15){
                    return true;
                }
                else{
                    return false;
                }
	}
        
        public String getAgeUpToDate(String bday){
//            AgeCalculator ag=new AgeCalculator(date);
//            ag.calc(bday);
            this.calc(bday);
            return "Age uptodate: "+this.diff_years + " years, "+this.diff_months+" months and "+this.diff_days+" days.";
        }
}
