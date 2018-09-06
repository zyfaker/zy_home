package demo.jingdong.diary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		List<Integer> indexs = new ArrayList<>();
		for(int i=0;i<n;i++){
			nums[i] = sc.nextInt();
			indexs.add(i);
		}

		//开n个线程计算
		ExecutorService executor = Executors.newFixedThreadPool(n);
		CompletionService<Integer> results = new ExecutorCompletionService<Integer>(executor);

		for(final int i = 0;i<n;){
			results.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int j = i;
					return j;
				}
			});
		}


	}
}
