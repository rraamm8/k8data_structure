package Chap5_Recursive;
/*
 * 미로 찾기 문제
 * plato(LMS)의 미로 찾기 문제 설명 자료 학습
 * int input[12][15] 테이블에서 입구는 (0,0)이며 출구는 (11,14)임
 * 미로 테이블 (12,15)을 상하좌우 울타리를 친 maze[14][17]을 만들고 입구는 (1,1)이며 출구는(12,15)
 * stack을 사용한 backtracking 구현
 */

import java.util.Stack;

//23.2.16 수정
//23.2.24: 객체 배열 초기화, static 사용, 내부 클래스와 외부 클래스 사용 구분을 못하는 문제 => 선행 학습 필요

class Items {
	int x;
	int y;
	int dir;

	public Items(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

class Offsets {
	int a;
	int b;

	public Offsets(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

public class train_실습_미로찾기실습과제 {

	static Offsets[] moves = new Offsets[8];//static을 선언하는 이유를 알아야 한다

	static void path(int maze[][], int mark[][], int m, int p) {
		//m = 12, p = 15
		//출발점 (1,1), 이동 방향 dir = 2(2는 동쪽) 을 스택에 push
		//initialize stack to the maze entrance coordinates and direction east;
		Stack<Items> stack = new Stack<>();
		stack.push(new Items(1, 1, 2));

		while (!stack.isEmpty()){
			Items temp = stack.pop();
			int i = temp.x;
			int j = temp.y;
			int dir = temp.dir; //(i,j,dir) = coordinates and direction deleted from top of stack;
			mark[i][j] = 1;//현재 위치 (i,j)에 대하여 mark[][]을 1로 설정

			while (dir < 8) {//8가지 방향중에서 남은 방향에 대하여

				int g = i + moves[dir].a;
				int h = j + moves[dir].b;
				//(g,h) = coordinates of next move;//현재 위치 (i,j)에 대하여 이동 방향 계산

				if ((g == m) && (h == p)) {
					//success;
					mark[g][h] = 1;//(i,j)와 (g,h)에 대하여 mark 표시
					return;
				}
				if (maze[g][h] == 0 && mark[g][h] == 0) {//haven't been here before

					mark[g][h] = 1;
					//dir = next direction to try;
					stack.push(new Items(i, j, dir + 1));		//add (i,j,dir) to top of stack;
					i = g;
					j = h;
					dir = 0;
				} else {
					dir++; //try next direction
				}
			}
			mark[i][j] = 0; //(i,j) 현위치에 대한 mark를 취소
		}
		System.out.println("No path found");
	}

	static void show(String title, int[][] array) {
		System.out.println(title);
		for (int[] row : array) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		enum Directions {N, NE, E, SE, S, SW, W, NW}
		int[][] maze = new int[14][17];//미로 입력 데이터
		int[][] mark = new int[14][17];//울타리치고 전부 0으로

		int input[][] = { // 12 x 15
				{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
				{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }
		};

		for (int ia = 0; ia < 8; ia++)

			moves[ia] = new Offsets(0, 0);//배열에 offsets 객체를 치환해야 한다.
			moves[0].a = -1;	moves[0].b = 0;
			moves[1].a = -1;	moves[1].b = 1;
			moves[2].a = 0;		moves[2].b = 1;
			moves[3].a = 1;		moves[3].b = 1;
			moves[4].a = 1;		moves[4].b = 0;
			moves[5].a = 1;		moves[5].b = -1;
			moves[6].a = 0;		moves[6].b = -1;
			moves[7].a = -1;	moves[7].b = -1;
		//Directions d;
		//d = Directions.N;
		//d = d + 1;//java는 지원안됨

		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				//input[][]을 maze[][]로 복사
				if (i == 0 || i == 13 || j == 0 || j == 16) {
					// maze 경계 1로 설정
					maze[i][j] = 1;
				} else if (i >= 1 && i <= 12 && j >= 1 && j <= 15) {
					maze[i][j] = input[i - 1][j - 1];
				} else {
					maze[i][j] = 0; // 미사용 영역
				}
				mark[i][j] = 0; // 초기화
			}
		}

		show("maze[12,15]::", maze);
		show("mark[12,15]::", mark);

		path(maze, mark, 12, 15);
		show("maze[12,15]::", maze);
		show("mark[12,15]::", mark);


	}

}