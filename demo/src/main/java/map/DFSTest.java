package map;

import java.util.Scanner;

/**
 * @anthor zhangyuan33@jd.com
 * @date 2018/9/7 10:03
 */
public class DFSTest {
	private static int times = 0;
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);

		int n = sin.nextInt();
		int[][] nums = new int[n + 1][n + 1];

		for (int i = 0; i < n - 1; i++) {
			int a = sin.nextInt();
			int b = sin.nextInt();
			nums[a][b] = 1;
			nums[b][a] = 1;
		}
		DFS(n, nums);
	}

	private static void DFS(int n, int[][] nums) {

		//构建一个节点访问状态
		int max = 0;
		for (int i = 1; i < n + 1; i++) {
			times = 0;
			boolean[] isVisted = new boolean[n + 1];
			DFS(i, isVisted, nums, n);
			System.out.println(times);
		}
	}

	private static void DFS(int index, boolean[] states, int[][] nums, int n) {

		states[index] = true;
		System.out.print(index+" ");
		int nexIndex = getNextIndex(nums, index,0, n);

		while (nexIndex != -1) {
			if (!states[nexIndex]) {
				times++;
				DFS(nexIndex, states, nums, n);
			}
			nexIndex = getNextIndex(nums, index,nexIndex, n);
		}
		if(!isAllFind(states)){
			times++;
		}
	}

	private static boolean isAllFind(boolean[] states) {
		boolean s = true;
		for(int i=1;i<states.length;i++){
			if(!(s&=states[i])){
				return false;
			}
		}
		return true;
	}

	private static int getNextIndex(int[][] nums, int index,int beginIndex, int length) {

		for (int i = beginIndex+1; i < length + 1; i++) {
			if (nums[index][i] == 1) {
				return i;
			}
		}
		return -1;
	}

}
