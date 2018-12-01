package LinkedListTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationTest {

	/**
	 * 递归实现
	 *
	 * @param data
	 * @param n
	 */
	public void combinations(ArrayList selected, List<Integer> data, int n) {
		// initial value for recursion 选择一个初始化的值
		// how to select elements
		// how to output

		if (n == 0) {
			// output empty list
			// output all select elements
			System.out.println(selected.toString());
			return;
		}

		if (data.isEmpty()) {
			return;
		}

		if (n < 0) {
			return;
		}


		// select element 0 选中了第一个元素
		selected.add(data.get(0));
		combinations(selected, data.subList(1, data.size()), n - 1);
		// un-select element 0 没选中第一个元素
		selected.remove(selected.size() - 1);
		combinations(selected, data.subList(1, data.size()), n);
	}

	public static void main(String[] args) {
		CombinationTest comb = new CombinationTest();
		comb.combinations(new ArrayList<>(), new ArrayList<>(), 0);
		comb.combinations(new ArrayList<>(), new ArrayList<>(), 1);
		comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 5), 5);
//		comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 5,6,7,8), 5);
	}

}
