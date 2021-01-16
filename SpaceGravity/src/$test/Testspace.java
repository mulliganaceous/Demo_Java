package $test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import body.Body;
import space.Realspace;
import space.Vector;

public class Testspace {
	public static void main(String[] args) {
		Realspace space = new Realspace(2);
		space.addBody(new Body("Alpha", 1, 0), new Vector(0,0), new Vector(0,0));
		space.addBody(new Body("Beta ", 1, 0), new Vector(2,0), new Vector(0,0));
		space.addBody(new Body("Gamma", 1, 0), new Vector(0,2), new Vector(0,0));
		
		space.update(1.0/16);
		System.out.println(space);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = reader.readLine();
			while (!input.equals("!")) {
				space.update(1.0/16);
				System.out.println(space);
				input = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
