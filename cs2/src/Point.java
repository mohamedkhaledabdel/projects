public class Point {
	int x;
	int y;
	static int counter;

	public Point() {
		x = 0;
		y = 0;
		counter++;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		counter++;
	}

	public static void swap(Point p1, Point p2) {
		int tempX = p1.x;
		int tempY = p1.y;
		p1.x = p2.x;
		p1.y = p2.y;
		p2.x = tempX;
		p2.y = tempY;
	}

	public void swap2(Point p) {
		int tempX = this.x;
		int tempY = this.y;
		this.x = p.x;
		this.y = p.y;
		p.x = tempX;
		p.y = tempY;
	}

	public static void main(String[] args) {
		Point p1 = new Point(10, 20);
		Point p2 = new Point(1, 2);
		// swap(p1, p2);
		System.out.println(p2.x + " " + p2.y);
		p1.swap2(p2);
		System.out.println(p1.x + " " + p1.y);
		System.out.println("The counter is " + Point.counter);
	}

}
