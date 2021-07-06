import java.awt.Color;

public class SnakeLinkedList {
	SnakeNode head = null;
	
	public void addHead(int x, int y, Color c) {
		head = new SnakeNode(x, y, c);
	}
	
	public void addJoint(int x, int y, Color c) {
		SnakeNode newJoint = new SnakeNode(x, y, c);
		newJoint.setNext(head.getNext());
		head.setNext(newJoint);
	}
	
	public void addTail(Color c) {
		SnakeNode curr = head;
		
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		
		curr.setNext(new SnakeNode(curr.getX() + 10, curr.getY() + 10, c));
	}
	
	public SnakeNode getHead() {
		return head;
	}
	
	public SnakeNode getJoint(int i) {
		SnakeNode curr = head;
		
		for (int k = 0; k < i; k++) {
			curr = curr.getNext();
		}
		
		return curr;
	}
	
	public void addFirst(SnakeNode node) {
		node.setNext(head);
		head = node;
	}
	
	public void addLast(SnakeNode node) {
		if (head == null) {
			head = node;
			return;
		}
		
		SnakeNode curr = head;
		
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		
		curr.setNext(node);
	}
	
	public void snakeMove(int dots, boolean left, boolean right, boolean up, boolean down, final int SIZE) {
		SnakeNode joint;
		SnakeNode jointPrev;
		
		for (int z = dots; z > 0; z--) {
			joint = this.getJoint(z);
			jointPrev = this.getJoint(z - 1);
			joint.setX(jointPrev.getX());
			joint.setY(jointPrev.getY());
		}

		if (left) {
			head.setX(head.getX() - SIZE);
		}
		
		if (right) {
			head.setX(head.getX() + SIZE);
		}
		
		if (up) {
			head.setY(head.getY() - SIZE);
		}
		
		if (down) {
			head.setY(head.getY() + SIZE);
		}
	}
}