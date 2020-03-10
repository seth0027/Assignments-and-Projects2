package Structures;

public class Stacks {

	String[] names;
	int last;
	int size;

	Stacks(int size) {
		this.size = size;
		names = new String[size];

	}

	String push(String value) {
		if (last < size) {
			names[last] = value;
			System.out.println("ADDED " + value + " AT INDEX " + last);
			last++;

			return value;
		} else {
			System.out.println("ITS FULL !");
			return null;
		}
	}

	boolean isEmpty() {
		if (size <= 0) {
			System.out.println("SIZE IS " + size);
			return true;
		} else
			return false;
	}

	String pop() {
		if (!isEmpty()) {
			System.out.println("POPPED " + names[--last]);
			return names[last];
		} else
			return null;
	}

	String peek() {
		if (!isEmpty()) {
			System.out.println("PEEKED AT " + names[--last]);
			return names[last];
		} else
			return null;
	}

	void display() {
		for (int i = 0; i < last; i++) {
			System.out.println(i + " : " + names[i]);
		}
	}

	void addMultiple(String multi) {
		String[] s = multi.split(" ");
		for (int i = 0; i < s.length; i++) {

			this.push(s[i]);
		}
	}

	String popMulti(int i) {
		for (int x = 0; x < i; x++) {
			this.pop();

		}
		return i + " ITEMS POPPED";
	}

	public static void main(String[] args) {
		Stacks s = new Stacks(10);
		s.addMultiple("LONDON PARIS ROME FRANCE BRAZIL MOSCOW SYRIA EQGYT QATAR SPAIN BELGIUM");
		// s.display();
		// s.pop();
		// s.pop();
		s.display();
		s.popMulti(5);
		s.display();

	}

}
