package demo.jingdong.diary;

import java.util.Scanner;

public class TeamFuns {
	private static int n;
	private static int m;
	private static int[][] posNum;

	private static int kind;
	private static int max;

	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);

		n = sin.nextInt();
		m = sin.nextInt();

		posNum = new int[n + 2][m + 2];

		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < m+1; j++) {
				posNum[i][j] = sin.nextInt();
			}
		}
		print();
		BFS();
		//;
		System.out.println(kind);
		System.out.println(max);
	}

	private static void BFS() {
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if (posNum[i][j] == 1) {
					kind++;
					posNum[i][j] = 0;
					int thisFuns = search(i, j)+1;
					if (thisFuns > max) {
						max = thisFuns;
					}
				}
			}
		}
	}

	private static int search(int a, int b) {
		int num = 0;
		//上一列
		if (posNum[a - 1][b - 1] == 1) {
			num++;
			posNum[a - 1][b - 1] = 0;
			num += search(a - 1, b - 1);
		}
		if (posNum[a][b - 1] == 1) {
			num++;
			posNum[a][b - 1] = 0;
			num += search(a, b - 1);
		}
		if (posNum[a + 1][b - 1] == 1) {
			num++;
			posNum[a + 1][b - 1] = 0;
			num += search(a + 1, b - 1);
		}

		//这一行
		if (posNum[a - 1][b] == 1) {
			num++;
			posNum[a - 1][b] = 0;
			num += search(a - 1, b);
		}
		if (posNum[a][b] == 1) {
			num++;
			posNum[a][b] = 0;
			num += search(a, b);
		}
		if (posNum[a + 1][b] == 1) {
			num++;
			posNum[a + 1][b] = 0;
			num += search(a + 1, b);
		}

		//下一列
		if (posNum[a - 1][b + 1] == 1) {
			num++;
			posNum[a - 1][b + 1] = 0;
			num += search(a - 1, b + 1);
		}
		if (posNum[a][b + 1] == 1) {
			num++;
			posNum[a][b + 1] = 0;
			num += search(a, b + 1);
		}
		if (posNum[a + 1][b + 1] == 1) {
			num++;
			posNum[a + 1][b + 1] = 0;
			num += search(a + 1, b + 1);
		}
		return num;
	}

	private static void print() {
		for (int i = 0; i < n + 2; i++) {
			String line = "";
			for (int j = 0; j < m + 2; j++) {
				line += posNum[i][j] + " ";
			}
			System.out.println(line.trim());
		}
	}
}
