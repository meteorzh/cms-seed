package com.github.wenzhencn.cmsseed.web;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		List<Integer> l2 = new ArrayList<>();
		
		int i = 0;
        for (Integer l : list) {
            l2.add(l);
            if (i >= 1 && i % 10 == 0) {
                System.out.println(l2.size());
                l2.clear();
            }
            i++;
        }
        System.out.println(l2.size());
	}
	
}
