package a4;
import java.util.ArrayList;
import java.util.Iterator;
@SuppressWarnings("rawtypes")
public  class ObjectCollection implements ICollection{
private ArrayList<GameObject> theCollection;
private ArrayList<GameObject> removeCollection;

	public ObjectCollection()
	{
		theCollection = new ArrayList<GameObject>();		
	}
	
	public void add(GameObject newObj) {
		// TODO Auto-generated method stub
		
		theCollection.add(newObj);
	}
	public Iterator getIterator()
	{
		return new ArrayListIterator(theCollection);
	}
	public int getSize()
	{
		return theCollection.size();
	}
	@Override
	public Iterator iterator() {
		ArrayListIterator itr = new ArrayListIterator(theCollection);
		// TODO Auto-generated method stub
		return itr;
	}
	
	private class ArrayListIterator implements Iterator<GameObject> {
		
		private int currElementIndex,next =0;
		
		public ArrayListIterator(ArrayList<GameObject> obj)
		{
			currElementIndex=-1;
			theCollection = obj;
		}
		public boolean hasNext() {
			// TODO Auto-generated method stub
			/* ERROR!!!
			if (theCollection.size() <= 0) return false;
			if (currElementIndex== theCollection.size()-1 )
			{
				return false;
			}
			 return true;	
			 */
			
			 if (theCollection.size() <= 0) return false;
				if (next < theCollection.size() )//return true if the next index is within the collection size
				{
					return true;
				}
				 return false;	
				 
		}
		@Override
		public GameObject next() {
			currElementIndex = next;
			GameObject currentObj = theCollection.get(currElementIndex);
			next +=1; // Increament the next		
			// TODO Auto-generated method stub
			return currentObj;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			theCollection.remove(currElementIndex);
		}
		public void remove(int whichOne)
		{
			theCollection.remove(whichOne);
		}
	}
}
