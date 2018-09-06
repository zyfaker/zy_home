package demo.jingdong.diary;

import java.util.Scanner;

public class SelectKinds {
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);

		//需要的總長度；
		int K = sin.nextInt();

		//第一種的長度和數量
		int A = sin.nextInt();
		int X = sin.nextInt();

		//第二種的長度和數量
		int B = sin.nextInt();
		int Y = sin.nextInt();
		int Akingds = K / A;

		long num = 0l;
		//以A爲基准
		for (int i = 0; i <= X && i <= Akingds; i++) {

			//剩餘長度
			int Ktemp = K - A * i;

			//計算B的長度,需要整除
			if (Ktemp % B != 0) {
				continue;
			}
			int BKinds = Ktemp / B;

			//判斷是否越界
			if (BKinds > Y) {
				continue;
			}

			long a = getNum(i,X);
			long b = getNum(BKinds,Y);
			long c = (a*b)%1000000007;

			num += c;
		}
		System.out.println(num);
	}

	private static long getNum(int i, int x) {


		i = i > x / 2 ? x - i : i;
		if (i == 0) {
			return 1;
		}

		long iAll = 1l;
		for (int j = 1; j < (i + 1); j++) {
			iAll *= j;
			iAll %= 1000000007;
		}

		long all = 1l;
		boolean isNotFind = true;
		for (int j = x; j > (x - i); j--) {
			all*=j;
			if(isNotFind&&all%iAll==0){
				isNotFind = false;
				all /= iAll;
			}
			all%= 1000000007;
		}
		return all;
	}
}
