package Chap3_검색;

/*
 * 3장 2번 실습과제 - 스트링 배열의 정렬과 이진검색 
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train_실습3_6_0스트링배열정렬이진탐색 {

	
	static void showData(String message, String[] data) {
		System.out.println(message);
		for (String s : data) {
            System.out.print(s + ", ");
        }
	}
	
	static void sortData(String[] data) {
		for (int i = 0; i < data.length -1 ; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++)
				if (data[j].compareTo(data[min]) < 0)
					min = j;
			swap(data, i, min);
		}
	}
	
	static void swap(String[] data, int i, int j) {
		String dataTemp = data[i];
		data[i] = data[j];
		data[j] = dataTemp;
	}
	
	static int linearSearch(String[] data, String key) {
		int i= 0;
		
		while (true) {
			if (i == data.length)
				return -1;
			if (data[i].equals(key))
				return i;
			i++;
		}
	}
	
	static int binarySearch(String[] data, String key) {
		int left = 0;
		int right = data.length - 1;
		
		do {
			int center = (left + right) / 2;
			if(data[center].compareTo(key) == 0)
				return center;
			else if (data[center].compareTo(key) < 0)
				left = center + 1;
			else
				right = center - 1;
		} while (left <= right);
		return -1;
	}
	
	
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};
		showData("정렬전", data);
		sortData(data);//올림차순으로 정렬 교재211-212 단순 선택 정렬 알고리즘으로 구현
		showData("정렬후", data);

		String key = "포도";
		int resultIndex = linearSearch(data, key);//교재 100 페이지 seqSearch() 함수로 구현
		System.out.println("\nlinearSearch(포도): key = " + key + ", result 색인 = " + resultIndex);

		key = "배";
		resultIndex = binarySearch(data, key);//교재 109 페이지 binSearch() 함수로 구현
		System.out.println("\nbinarySearch(배):key = " + key + ",  result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): key = " + key + ", result = " + resultIndex);
	}
}
