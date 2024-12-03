package Chap8_List;

class Node1 {
	int data; // 노드에 저장된 데이터. 정수형
	Node1 link; // 다음 노드를 가리키는 참조변수
	
	public Node1(int element) { //constructor
		data = element; // data에 element 값 할당
		link = null; // 초기화
	}
}

class LinkedList1 {
	Node1 first; // Linked list의 첫번째 노드 가리키는 pointer
	
	public LinkedList1() { constructer
		first = null; // first pointer를 null로 설정하여 초기화
	}
	
	//전달된 element 값이 존재하면 삭제하고 true로 리턴
	public boolean Delete(int element) {
		Node1 q, current = first; //리스트의 현재 노드
		q = current; // first 노드를 임시 저장
		Node1 previous = null; //이전 노드 추적 위한 변수
		
		while (current != null) { // 리스트 끝까지 순회
			if (current.data == element) { //element와 일치하는 노드 찾으면 삭제
				if (previous == null) { //삭제할 노드가 첫번째인 경우
					first = current.link; // first 포인터를 current.link로 설정하여 첫번째 노드 제거
				} else {
					previous.link = current.link;
				}return true;
			}
			previous = current;
			current = current.link;
		}
		return false;
	}
		
}

public class linkedList {

}
