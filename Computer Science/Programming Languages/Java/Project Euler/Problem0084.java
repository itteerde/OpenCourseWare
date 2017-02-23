package de.itteerde.euler;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Problem0084 {

	static int[] counter = new int[40];
	static int doubles = 0;
	static int currentField = 0;
	static int cc = 0;
	static int ch = 0;

	public static void main(String[] args) {

		for (int i = 0; i < 100000000; i++) {
			currentField = move();
			counter[currentField]++;
		}
		System.out.println(Arrays.toString(counter));

	}

	public static int move() {

		int w1 = roll(4);
		int w2 = roll(4);

		if (w1 == w2) {
			if (doubles == 2) {
				currentField = 10;
				return currentField;
			} else {
				doubles++;
			}
		} else {
			doubles = 0;
		}

		currentField = (currentField + w1 + w2) % 40;

		if (currentField == 30) {
			return 10;
		}

		if (currentField == 7 || currentField == 22 || currentField == 36) {// CH

			switch (ch++ % 16) {
			case 1:
				return 0;
			case 2:
				return 10;
			case 3:
				return 11;
			case 4:
				return 24;
			case 5:
				return 39;
			case 6:
				return 5;
			case 7:
				if (currentField < 5) {
					return 5;
				}
				if (currentField < 15) {
					return 15;
				}
				if (currentField < 25) {
					return 25;
				}
				if (currentField < 35) {
					return 35;
				}
				return 5;
			case 8:
				if (currentField < 5) {
					return 5;
				}
				if (currentField < 15) {
					return 15;
				}
				if (currentField < 25) {
					return 25;
				}
				if (currentField < 35) {
					return 35;
				}
				return 5;
			case 9:
				if (currentField < 12) {
					return 12;
				}
				if (currentField < 28) {
					return 28;
				}
				return 12;
			case 10:
				return (currentField - 3) % 40;
			default:
				return currentField;
			}
		}

		if (currentField == 2 || currentField == 17 || currentField == 33) {// CC

			switch (cc++ % 16) {
			case 1:
				return 0;
			case 2:
				return 10;
			default:
				return currentField;
			}
		}

		return currentField;
	}

	public static int roll(int sides) {
		return ThreadLocalRandom.current().nextInt(1, sides + 1);
	}

}
