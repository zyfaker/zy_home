package demo.jingdong.diary;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Round_robin {
	private static class process{
		int exeTime;
		int arrTime;
		process(int a,int b){
			arrTime = a;
			exeTime = b;
		}
	}

	public static void main(String[] args){

		int[] arrTime = {0,50,130,190,210,350};
		int[] exeTime = {250,170,75,100,130,50};

		int timeSlot = 100;

		RoundRobin_Algo(arrTime,exeTime,timeSlot);
		/*byte[] test = null;


		String a = test==null?"key":new String(test);


		System.out.println(a);*/

	}


	public static void RoundRobin_Algo(int[] arrTime, int[] exeTime, int timeSlot) {
		if (arrTime == null || exeTime == null || arrTime.length != exeTime.length) {
			System.out.println("Error!");
		}

		Queue<process> queue = new LinkedList<>();
		int index = 0;
		int totalWaitTime = 0;		//所有进程的等待总时间
		int currentTime = 0;		//当前时间
		int visitedTimes = 0;		//CPU访问次数

		while (!queue.isEmpty() || index < arrTime.length) {
			if (!queue.isEmpty()) {
				visitedTimes ++;
				process tempP = queue.poll();
				//waiting time in total
				totalWaitTime += currentTime - tempP.arrTime;
				//update the currentTime
				currentTime += Math.min(tempP.exeTime, timeSlot);

				//add the process which arrived before the end of this process finished.
				for (; index < arrTime.length && arrTime[index] < currentTime; index++) {
					queue.add(new process(arrTime[index], exeTime[index]));
				}

				//add the rest of current process into process queue
				if (tempP.exeTime > timeSlot) {
					queue.add(new process(currentTime, tempP.exeTime - timeSlot));
				}

			} else {
				queue.add(new process(arrTime[index], exeTime[index]));
				currentTime = arrTime[index];
				index++;
				visitedTimes ++;

			}
		}
		System.out.println("Total wait time among the process: " + totalWaitTime);
		System.out.println("Total CPU visited times: " + visitedTimes);
	}

}
