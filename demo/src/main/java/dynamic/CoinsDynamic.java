package dynamic;

import java.util.Scanner;

public class CoinsDynamic {

	private static int[] coins = {1, 5, 10, 50, 100};

	private static boolean[] states;

	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		int K = sin.nextInt();

		int[] sums = new int[K + 1];
		states = new boolean[K + 1];

		System.out.println(dynamic2(K, 4));
		//System.out.println(sums[K]);
	}

	private static int dynamic2(int k, int j) {

		int num = 0;
		if (k / coins[j] > 0) {
			num += k / coins[j];
		}
		if (k % coins[j] != 0) {
			num += dynamic2(k % coins[j], j - 1);
		}

		return num;


	}

	private static void dynamic(int[] sums, int k) {
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < 5; j++) {
				if ((i - coins[j]) >= 0) {
					if (states[i]) {
						if ((sums[i - coins[j]] + 1) < sums[i]) {
							sums[i] = sums[i - coins[j]] + 1;
						}
					} else {
						sums[i] = sums[i - coins[j]] + 1;
						states[i] = true;
					}
				} else {
					break;
				}
			}
		}
	}


}
