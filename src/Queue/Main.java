package Queue;

import java.util.Random;

public class Main {

	// 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
	private static double testQueue(Queue<Integer> q, int opCount) {

		long startTime = System.nanoTime();

		Random random = new Random();
		for (int i = 0; i < opCount; i++)
			q.push(random.nextInt(50));
//		System.out.println(q);

		for (int i = 0; i < opCount; i++)
			q.pop();


		long endTime = System.nanoTime();

		return (endTime - startTime) / 1000000000.0;
	}

	public static void main(String[] args) {

		int opCount = 2000;

//		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
//		double time1 = testQueue(arrayQueue, opCount);
//		System.out.println("ArrayQueue, time: " + time1 + " s");

		LoopQueue<Integer> loopQueue = new LoopQueue<>();
		double time2 = testQueue(loopQueue, opCount);
		System.out.println("LoopQueue, time: " + time2 + " s");

		LoopQueueNoSize<Integer> loopQueueNoSize = new LoopQueueNoSize<>();
		double time3 = testQueue(loopQueueNoSize, opCount);
		System.out.println("LoopQueueNoSize, time: " + time3 + " s");

		LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
		double time4 = testQueue(linkedQueue, opCount);
		System.out.println("LinkedQueue, time: " + time4 + " s");
	}
}
