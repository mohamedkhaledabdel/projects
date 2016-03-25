import javax.swing.text.html.MinimalHTMLWriter;


public class Time {
	static int hours,minutes,seconds;
	
	public Time() {
		hours = 0;
		minutes = 0;
		seconds = 0;
	}
	
	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public int getHours() {
		return hours;
	}

	public void setHours() {
		if (this.hours < 23) {
			this.hours++;
		}
		else{
			this.hours = 0;
			this.seconds = 0;
			this.minutes = 0;
		}
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes() {
		if (this.minutes < 59) {
			this.minutes++;
		}
		else{
			this.minutes = 0;
			setHours();
		}
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds() {
		if (this.seconds < 59) {
			this.seconds++;
		}
		else{
			this.seconds = 0;
			setMinutes();
		}
	}
	
	public String toString() {
		return (hours + ":" + minutes + ":" + seconds);
	}
	
	public static void main(String[] args) {
		Time t = new Time(23, 59, 58);
		t.setSeconds();
		System.out.println(t.toString());
	}
	
	
}
