package dynamic;

import java.util.Scanner;

/**
 * LongestPalindromeSubseq class
 * @anthor zhangyuan33@jd.com
 * @date 2018/9/7 9:42
 * 最长回文子序列
 * abbaba->abbba
 * 方法动态规划
 */
public class LongestPalindromeSubseq {
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		String str = sin.nextLine();
		int length = str.length();

		int res = dynamic(str,length);

		System.out.println(res);
		sin.close();
	}
	private static int dynamic(String str,int length){
		int[][] dl = new int[length][length];
		for (int i = length - 1; i >= 0; i--) {
			dl[i][i] = 1;
			for (int j = i + 1; j < length; j++) {
				if (str.charAt(j) == str.charAt(i)) {
					dl[i][j] = dl[i+1][j-1]+2;
					continue;
				}
				dl[i][j] = Math.max(dl[i][j-1],dl[i+1][j]);
			}
		}
		return dl[0][length-1];
	}
}
