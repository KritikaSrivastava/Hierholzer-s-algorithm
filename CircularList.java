import java.util.*;

 class CircularList<T> implements Iterable<T> {
	public class Entry<T> {
		T element;
		Entry<T> next;
	
	    Entry(T x,Entry<T> nxt) {
		   element = x;
		   next = nxt;
		
	   }
	}
	
	Entry<T> header,tail;
	int size;
	
	CircularList() {
		header = new Entry<T>(null,null);
		tail = header;
		size = 0;
	}
	
	void add(T x) {
		if(header == null){
			header = new Entry<>(x,null);
			tail = header;
		}
		else {
			tail.next = new Entry<>(x,null);
			tail = tail.next;
			tail.next = null;
		}
		size++;
	}
	
	void reset(){
		header.element=null;
		header.next = null;
		tail = header;
		size = 0;
	}
	
	public Iterator<T> iterator() { return new CLIterator<>(header);}
	
	private class CLIterator<E> implements Iterator<E> {
		Entry<E> cursor,prev;
		
		CLIterator(Entry<E> head) {
			cursor = head;
			prev = null;
		}
		
		public boolean hasNext() {
		    return cursor.next != null;
		}
		
		public E next() {
		    prev = cursor;
		    cursor = cursor.next;
		    return cursor.element;
		}

	}
	
	
	

}
