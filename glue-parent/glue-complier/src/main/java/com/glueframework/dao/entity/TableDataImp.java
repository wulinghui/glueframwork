package com.glueframework.dao.entity;

import java.util.ArrayList;
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

public class TableDataImp  implements TableData{
	List<Record> list;
	
	public TableDataImp(List list) {
		super();
		this.list = list;
	}
	@Override
	public String toString() {
		return list.toString();
	}

	public TableDataImp() {
		this(new ArrayList<>());
	}

	public  void forEach(Consumer<? super Record> action) {
		list.forEach(action);
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator<Record> iterator() {
		return list.iterator();
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(Record e) {
		return list.add(e);
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection<? extends Record> c) {
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Record> c) {
		return list.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	public  void replaceAll(UnaryOperator<Record> operator) {
		list.replaceAll(operator);
	}

	public  boolean removeIf(Predicate<? super Record> filter) {
		return list.removeIf(filter);
	}

	public  void sort(Comparator<? super Record> c) {
		list.sort(c);
	}

	public void clear() {
		list.clear();
	}

	public boolean equals(Object o) {
		return list.equals(o);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public Record get(int index) {
		return list.get(index);
	}

	public Record set(int index, Record element) {
		return list.set(index, element);
	}

	public void add(int index, Record element) {
		list.add(index, element);
	}

	public  Stream<Record> stream() {
		return list.stream();
	}

	public Record remove(int index) {
		return list.remove(index);
	}

	public  Stream<Record> parallelStream() {
		return list.parallelStream();
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	public ListIterator<Record> listIterator() {
		return list.listIterator();
	}

	public ListIterator<Record> listIterator(int index) {
		return list.listIterator(index);
	}

	public List<Record> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	public  Spliterator<Record> spliterator() {
		return list.spliterator();
	}

	@Override
	public Object get(int index, String name) {
		Record record = this.get(index);
		if( record == null) return null;
		return record.get(name);
	}

	
	
}
