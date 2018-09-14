package sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/14 13:05
 */
public class QuickSort {
	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);
		int n = sin.nextInt();

		int[] nums = new int[n];
		for(int i=0;i<n;i++){
			nums[i] = sin.nextInt();
		}
		sin.close();

		quickSort(nums,0,n-1);

		System.out.println(Arrays.toString(nums));
	}

	private static void quickSort(int[] nums,int begin,int end) {
		if(begin>=end){
			return;
		}
		int index = getIndex(nums,begin,end);
		quickSort(nums,begin,index-1);
		quickSort(nums,index+1,end);
	}

	private static int getIndex(int[] nums, int begin, int end) {
		int temp = nums[begin];

		while(begin<end){
			while(begin<end&&nums[end]>temp){
				end--;
			}
			if(begin<end){
				nums[begin++]= nums[end];
			}

			while(begin<end&&nums[begin]<temp){
				begin++;
			}
			if(begin<end){
				nums[end--] = nums[begin];
			}

		}
		nums[begin] = temp;
		return begin;
	}
}
