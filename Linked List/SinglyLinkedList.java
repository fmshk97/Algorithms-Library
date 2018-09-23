class SinglyLinkedList<T> {
	private int length;
	public SinglyListNode<T> head;
	public SinglyLinkedList() { length = 0; }
	public SinglyLinkedList(T[] inputArray) {
		length = 0;
		for (int i = 0; i < inputArray.length; i++) {
			add(inputArray[i]);
		}
	}
	public void add(T data) {
		SinglyListNode<T> newNode = new SinglyListNode<T>(data, null);
		if (head == null) {
			head = newNode;
		}
		else {
			SinglyListNode<T> currentNode = head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
		}
		length++;
	}
	public void addToStart(T data) {
		SinglyListNode<T> newNode = new SinglyListNode<T>(data, head);
		head = newNode;
		length++;
	}
	public T remove(T input) throws LinkedListException {
		SinglyListNode<T> currentNode = head, previousNode = head;
		while (currentNode != null && currentNode.data != input) {
			previousNode = currentNode;
			currentNode = currentNode.next;
		}
		if (currentNode == null || currentNode.data != input) {
			throw new LinkedListException(input + " is not present in the linked list.");
		}
		if (currentNode == head) {
			head = currentNode.next;
		}
		else {
			previousNode.next = currentNode.next;
		}
		T data = currentNode.data;
		currentNode = null;
		System.gc();
		length--;
		return data;
	}
	public T removeAt(int index) throws LinkedListException {
		SinglyListNode<T> currentNode = head;
		T data;
		if (index >= length) {
			throw new LinkedListException("Index out of bound.");
		}
		else if (index == 0) {
			head = head.next;
			data = currentNode.data;
			currentNode = null;
		}
		else {
			int i = 0;
			while (i < index - 1) {
				currentNode = currentNode.next;
				i++;
			}
			data = currentNode.next.data;
			currentNode.next = currentNode.next.next;
		}
		System.gc();
		length--;
		return data;
	}
	public int size() {
		return length;
	}
	public String toString() {
		StringBuilder linkedList = new StringBuilder();
		SinglyListNode<T> currentNode = head;
		linkedList.append("[ ");
		while (currentNode != null) {
			linkedList.append(currentNode.data + " --> ");
			currentNode = currentNode.next;
		}
		linkedList.append("NULL ]");
		return linkedList.toString();
	}
}
class SinglyListNode<T> {
	T data;
	SinglyListNode<T> next;
	public SinglyListNode() {}
	public SinglyListNode(T data, SinglyListNode<T> next) {
		this.data = data;
		this.next = next;
	}
}
class LinkedListException extends Exception {
	private static final long serialVersionUID = 1L;
	public LinkedListException(String s) {
        super(s);
    }
}