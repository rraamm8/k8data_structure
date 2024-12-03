package Chap4_스택과큐;
/*
 * 실습 4_1번 - 정수 배열 스택
 * 스택을 정수 배열로 구현
 * 예외 처리 코드 이해가 필요
 * 교재 133 - 실습 4-1 소스코드를 읽어보고 가능하면 책을 보지 않고 소스코드 구현완성 노력이 좋다 
 */

import java.util.Scanner;

//int형 고정 길이 스택

class IntStack3 {
	private int[] stk; // 스택용 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
//추가
		public EmptyIntStackException() {}
	}

//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
//추가
		public OverflowIntStackException() {}
	}

//--- 생성자(constructor) ---//
	public IntStack3(int maxlen) {
		
		capacity = maxlen;
		top = 0;
		
		try {
			stk = new int[capacity];
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

	//--- 스택에 x를 푸시 ---//
	public int push(int x) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException();
		
//추가
		return stk[top++] = x;
	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public int pop() throws EmptyIntStackException {
//추가
		if (top <= 0)
			throw new EmptyIntStackException();
		return stk[--top];
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntStackException {
//추가
		if (top <= 0)
			throw new EmptyIntStackException();
		return stk[top -1];
	}

//--- 스택을 비움 ---//
	public void clear() {
//추가
		top = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(int x) {
//추가
		for(int i = top - 1; i >= 0; i--)
			if (stk[i] == x)
				return i;
		return -1;
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
//추가
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
//추가
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
//추가
		return top <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
//추가
		return top >= capacity;
	}
	
//--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() throws EmptyIntStackException{
//추가
		if (top <= 0)
			System.out.println("스택이 비어 있습니다.");
		else {
			for(int i = 0; i < top; i++)
				System.out.println(stk[i] + " ");
			System.out.println();
		}
	}
}

public class train_실습4_2정수스택_배열 {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack3 s = new IntStack3(4); // 최대 64 개를 푸시할 수 있는 스택

		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {

			case 1: // 푸시
				System.out.print("데이터: ");
				x = stdIn.nextInt();
				try {
					s.push(x);
				} catch (IntStack3.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 팝
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 4: // 덤프
				try {
					s.dump();
				} catch (IntStack3.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				s.dump();
				break;
			}
		}
	}
}