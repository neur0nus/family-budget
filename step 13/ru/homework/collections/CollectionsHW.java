package ru.homework.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsHW {
	
	public static class Elem {
		public final String value;
		public Elem(String s) {this.value = s;}
		@Override
		public String toString() {
			return this.value;
		}
	}
	public static class CmprtrSet implements Comparator <Elem>{
		@Override
		public int compare(Elem arg0, Elem arg1) {
			return arg0.value.compareTo(arg1.value);
		}
	}
	
	 public static void main(String[] args) throws Exception {
	      
	        
		 List<Elem> elemList = new ArrayList<>();
	        elemList.add(new Elem("Кот"));
	        elemList.add(new Elem("Пес"));
	        elemList.add(new Elem("Жираф"));
	        elemList.add(new Elem("Рыбка"));
	        System.out.println("Список Elem: " + elemList);
	        
	        Set<Elem> elemSet = new TreeSet<>(new CmprtrSet());
	        elemSet.addAll(elemList);
	        System.out.println("TreeSet отсортированный: " + elemSet);
	    }
	}