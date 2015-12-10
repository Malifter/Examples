package hashstructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class HashTableSeparateChaining<K extends Comparable<? super K>, V extends Comparable<? super V>> {
	private static final float LOAD_FACTOR_MAX = 0.75f;
	private static final float LOAD_FACTOR_MIN = 0.25f;
	private static final int MINIMUM_BUCKETS = 16;
	private int size = 0;
	@SuppressWarnings("unchecked")
	ArrayList<BucketEntry<K, V>> [] buckets = (ArrayList<BucketEntry<K, V>>[])Array.newInstance(ArrayList.class, MINIMUM_BUCKETS);
	
	private int hash(K key) {
		return key.hashCode() % buckets.length; // TODO: Consider writing my own hash function
	}
	
	private float loadFactor() {
		return size / (float) buckets.length;
	}
	
	public int size() {
		return size;
	}
	
	// O(n)
	private void resize(int newSize) {
		newSize = newSize < MINIMUM_BUCKETS ? MINIMUM_BUCKETS : newSize;
		@SuppressWarnings("unchecked")
		ArrayList<BucketEntry<K, V>> [] temp = (ArrayList<BucketEntry<K, V>>[])Array.newInstance(ArrayList.class, newSize);
		// Fill the newly sized hash table with the data from the old buckets
		for(BucketEntry<K, V> entry: entrySet()) {
			int hash = entry.getKey().hashCode() % newSize;
			if(temp[hash] == null) { // empty bucket
				temp[hash] = new ArrayList<BucketEntry<K, V>>();
			}
			temp[hash].add(new BucketEntry<K, V>(entry.getKey(), entry.getValue()));
		}
		clear(); // Ensure that this doesn't wipe out the entry pointers
		buckets = null;
		buckets = temp;
	}
	
	// O(1) to O(n)
	public V get(K key) {
		int hash = hash(key);
		if(buckets[hash] == null) { // empty bucket
			return null; // not found
		} else { // bucket with content
			// search for existing key
			for(BucketEntry<K, V> entry: buckets[hash]) {
				if(entry.getKey().compareTo(key) == 0) {
					return entry.getValue(); // return curr V
				}
			}
		}
		return null; // not found
	}
	
	// O(1) to O(n)
	public V put(K key, V value) {
		int hash = hash(key);
		if(buckets[hash] == null) { // empty bucket
			buckets[hash] = new ArrayList<BucketEntry<K, V>>();
		} else { // bucket with content
			// search for existing key
			for(BucketEntry<K, V> entry: buckets[hash]) {
				if(entry.getKey().compareTo(key) == 0) {
					entry.setValue(value);
					return entry.getValue(); // return previous V
				}
			}
		}
		// insert new key
		buckets[hash].add(new BucketEntry<K, V>(key, value));
		size++;
		if(loadFactor() > LOAD_FACTOR_MAX) resize(buckets.length * 2);
		return null; // no previous value
	}
	
	// O(1) to O(n)
	public V remove(K key) {
		int hash = hash(key);
		if(buckets[hash] == null) { // empty bucket
			return null; // not found
		} else { // bucket with content
			// search for existing key
			Iterator<BucketEntry<K, V>> iter = buckets[hash].iterator();
			while(iter.hasNext()) {
				BucketEntry<K, V> entry = iter.next();
				if(entry.getKey().compareTo(key) == 0) {
					iter.remove();
					size--;
					if(loadFactor() < LOAD_FACTOR_MIN) resize(buckets.length / 2);
					else if(buckets[hash].size() == 0) buckets[hash] = null;
					return entry.getValue(); // return previous V
				}
			}
		}
		return null; // not found
	}
	
	// O(1) to O(n)
	public boolean containsKey(K key) {
		int hash = hash(key);
		if(buckets[hash] == null) { // empty bucket
			return false; // not found
		} else { // bucket with content
			// search for existing key
			for(BucketEntry<K, V> entry: buckets[hash]) {
				if(entry.getKey().compareTo(key) == 0) {
					return true; // found key
				}
			}
		}
		return false; // not found
	}
	
	// O(n)
	public boolean containsValue(V value) {
		for(BucketEntry<K, V> entry: entrySet()) {
			if(entry.getValue().compareTo(value) == 0) {
				return true;
			}
		}
		return false;
	}
	
	// O(n)
	public Set<K> keySet() {
		Set<K> keySet = new HashSet<K>();
		// Even though it's a double for loop, it still only iterates over a total of O(n) elements
		for(ArrayList<BucketEntry<K, V>> array: buckets) {
			if(array != null) {
				for(BucketEntry<K, V> entry: array) {
					keySet.add(entry.getKey());
				}
			}
		}
		return keySet;
	}
	
	// O(n)
	public Set<BucketEntry<K, V>> entrySet() {
		Set<BucketEntry<K, V>> entrySet = new HashSet<BucketEntry<K, V>>();
		// Even though it's a double for loop, it still only iterates over a total of O(n) elements
		for(ArrayList<BucketEntry<K, V>> array: buckets) {
			if(array != null) {
				for(BucketEntry<K, V> entry: array) {
					entrySet.add(entry);
				}
			}
		}
		return entrySet;
	}
	
	// O(n)
	@SuppressWarnings("unchecked")
	public void clear() {
		// go through and null everything out
		for(ArrayList<BucketEntry<K, V>> array: buckets) {
			if(array != null) {
				for(BucketEntry<K, V> entry: array) {
					entry.setValue(null);
					entry = null;
				}
			}
			array = null;
		}
		buckets = null;
		buckets = (ArrayList<BucketEntry<K, V>>[])Array.newInstance(ArrayList.class, MINIMUM_BUCKETS);
	}
	
	public String toFullString() {
		String out = "";
		for(ArrayList<BucketEntry<K, V>> array: buckets) {
			out += array + ": ";
			if(array != null) {
				for(BucketEntry<K, V> entry: array) {
					out += "(" + entry.getKey() + ", " + entry.getValue() + ")  ";
				}
			}
			out += "\n";
		}
		return out;
	}

	private static class BucketEntry<K extends Comparable<? super K>, V extends Comparable<? super V>> {
		private K key;
		private V value;
		
		public BucketEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
}
