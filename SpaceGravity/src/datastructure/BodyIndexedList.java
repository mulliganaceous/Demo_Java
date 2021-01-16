package datastructure;

import java.util.Iterator;

import body.Body;

public class BodyIndexedList {
	private final int[] rehashCriteria;
	private short rehashLevel = 0;
	private int size = 0;
	private Body[] bodies;
	
	public BodyIndexedList() {
		this.rehashCriteria = new int[0];
		this.bodies = new Body[2];
	}
	
	public BodyIndexedList(int[] rehashCriteria) {
		this.rehashCriteria = rehashCriteria;
		this.bodies = new Body[rehashCriteria[0]];
	}
	
	public int getSize() {
		return this.size;
	}
	
	private void rehash(int capacity) {
		Body[] bodies = new Body[capacity];
		int size = 0;
		for (int k = 0; k < this.bodies.length; k++) {
			if (this.bodies[k] != null) {
				bodies[size] = this.bodies[k];
				size++;
			}
		}
		this.bodies = bodies;
	}
	
	public void addBody(Body newBody) {
		if (this.size >= this.bodies.length) {
			if (this.rehashLevel >= this.rehashCriteria.length - 1)
				this.rehash(this.bodies.length * 2);
			else
				this.rehash(this.rehashCriteria[this.rehashLevel + 1]);
			this.rehashLevel++;
		}
		this.bodies[this.size] = newBody;
		this.size++;
	}
	
	public class BodyIndexedIterator implements Iterator<Body> {
		private int index;
		private int traversed;
		public BodyIndexedIterator() {
			this.index = 0;
			this.traversed = 0;
		}
		
		@Override
		public boolean hasNext() {
			return this.traversed < BodyIndexedList.this.size;
		}

		@Override
		public Body next() {
			Body element = BodyIndexedList.this.bodies[this.index];
			this.traversed++;
			this.index++;
			while (this.hasNext() && BodyIndexedList.this.bodies[this.index] == null)
				this.index++;
			return element;
		}
		
		public BodyIndexedIterator clone() {
			BodyIndexedIterator cloned = new BodyIndexedIterator();
			cloned.index = this.index;
			cloned.traversed = this.traversed;
			return cloned;
		}
	}
	
	public BodyIndexedIterator iterator() {
		return new BodyIndexedIterator();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < this.bodies.length; k++)
			sb.append("#" + k + ":\t").append(this.bodies[k]).append("\n");
		return sb.toString();
	}
}
