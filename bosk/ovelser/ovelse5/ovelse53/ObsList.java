package bosk.ovelser.ovelse5.ovelse53;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;

public class ObsList<T> extends Observable{
	//Uses the multiton pattern
	private ArrayList<T> list;
	private ArrayList<Observer> obs = new ArrayList<Observer>();
	private static final HashMap<ArrayList<Object>, ObsList<Object>> instances = new HashMap<ArrayList<Object>, ObsList<Object>>();
	
	private ObsList(ArrayList<T> l){
		list = l;
	}

	public static ObsList<Object> getObservableListInstance(ArrayList<Object> l){
		ObsList<Object> instance = instances.get(l);
		if(instance == null){
			instance = new ObsList(l);
			instances.put(l, instance);
		}
		return instance;
	}
	
	@Override
	public void addObserver(Observer o){
		obs.add(o);
	}
	
	@Override
	public void deleteObserver(Observer o){
		obs.remove(o);
	}
	
	//Notify observers are called in: add(Object o), add(int index, Object o), addAll(Collection c),
	//clear(), remove(Object o), remove(int index), set(int index, Object o), retainAll(Collection c).
	
	@Override
	public void notifyObservers(){
		for(Observer o : obs){
			o.update(this, "List has been updated");
		}
	}
	
	@Override
	public void notifyObservers(Object args){
		for(Observer o : obs){
			o.update(this, args);
		}
	}
	
	
	
	//List methods
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator<T> iterator() {
		return list.iterator();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(T e) {
		notifyObservers();
		return list.add(e);
	}

	public boolean remove(Object o) {
		notifyObservers();
		return list.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection<? extends T> c) {
		notifyObservers();
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		notifyObservers();
		return list.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		notifyObservers();
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		notifyObservers("List may have changed");
		return list.retainAll(c);
	}

	public void clear() {
		notifyObservers();
		list.clear();
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public T get(int index) {
		return list.get(index);
	}

	public T set(int index, T element) {
		notifyObservers();
		return list.set(index, element);
	}

	public void add(int index, T element) {
		list.add(index, element);
	}

	public T remove(int index) {
		return list.remove(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}
