package data_structures;
import java.util.Iterator;

public abstract class AbstractList<T> implements List<T>
{
	public String toString()
	{
		StringBuilder sb = new StringBuilder(500);
		Iterator<T> it = this.iterator();
		sb.append("[");
		while(it.hasNext())
		{
			sb.append(it.next());
			if(it.hasNext())
			{
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	public int hashCode()
	{
		final int SHUFFLE = 97;
		int hash = 0;
		for(T t : this)
		{
			hash += SHUFFLE * t.hashCode();
		}
		return hash;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj)
	{
		if(!(obj instanceof List))
		{
			return false;
		}
		if(obj == this)
		{
			return true;
		}
		List<T> list = (List<T>) obj;
		if(list.size() != this.size())
		{
			return false;
		}
		Iterator<T> it1 = this.iterator();
		Iterator<T> it2 = list.iterator();
		while(it1.hasNext())
		{
			if(!it1.next().equals(it2.next()))
			{
				return false;
			}
		}
		return true;
	}
}
