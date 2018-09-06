package string;

import java.util.Scanner;

public class KMPTest {

	public static void main(String[] args) {

		Scanner sin = new Scanner(System.in);

		char[] ts = sin.next().toCharArray();
		char[] ps = sin.next().toCharArray();

		int[] next = getBackIndex(ps);

		int index = KMP(ts, ps, next);

		System.out.println(index);

	}

	private static int KMP(char[] ts, char[] ps, int[] next) {

		int i = 0;
		int j = 0;

		while (i < ts.length && j < ps.length) {
			if (j == -1 || ts[i] == ps[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}

		if (j == ps.length) {
			return i - j;
		}

		return -1;


	}

	private static int[] getBackIndex(char[] ps) {

		int[] next = new int[ps.length];
		next[0] = -1;

		int j = 0;
		int k = -1;

		while (j < ps.length - 1) {
			if (k == -1 || ps[j] == ps[k]) {

				if (ps[++j] == ps[++k]) {
					next[j] = next[k];
				} else {
					next[j] = k;
				}

			} else {
				k = next[k];
			}
		}
		return next;

	}
}
