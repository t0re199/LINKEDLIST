package data_structures;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T>
{
	private static enum IteratorStatus{FORWARD, BACKWARD, UNKNOWN}
																			
	private Nodo<T> inizio;
	private Nodo<T> fine;
	private int size;
	private int numOp;
	
	
	public LinkedList()
	{
		inizio = null;
		fine = null;
		size = 0;
		numOp = 0;
	}
	
	
	public LinkedList(LinkedList<T> linkedList)
	{
		this();
		for(T t : linkedList)
		{
			add(t);
		}
	}
	
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	
	public void add(T e)
	{
		Nodo<T> toAdd = new Nodo<>();
		toAdd.info = e;
		if(inizio == null)
		{
			toAdd.previous = null;
			if(fine == null)
			{
				toAdd.next = null;
			}
			inizio = toAdd;
			fine = toAdd;
		}
		else
		{
			fine.next = toAdd;
			toAdd.previous = fine;
			toAdd.next = null;
			fine = toAdd;
		}
		size++;
		onListEdited();
	}
	
	
	public void addFirst(T e)
	{
		if(inizio != null)
		{	
			Nodo<T> toAdd = new Nodo<>();
			toAdd.info = e;
			inizio.previous = toAdd;
			toAdd.previous = null;
			toAdd.next = inizio;
			inizio = toAdd;
			size++;
			onListEdited();
		}
		else
		{
			add(e);
		}
	}
	
	
	public void addLast(T e)
	{
		add(e);
	}
	
	
	public void remove(T e)
	{
		Nodo<T> rm = inizio;
		boolean found = false;
		while(rm != null)
		{
			if(rm.info.equals(e))
			{
				found = true;
				break;
			}
			rm = rm.next;
		}
		if(found)
		{
			remove(rm);
		}
	}
	
	
	private void remove(Nodo<T> rm)
	{
		if(rm == inizio)
		{
			inizio = inizio.next;
			if(inizio == null)
			{
				fine = null;
			}
			else
			{
				inizio.previous = null;
			}
		}
		else if(rm == fine)
		{
			fine = fine.previous;
			fine.next = null;
		}
		else
		{
			rm.previous.next = rm.next;
			rm.next.previous = rm.previous;
		}
		size--;
		onListEdited();
	}
	
	
	public T removeFirst()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		T t = inizio.info;
		remove(inizio);
		return t;
	}
	
	
	public T removeLast()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		T t = fine.info;
		remove(fine);
		return t;
	}
	
	
	public T getFirst()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		return inizio.info;
	}
	
	
	public T getLast()
	{
		if(size == 0)
		{
			throw new NoSuchElementException();
		}
		return fine.info;
	}


	public void clear()
	{
		inizio = null;
		fine = null;
		size = 0;
	}
	
	
	public int size()
	{
		return size;
	}
	
	
	public void sort(Comparator<T> c)
	{
		bubbleSort(c);
	}
	
	
	private void bubbleSort(Comparator<T> c)
	{
		if(size == 0)
		{
			throw new IllegalArgumentException("Empty List");
		}
		if(size == 1)
		{
			return;
		}
		boolean swap = true;
		Nodo<T> n = inizio;
		while(swap)
		{
			swap = false;
			while(n.next != null)
			{
				if(c.compare(n.info, n.next.info) > 0)
				{
					swap = true;
					T t = n.next.info;
					n.next.info = n.info;
					n.info = t;
				}
				n = n.next;
			}
			n = inizio;
		}
	}
	
	
	private void onListEdited()
	{
		numOp++;
	}
	
	
	@Override
 	public ListIterator<T> listIterator()
	{
		return new LinkedListIterator();
	}
	

	@Override
	public ListIterator<T> listIterator(int pos)
	{
		return new LinkedListIterator(pos);
	}
	

	@Override
	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}
	
	
	private static class Nodo<E>
	{
		E info;
		Nodo<E> next;
		Nodo<E> previous;
	}
	
	
	private class LinkedListIterator implements ListIterator<T>
	{
		private Nodo<T> cursor;
		private IteratorStatus status = IteratorStatus.UNKNOWN;
		private int index;
		private boolean editable = false;
		private int numOp;
		
		
		public LinkedListIterator()
		{
			cursor = new Nodo<>();
			cursor.previous = null;
			cursor.next = inizio;
			index = 0;
			this.numOp = LinkedList.this.numOp;
		}
		
		
		public LinkedListIterator(int pos)
		{
			this();
			if(pos < 0 || pos > size)
			{
				throw new IndexOutOfBoundsException();
			}
			setupIterator(pos);
			this.index = pos;
		}
		
		
		private void setupIterator(int pos)
		{
			while(pos > 0)
			{
				next();
				pos--;
			}
		}
		
		
		@Override
		public void add(T e)
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			editable = false;
			if(cursor.previous == null)
			{
				LinkedList.this.addFirst(e);
				cursor.previous = inizio;
			}
			else if(cursor.next == null)
			{
				LinkedList.this.addLast(e);;
				cursor.previous = fine;
			}
			else
			{
				Nodo<T> toAdd = new Nodo<>();
				toAdd.info = e;
				cursor.previous.next = toAdd;
				cursor.next.previous = toAdd;
				toAdd.next = cursor.next;
				toAdd.previous = cursor.previous;
				cursor.previous = toAdd; 
				LinkedList.this.size++;
				LinkedList.this.onListEdited();
			}
			updateNumOp();
			index++;
		}
		
		
		@Override
		public boolean hasNext()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(cursor.previous == null)
			{
				return inizio != null;
			}
			return cursor.next != null;
		}
		

		@Override
		public boolean hasPrevious()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(cursor.next == null)
			{
				return fine != null;
			}
			return cursor.previous != null;
		}
		

		@Override
		public T next()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			status = IteratorStatus.FORWARD;
			editable = true;
			T t = cursor.next.info;
			cursor.previous = cursor.next;
			cursor.next = cursor.next.next;
			index++;
			return t;
		}
		

		@Override
		public int nextIndex()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			return index;
		}

		
		@Override
		public T previous()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			status = IteratorStatus.BACKWARD;
			editable = true;
			T t = cursor.previous.info;
			cursor.next = cursor.previous;
			cursor.previous = cursor.previous.previous;
			index--;
			return t;
		}
		

		public int previousIndex()
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			return index -1;
		}
		

		@Override
		public void remove() 
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(!editable)
			{
				throw new IllegalStateException();
			}
			if(status.equals(IteratorStatus.FORWARD))
			{
				LinkedList.this.remove(cursor.previous);
				cursor.previous = cursor.previous != null ? cursor.previous.previous : null;
				index--; 
			}
			else if(status.equals(IteratorStatus.BACKWARD))
			{
				LinkedList.this.remove(cursor.next);
				cursor.next = cursor.next != null ? cursor.next.next : null;
			} 
			editable = false;
			updateNumOp();
		}
		

		@Override
		public void set(T e)
		{
			if(verifyCuncurrentModification())
			{
				throw new ConcurrentModificationException();
			}
			if(!editable)
			{
				throw new IllegalStateException();
			}
			if(status.equals(IteratorStatus.FORWARD))
			{
				cursor.previous.info = e;
			}
			else if(status.equals(IteratorStatus.BACKWARD))
			{
				cursor.next.info = e;
			}
			else
			{
				throw new RuntimeException("Unknown Iterator Status.");
			}	
		}
		
		
		private void updateNumOp()
		{
			this.numOp = LinkedList.this.numOp;
		}
		
		
		private boolean verifyCuncurrentModification()
		{
			return this.numOp != LinkedList.this.numOp;
		}
	}
	
	public static void main(String[] args)
	{
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(int i = 0; i < 10; i++)
		{
			ll.add(i);
		}
		System.out.println(ll);
		ListIterator<Integer> li = ll.listIterator();
		li.next();
		li.next();
		li.remove();
		System.out.println(ll);
	}
}
