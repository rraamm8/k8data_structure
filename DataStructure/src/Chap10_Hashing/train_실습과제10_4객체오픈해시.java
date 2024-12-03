package Chap10_Hashing;

import java.util.Comparator;
import java.util.Scanner;

//오픈 주소법에 의한 해시

class SimpleObject2 {
	static final int NO = 1;
	static final int NAME = 2;
	String sno; // 회원번호
	String sname; // 이름

	public SimpleObject2() {
		this.sno = "";
		this.sname = "";
	}

	public void scanData(String message, int field) {
		if ((field & NO) != 0) {
			System.out.print(message + " 회원번호: ");
			sno = new Scanner(System.in).next();
		}
		if ((field & NAME) != 0) {
			System.out.print(message + " 이름: ");
			sname = new Scanner(System.in).next();
		}
	}

	@Override
	public String toString() {
		return "회원번호: " + sno + ", 이름: " + sname;
	}
	
	public static final Comparator<SimpleObject2> NO_ORDER = new Comparator<>() {
        public int compare(SimpleObject2 o1, SimpleObject2 o2) {
            return o1.sno.compareTo(o2.sno);
        }
    };
}
//*
class OpenHash {

	// --- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	}; // {데이터 저장, 비어있음, 삭제 완료}

	// --- 버킷 ---//
	static class Bucket { 
		private SimpleObject2 data; // 데이터
		private Status stat; // 상태

		public Bucket() {
			stat = Status.EMPTY;
		}
		
		void set(SimpleObject2 data, Status stat) {
			this.data = data;
			this.stat = stat;
		}
		
		SimpleObject2 getData() {
			return data;
		}
		
		Status getStat() {
			return stat;
		}
	}

	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public OpenHash(int size) {
		this.size = size;
		table = new Bucket[size];
		for (int i = 0; i < size; i++) {
			table[i] = new Bucket();
		}
	}

	// --- 해시값을 구함 ---//
	public int hashValue(SimpleObject2 key) {
		return Integer.parseInt(key.sno) % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		int hash = hashValue(key);
	    Bucket bucket = table[hash];

	    for (int i = 0; i < size; i++) {
	        if (bucket.getStat() == Status.EMPTY) {
	            break;
	        } else if (bucket.getStat() == Status.OCCUPIED && c.compare(bucket.getData(), key) == 0) {
	            return bucket;
	        }
	        hash = rehashValue(hash);
	        bucket = table[hash];
	    }
	    return null;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public SimpleObject2 search(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		Bucket bucket = searchNode(key, c);
		if (bucket != null && bucket.getStat() == Status.OCCUPIED) {
			return bucket.getData();
		}
		return null;
		
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		if (search(key, c) != null) {
			return 1;
		}
		
		int hash = hashValue(key);
		Bucket bucket = table[hash];
		
		for (int i = 0; i < size; i++) {
			if (bucket.getStat() == Status.EMPTY || bucket.getStat() == Status.DELETED) {
				bucket.set(key, Status.OCCUPIED);
				return 0;
			}
			hash = rehashValue(hash);
			bucket = table[hash];
		}
		return 2;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject2 key, Comparator<? super SimpleObject2> c) {
		Bucket bucket = searchNode(key, c);
		if (bucket != null && bucket.getStat() == Status.OCCUPIED) {
			bucket.set(null, Status.DELETED);
			return 0;
		}
		return 1;
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
	        System.out.print(i + ": ");
	        if (table[i].getStat() == Status.OCCUPIED) {
	            System.out.println(table[i].getData());
	        } else {
	            System.out.println("EMPTY");
	        }
	    }
	}
}
//*/
public class train_실습과제10_4객체오픈해시 {

	static Scanner stdIn = new Scanner(System.in);

//--- 메뉴 열거형 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
	
		SimpleObject2 temp; // 읽어 들일 데이터
		int result;
		OpenHash hash = new OpenHash(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				temp = new SimpleObject2();
				temp.scanData("추가", SimpleObject2.NO | SimpleObject2.NAME);
				int k = hash.add(temp, SimpleObject2.NO_ORDER);
				switch (k) {
				case 1:
					System.out.println("그 키값은 이미 등록되어 있습니다.");
					break;
				case 2:
					System.out.println("해시 테이블이 가득 찼습니다.");
					break;
				case 0:
				}
				break;

			case REMOVE: // 삭제
				temp = new SimpleObject2();
				temp.scanData("삭제", SimpleObject2.NO);
				result = hash.remove(temp, SimpleObject2.NO_ORDER);
				if (result == 0)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;

			case SEARCH: // 검색
				temp = new SimpleObject2();
				temp.scanData("검색", SimpleObject2.NO);
				SimpleObject2 t = hash.search(temp, SimpleObject2.NO_ORDER);
				if (t != null)
					System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
