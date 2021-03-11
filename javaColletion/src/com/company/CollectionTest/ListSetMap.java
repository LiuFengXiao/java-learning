package com.company.CollectionTest;
import  java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListSetMap {
    public static void main(String[] args){
        ListSetMap instance =new ListSetMap();
        instance.arrayList.add("jj");
        instance.linkedList.get(0);
        instance.concurrentHashMap.size();
    }
   
    //  -----List-----
    ArrayList<String> arrayList =new ArrayList<>();


    LinkedList<String> linkedList =new LinkedList<>();

    Vector<String> vector =new Vector<>();

    CopyOnWriteArrayList<String> copyOnWriteArrayList =new CopyOnWriteArrayList<>();
   
    // -------map---------
    HashMap<String,Integer> hashMap =new HashMap<>();

    LinkedHashMap<String,Integer> linkedHashMap =new LinkedHashMap<>();

    TreeMap<String,Integer> treeMap =new TreeMap<>();

    Hashtable<String,Integer> hashtable =new Hashtable<>();

    ConcurrentHashMap<String,Integer> concurrentHashMap =new ConcurrentHashMap<>();


    //------set-------
    HashSet<String> hashSet =new HashSet<>();




}
