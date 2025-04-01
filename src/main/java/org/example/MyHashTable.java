package org.example;

import java.util.*;

public class MyHashTable<T> implements MyHashTableInterface<T> {
    List<LinkedList<Pair<String, T>>> hashTable = new ArrayList<>(10);

    private  int hashValue(String string){
        char[] charArray = string.toCharArray();
        int value = 0;
        for (char ch : charArray) {
            value = value + (int) ch;
        }
        double returnValue = Math.floor((hashTable.size() * (value * ((Math.sqrt(5) - 1) / 2)) % hashTable.size()));
        return  (int) returnValue;
    };

    @Override
    public void insert(String key, T element){
        int hashValue = hashValue(key);
            LinkedList<Pair<String,T>> list = hashTable.get(hashValue);
          if (list != null) {
            list.add(new Pair(key, element));
          } else {
              LinkedList<Pair<String,T>> newList = new LinkedList<>();
              newList.add(new Pair(key, element));
              hashTable.set(hashValue, newList);
          }

    }

    @Override
    public T search(String key){
        int hashValue = hashValue(key);
        LinkedList<Pair<String, T> > list = hashTable.get(hashValue);
        if (list == null) {
            return null;
        } else {
          Optional<Pair<String, T>> found = list.stream().filter(i -> Objects.equals(i.first, key)).findFirst();
            return found.map(pair -> pair.second).orElse(null);
        }
    };

    @Override
    public void delete(String key) {
        int hashValue = hashValue(key);
        LinkedList<Pair<String, T> > list = hashTable.get(hashValue);
        if (list != null && list.size() == 1) {
            list.clear();
        } else if (list != null && list.size() > 1) {

            list.remove(list.stream().filter(i -> Objects.equals(i.first, key)).findFirst());
        }
    };

    private static class Pair<K,V> {
        public K first;
        public V second;
        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}
