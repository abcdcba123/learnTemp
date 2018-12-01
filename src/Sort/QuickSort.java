package Sort;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.Random;

public class QuickSort {

    public int[] quickSort(int[] array) {
        sortArray(array, 0 , array.length - 1);
        return array;
    }

    public void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            //找出最小值放到最前面
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            //交换array[i]和array[minIndex]
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public void sortArray(int[] array, int L, int R){
        if (R - L < 12) {
            insertSort(array);
            return;
        }
        int index = sortForPart(array,L,R);
        sortArray(array, L ,index - 1);
        sortArray(array, index + 1 ,R);
    }

    // 对索引为[L,R]区间的数组查找index所在位置
    private int sortForPart(int[] array, int L, int R) {
        int value = array[L];
        int index = L;
        for (int i = L; i <= R; i++) {
            if (array[i] < value) {
                int temp = array[index + 1];
                array[index + 1] = array[i];
                array[i] = temp;
                index++;
            }
        }
        int temp = array[index];
        array[index] = value;
        array[L] = temp;
        return index;
    }

    public static void main(String[] args){
        int[] nums = {3381, 443, 6918, -4429, 1531, 4315, 862, -2062, -8358, -5806, -7567, -5437, -6660, -3483, -2651, 5305, 9164, -7098, 1408, -6435, -9306, -5091, -437, -5228, -1196, -5312, -4654, 9895, -7803, 1636, -6296, -3011, 2185, 663, 4016, -759, 6016, -3910, -4643, 686, -2373, 5082, 6201, 6240, 6638, 9984, 9819, -6736, -8280, 7932, -5210, -3511, -915, -6887, -2265, -2133, -1292, -9621, 7449, 451, 2765, -4811, -5825, 9327, -6109, 962, -2363, 2837, 8754, 8414, -5950, -930, 7391, -1574, 4514, -4607, 8941, 7010, -357, -9295, 2337, -6914, 6448, -7880, -2134, -8797, 5310, -2017, 585, -2826, -1244, -9290, -5723, 7473, -5460, -6406, 3857, 1089, 2669, -2807, -6598, -7025, -7512, -8397, -5027, -4457, 3818, -5861, -3930, -5905, 404, -9466, -4208, -5352, -5137, -9254, 6129, 9257, -112, -3457, 314, 6306, 7857, -5851, 8589, 2159, -5264, -7905, -5421, 2589, 1945, -6711, -9422, -3903, -869, 9798, -1245, 3295, -1739, 756, -5244, -7396, -6120, 4203, -762, 6213, -8005, 4606, -9677, 5305, -7428, -5276, 7150, 3434, 1496, -5736, 3100, 7219, 8626, 8664, -4300, 7572, -6091, -7421, 6306, 4138, -1364, -8980, -7514, 6492, 2519, -9190, -2939, 5458, 7882, 7183, 1547, -7266, 3605, 5848, 4650, 3624, 524, -3313, 5622, -3865, -9391, 3416, 4479, 3110, 7630, -2013, 6510, 5085, -5563, 8033, -2656, 5074, -6304, 6927};
//        int[] nums = {1,4,3,2};
        QuickSort sort = new QuickSort();
        sort.quickSort(nums);
        StringBuffer echo = new StringBuffer();
        for (int i = 0; i < nums.length; i++){
            echo.append(nums[i] + "->");
        }
        System.out.println(echo);

        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            if (flag){
                sum = sum + nums[i];
                System.out.println("sum :" + sum);
            }
            flag = !flag;
        }
        System.out.println(sum);
    }
}
