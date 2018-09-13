package thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author zhangyuan33@jd.com
 * @date 2018/9/12 10:49
 */
public class FileSearchTask {

	private static class MatchCount implements Callable<Integer> {
		File file;
		String keyword;
		private Integer count = 0;

		@Override
		public Integer call(){
			if (search(file)) {
				count++;
			}
			return count;
		}

		private boolean search(File file) {
			boolean founded = false;
			try (Scanner scanner = new Scanner(new FileInputStream(file))) {
				while (!founded && scanner.hasNextLine()) {
					if (scanner.nextLine().contains(keyword)) {
						founded = true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return founded;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//获取文件路径
		String path = "E:\\software\\elasticsearch-2.3.5\\logs";
		//获取关键字
		String keyword = "error";

		int c = 0;
		File[] files = new File(path).listFiles();
		ArrayList<Future<Integer>> rs = new ArrayList<>();
		//每个文件启动一个task去查找
		for (File file : files) {
			MatchCount count = new MatchCount();
			count.file = file;
			count.keyword = keyword;
			FutureTask<Integer> task = new FutureTask(count);
			//将任务返回的结果添加到集合中
			rs.add(task);
			Thread thread = new Thread(task);
			thread.start();
		}
		//迭代返回结果并累加
		for (Future<Integer> f : rs) {
			c += f.get();
		}
		System.out.println("包含关键字的总文件数为：" + c);
	}
}

