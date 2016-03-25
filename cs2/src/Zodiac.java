import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Zodiac {

	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int month = Integer.parseInt(br.readLine());
		int day = Integer.parseInt(br.readLine());
		String sign = "";
		if(month == 1) {
			if(day >= 20) {
				sign = "aquarius";
			}
			else {
				sign = "Capricorn";
			}
		}
		if(month == 2) {
			if(day >= 18) {
				sign = "pisces";
			}
			else {
				sign = "aquarius";
			}
		}
		if(month == 3) {
			if(day >= 20) {
				sign = "aries";
			}
			else {
				sign = "pisces";
			}
		}
		if(month == 4) {
			if(day >= 20) {
				sign = "taurus";
			}
			else {
				sign = "aries";
			}
		}
		if(month == 5) {
			if(day >= 21) {
				sign = "gemeni";
			}
			else {
				sign = "taurus";
			}
		}
		if(month == 6) {
			if(day >= 21) {
				sign = "cancer";
			}
			else {
				sign = "gemeni";
			}
		}
		if(month == 7) {
			if(day >= 23) {
				sign = "leo";
			}
			else {
				sign = "Cancer";
			}
		}
		if(month == 8) {
			if(day >= 23) {
				sign = "virgo";
			}
			else {
				sign = "leo";
			}
		}
		if(month == 9) {
			if(day >= 23) {
				sign = "libra";
			}
			else {
				sign = "virgo";
			}
		}
		if(month == 10) {
			if(day >= 23) {
				sign = "scorpio";
			}
			else {
				sign = "libra";
			}
		}
		if(month == 11) {
			if(day >= 22) {
				sign = "sagittarius";
			}
			else {
				sign = "scorpio";
			}
		}
		if(month == 12) {
			if(day >= 22) {
				sign = "Capricorn";
			}
			else {
				sign = "sagittarius";
			}
		}
		System.out.println(sign);
	}
}
