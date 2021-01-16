package numbersystem;

public class Quaternion {
	public static int PRINTMODE = 0;
	private double re;
	private double[] im;
	
	/**
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Quaternion(double a, double b, double c, double d) {
		this.re = a;
		this.im = new double[] {b,c,d};
	}
	
	/**
	 * @param re
	 * @param im
	 */
	public Quaternion(double re, double[] im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static Quaternion add(Quaternion h1, Quaternion h2) {
		double re = h1.re + h2.re;
		double[] im = new double[3];
		for (int k = 0; k < 3; k++)
			im[k] = h1.im[k] + h2.im[k];
		
		return new Quaternion(re, im);
	}
	
	/**
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static Quaternion mult(Quaternion h1, Quaternion h2) {
		double re = h1.re*h2.re;
		for (int k = 0; k < 3; k++)
			re -= h1.im[k]*h2.im[k];
		
		double imi = h1.re*h2.im[0] + h1.im[0]*h2.re;
		double imj = h1.re*h2.im[1] + h1.im[1]*h2.re;
		double imk = h1.re*h2.im[2] + h1.im[2]*h2.re;
		imi += h1.im[1]*h2.im[2] - h1.im[2]*h2.im[1]; // jk - kj
		imj += h1.im[2]*h2.im[0] - h1.im[0]*h2.im[2]; // ki - ik
		imk += h1.im[0]*h2.im[1] - h1.im[1]*h2.im[0]; // ij - ji
		
		return new Quaternion(re, imi, imj, imk);
	}
	
	/**
	 * @param h1
	 * @param h2
	 * @return
	 */
	public static Quaternion mult(double h1, Quaternion h2) {
		return new Quaternion(h1*h2.re, h1*h2.im[0], h1*h2.im[1], h1*h2.im[2]);
	}
	
	/**
	 * @return
	 */
	public Quaternion conj() {
		return new Quaternion(this.re, -this.im[0], -this.im[1], -this.im[2]);
	}
	
	public double norm() {
		double norm = this.re*this.re;
		for (int k = 0; k < 3; k++)
			norm += this.im[k]*this.im[k];
		return norm;
	}
	
	public static double abs(Quaternion h) {
		return Math.sqrt(h.norm());
	}
	
	public Quaternion inv() {
		return mult(1/this.norm(), this.conj());
	}
	
	public static Quaternion unit(Quaternion h1) {
		return mult(abs(h1), h1);
	}
	
	public static Quaternion subt(Quaternion h1, Quaternion h2) {
		return add(mult(-1,h1), h2);
	}
	
	public static Quaternion div(Quaternion h1, Quaternion h2) {
		return mult(1/h2.norm(),mult(h1,h2.conj()));
	}
	
	public String toString() {
		return String.format("%.3f;(%.3f,%.3f,%.3f)",this.re, this.im[0], this.im[1], this.im[2]);
	}
	
	public static void main(String[] args) {
		Quaternion h1 = new Quaternion(-1,5,-2,4);
		Quaternion h2 = new Quaternion(-2,3,-4,1);
		System.out.println(h1 + " + " + h2 + " = " + add(h1, h2));
		System.out.println(h1 + " * " + h2 + " = " + mult(h1, h2));
		System.out.println(h1 + " * " + h2 + " = " + div(h1, h2));
		System.out.println(div(h1, h2) + " * " + h2 + " = " + mult(div(h1, h2),h2));
		System.out.println(h2 + " * " + div(h1, h2) + " = " + mult(h2,div(h1, h2)));
		System.out.println(h1 + "⁻¹ = " + h1.inv());
		System.out.println(h1 + "⁻¹ * " + h1 + " = " + mult(h1.inv(), h1));
		System.out.println(h1 + "⁻¹ * " + h1 + " = " + unit(h1));
		System.out.println(-4 + " * " + h2 + " = " + mult(-4, h2));
		System.out.println("|" + h1 + "|^2 = " + h1.norm());
	}
}
