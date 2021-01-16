package space;

import exception.DimensionException;

public class Vector {
	private double[] entries;
	public final int DIMENSION;
	
	/* CONSTRUCTORS */
	public Vector(double[] entries) {
		this.entries = entries;
		this.DIMENSION = this.entries.length;
	}
	
	public Vector(double x, double y) {
		this.entries = new double[] {x,y};
		this.DIMENSION = 2;
	}
	
	public Vector(double x, double y, double z) {
		this.entries = new double[] {x,y,z};
		this.DIMENSION = 3;
	}
	
	public static Vector zeroVector(int dimension) {
		return new Vector(new double[dimension]);
	}
	
	/* GETTERS AND SETTERS */
	public int getDimension() {
		return this.entries.length;
	}
	
	public double entry(int index) {
		return this.entries[index];
	}
	
	public void setLocation(int index, double value) {
		this.entries[index] = value;
	}
	
	public void setLocation(double[] values) {
		if (values.length != this.entries.length)
			throw new DimensionException("Dimension Mismatch");
		this.entries = values;
	}
	
	/* ARITHMETIC */
	public void addBy(Vector y) {
		if (this.DIMENSION != y.DIMENSION)
			throw new DimensionException("Dimension Mismatch");
		for (int k = 0; k < this.DIMENSION; k++) 
			this.setLocation(k, this.entries[k] + y.entries[k]);
	}
	
	public void multiplyBy(double scalar) {
		for (int k = 0; k < this.DIMENSION; k++) 
			this.setLocation(k, scalar*this.entries[k]);
	}
	
	public static double norm2(Vector v) {
		double norm2 = 0;
		for (int k = 0; k < v.entries.length; k++)
			norm2 += v.entries[k]*v.entries[k];
		return norm2;
	}
	
	public static double abs(Vector v) {
		return Math.sqrt(norm2(v));
	}
	
	public static Vector unit(Vector v) {
		if (norm2(v) == 0)
			return v;
		return mult(1.0/Math.sqrt(norm2(v)), v);
	}
	
	public static Vector sum(Vector x1, Vector x2) {
		Vector y = new Vector(new double[x1.DIMENSION]);
		for (int k = 0; k < x1.DIMENSION; k++)
			y.entries[k] = x1.entries[k] + x2.entries[k];
		return y;
	}
	
	public static Vector diff(Vector x1, Vector x2) {
		Vector y = new Vector(new double[x1.DIMENSION]);
		for (int k = 0; k < x1.DIMENSION; k++)
			y.entries[k] = x1.entries[k] - x2.entries[k];
		return y;
	}
	
	public static Vector mult(double scalar, Vector x) {
		Vector y = new Vector(new double[x.DIMENSION]);
		for (int k = 0; k < x.DIMENSION; k++)
			y.entries[k] = scalar*x.entries[k];
		return y;
	}
	
	/* REPRESENTATION */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < this.entries.length; k++) {
			sb.append(this.entries[k]);
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return "[" + sb.toString() + "]";
	}
	
	public static void main(String[] args) {
		Vector x1 = new Vector(1,2,3);
		System.out.println(x1);
		x1.setLocation(0,4);
		System.out.println(x1);
		x1.setLocation(new double[]{-10,-20,-30});
		try {
			x1.setLocation(new double[]{-1,-2});
		} catch (DimensionException exc) {}
		System.out.println();
		
		Vector x2 = new Vector(4,5,6);
		System.out.println(x1);
		x1.addBy(x2);
		System.out.println(x2);
		System.out.println(x1);
		x1.multiplyBy(-4);
		System.out.println(x1);
	}
}
