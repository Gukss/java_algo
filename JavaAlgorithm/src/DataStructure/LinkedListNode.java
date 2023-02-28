package DataStructure;

public class LinkedListNode{
	int data;
	LinkedListNode next;
	
	public LinkedListNode(int data, LinkedListNode next) {
		this.data = data;
		this.next = next;
	}
	
	public LinkedListNode(int data) {
		this.data = data;
		this.next = null;
	}
	
	public LinkedListNode() {
		this.data = 0;
		this.next = null;
	}
	
	public int getData() {
		return this.data;
	}
}