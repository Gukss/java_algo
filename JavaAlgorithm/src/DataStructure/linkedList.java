package DataStructure;

import java.util.LinkedList;
import java.util.List;

public class linkedList {
	
	private LinkedListNode root;
	private int size;
	
	public linkedList() { //생성자, init
		this.root = null;
		this.size = 0;
	}
	public boolean addFirst(int data) {
		//add(0,data); //재사용해도 된다.
		LinkedListNode newNode = new LinkedListNode(data);
		if(root==null) {
			root = newNode;
		}else { //newNode의 다음을 root로 연결해 주고, root를 newNode로 바꿔준다.
			newNode.next = root;
			root = newNode;
		}
		size += 1;
		return true;
	}
	public boolean add(int data) { //제일 뒤에 추가
		LinkedListNode newNode = new LinkedListNode(data);
		if(root == null) { //root가 없으면 newNode가 root된다.
			root = newNode;
		}else { //root가 있으니까 root를 따라가서 next에 추가한다.
			LinkedListNode temp = root;
			while(temp.next != null) { //temp의 next가 없을 때까지 temp를 바꾸면서 반복하면 while문 탈출했을 때 temp는 마지막 노드다.
				temp = temp.next;
			}
			temp.next = newNode;
		}
		size += 1;
		return true;
	}
	public boolean add(int idx, int data) {//인덱스를 지정해서 추가
		LinkedListNode newNode = new LinkedListNode(data);
		LinkedListNode before = null;
		
		if(this.size() >= idx) { //size가 idx랑 크거나 같으면 정상적으로 추가할 수 있다.
			
		}else { //idx가 크면 정상적으로 추가할 수 없다.
			return false;
		}
		
		if(root == null) {
			root = newNode;
		}else {			
			if(idx == 0) {
				newNode.next = root;
				root = newNode;
			}else {			
				before = this.get(idx-1);
				newNode.next = before.next;
				before.next = newNode;
			}
		}
		size += 1;
		return true;
	}
	
	public boolean remove() { //제일 처음을 삭제
		
		size -= 1;
		return true;
	}
	public boolean remove(int idx) { //해당 인덱스의 값을 삭제
		LinkedListNode target = this.get(idx);
		size -= 1;
		return true;
	}
	public boolean removeLast() { //제일 마지막을 삭제
		if(root == null) { //root에 아무것도 없으면 return true
			return true;
		}else {
			LinkedListNode preNode = root;
			LinkedListNode lastNode = root.next;
			//마지막 노드가 null이 아닐 때 까지 반복한다.
			//pre는 마지막 하나 앞 노드, last는 마지막 노드를 가리킬때 까지 반복한다.  
			while(lastNode != null) { //root의 다음 노드가 없으면 root를 삭제하는 경우도 포함한다.
				preNode = lastNode;
				lastNode = lastNode.next;
			}
			preNode.next = null; //연결을 끊는다. 가비지 컬렉터 책임
		}
		size -= 1;
		return true;
	}
	
	public LinkedListNode get(int idx) { //해당 인덱스의 데이터를 반환
		LinkedListNode result = root;
		int curIdx=0;
		while(curIdx != idx && result.next != null) { //result.next가 null이아니고, idx와 curIdx가 같지 않을 때 반복 
			result = result.next;
			curIdx += 1;
		}
		//curIdx==idx일 때 탈출 -> 원하는 동작
		//result.next == null일 때 탈출 -> 원하지 않는 동작 -> return null
		if(curIdx == idx) {
			return result;
		}else {
			return null;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	
	public static void main(String[] args) {
		LinkedList<Integer> temp = new LinkedList<>();
		// temp.add(index, element);
	}

}
