package Chap3_검색;
/*
 * 3장 1번 실습과제: 03-3 정수배열이진검색
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * 3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class A implements Comparable<A> {
	int num;

	@Override
	public int compareTo(A c) {
		return num - c.num;
	}

	@Override
	public boolean equals(Object c) { //object class Overriding 구현
		if (num == ((A)c).num)
			return true;
		return false;
	}
}

public class train_실습3_4정수배열이진탐색 {

	public static void main(String[] args) {
		//		A ax = new A();
		//		A bx = new A();
		//		if (ax.equals(bx)) { //equals 함수 호출
		//			
		//		}
		//		
		int []data = new int[30];
		inputData(data);// 구현 반복 숙달 훈련 - 100 이하 난수를 생성

		showList("정렬 전 배열[]:: ", data);
		Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련

		Random random = new Random();
		int key = random.nextInt(100);

		boolean resultIndex = linearSearch(data, key);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없기 때문에 구현해야 한다 
		System.out.println("\nlinearSearch(13): key = " + key + ", result = " + resultIndex);

		key = random.nextInt(100);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//함수 구현이 필요
		System.out.println("\nbinarySearch(19): key = " + key + ", result = " + resultIndex);

		key = random.nextInt(100);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);


		static void inputData(int[] data) {
			Random random = new Random();
			for (int i = 0; i < data.length; i++) {
				data[i] = random.nextInt(100);
			}
		}

		static void showList(String message, int[] data) {
			System.out.println(message);
			for(int num : data) {
				System.out.println(num + " ");
			}
			System.out.println();

		}

		static boolean linearSearch(int[] data, int key) {
			for (int num: data) {
				if (num == key){
					return true;
				}
			}
			return false;
		}

		static boolean binarySearch(int[] data, int key) {
			int left = 0;
			int right = data.length - 1;

			do {
				int center = (left + right) / 2;
				if(data[center] == key)
					return true;
				else if (data[center] < key)
					left = center + 1;
				else
					right = center -1;
			} while (left <= right);

			return false; 
		}

	}
