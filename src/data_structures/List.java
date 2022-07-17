package data_structures;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public interface List<T> extends Iterable<T>
{
	ListIterator<T> listIterator();
	
	ListIterator<T> listIterator(int pos);
	
	void sort(Comparator<T> c);
	
	default int size()
	{
		int size = 0;
		for(T t : this)
		{
			size++;
		}
		return size;
	}
	
	default void clear()
	{
		Iterator<T> it = this.iterator();
		while(it.hasNext())
		{
			it.next();
			it.remove();
		}
	}
	
	default boolean contains(T e)
	{
		for(T t : this)
		{
			if(t.equals(e))
			{
				return true;
			}
		}
		return false;
	}
	
	default boolean isEmpty()
	{
		Iterator<T> it  = this.iterator();
		return it.hasNext();
	}
	
	default void add(T e)
	{
		addLast(e);
	}
	
	default void remove(T e)
	{
		Iterator<T> it = this.iterator();
		while(it.hasNext())
		{
			if(it.next().equals(e))
			{
				it.remove();
			}
		}
	}
	
	default void addFirst(T e)
	{
		ListIterator<T> listIterator = this.listIterator();
		listIterator.add(e);
	}
	
	default void addLast(T e)
	{
		ListIterator<T> listIterator = this.listIterator();
		while(listIterator.hasNext())
		{
			listIterator.next();
		}
		listIterator.add(e);
	}
	
	default T removeFirst()
	{
		ListIterator<T> listIterator = this.listIterator();
		if(!listIterator.hasNext())
		{
			throw new NoSuchElementException();
		}
		T t = listIterator.next();
		listIterator.remove();
		return t;
	}
	
	default T removeLast()
	{
		ListIterator<T> li = this.listIterator();
		T t = null;
		while(li.hasNext())
		{
			t = li.next();
		}
		if(t == null)
		{
			throw new NoSuchElementException();
		}
		li.remove();
		return t;	
	}
	
	default T getFirst()
	{
		Iterator<T> it = this.iterator();
		if(!it.hasNext())
		{
			throw new NoSuchElementException();
		}
		return it.next();
	}
	
	default T getLast()
	{
		Iterator<T> it = this.iterator();
		T t = null;
		while(it.hasNext())
		{
			t = it.next();
		}
		if(t == null)
		{
			throw new NoSuchElementException();
		}
		return t;
	}
}
