package $test;

import body.Body;
import space.Realspace;
import space.Vector;

public class TestBody {
	public static void main(String[] args) {
		Realspace space = new Realspace(2);
		Body alpha = new Body("Alpha", 1, 0);
		space.addBody(alpha, new Vector(0,0), new Vector(0,0));
		alpha.addAccelerationBy(new Vector(2,2));
		System.out.println(alpha);
	}
}
