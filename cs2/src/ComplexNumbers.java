
public class ComplexNumbers {

	float real;
	float imag;
	
	public ComplexNumbers(float real, float imag) {
		this.real = real;
		this.imag = imag;
	}
	
	public float getReal() {
		return this.real;
	}
	
	public float getImag() {
		return this.imag;
	}
	
	public void add(ComplexNumbers c ) {
		this.real += c.real;
		this.imag += c.imag;
	}
	
	public static ComplexNumbers add(ComplexNumbers c1, ComplexNumbers c2) {
		return new ComplexNumbers(c1.real + c2.real, c1.imag + c1.imag);
	}
	
	
	
}
