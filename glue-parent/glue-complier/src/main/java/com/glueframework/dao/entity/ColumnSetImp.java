package com.glueframework.dao.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ColumnSetImp<T>  implements ColumnSet<T>{
	List<T> arrayList;
	Class<T> elementType;
	public ColumnSetImp(List<T> arrayList , Class<T> cla) {
		super();
		this.arrayList = arrayList;
		elementType = cla;
	}
	public  void forEach(Consumer<? super T> action) {
		arrayList.forEach(action);
	}
	@Override
	public String toString() {
		return  arrayList.toString();
	}
	public int size() {
		return arrayList.size();
	}
	public boolean isEmpty() {
		return arrayList.isEmpty();
	}
	public boolean contains(Object o) {
		return arrayList.contains(o);
	}
	public Iterator<T> iterator() {
		return arrayList.iterator();
	}
	public Object[] toArray() {
		return arrayList.toArray();
	}
	public <T> T[] toArray(T[] a) {
		return arrayList.toArray(a);
	}
	public boolean add(T e) {
		return arrayList.add(e);
	}
	public boolean remove(Object o) {
		return arrayList.remove(o);
	}
	public boolean containsAll(Collection<?> c) {
		return arrayList.containsAll(c);
	}
	public boolean addAll(Collection<? extends T> c) {
		return arrayList.addAll(c);
	}
	public boolean addAll(int index, Collection<? extends T> c) {
		return arrayList.addAll(index, c);
	}
	public boolean removeAll(Collection<?> c) {
		return arrayList.removeAll(c);
	}
	public boolean retainAll(Collection<?> c) {
		return arrayList.retainAll(c);
	}
	public  void replaceAll(UnaryOperator<T> operator) {
		arrayList.replaceAll(operator);
	}
	public  boolean removeIf(Predicate<? super T> filter) {
		return arrayList.removeIf(filter);
	}
	public  void sort(Comparator<? super T> c) {
		arrayList.sort(c);
	}
	public void clear() {
		arrayList.clear();
	}
	public boolean equals(Object o) {
		return arrayList.equals(o);
	}
	public int hashCode() {
		return arrayList.hashCode();
	}
	public T get(int index) {
		return arrayList.get(index);
	}
	public T set(int index, T element) {
		return arrayList.set(index, element);
	}
	public void add(int index, T element) {
		arrayList.add(index, element);
	}
	public  Stream<T> stream() {
		return arrayList.stream();
	}
	public T remove(int index) {
		return arrayList.remove(index);
	}
	public  Stream<T> parallelStream() {
		return arrayList.parallelStream();
	}
	public int indexOf(Object o) {
		return arrayList.indexOf(o);
	}
	public int lastIndexOf(Object o) {
		return arrayList.lastIndexOf(o);
	}
	public ListIterator<T> listIterator() {
		return arrayList.listIterator();
	}
	public ListIterator<T> listIterator(int index) {
		return arrayList.listIterator(index);
	}
	public List<T> subList(int fromIndex, int toIndex) {
		return arrayList.subList(fromIndex, toIndex);
	}
	public  Spliterator<T> spliterator() {
		return arrayList.spliterator();
	}
	@Override
	public Class<T> getElementType() {
		return elementType;
	}

	
}
