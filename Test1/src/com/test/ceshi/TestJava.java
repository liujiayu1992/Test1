package com.test.ceshi;

import java.util.Arrays;
import java.util.List;

public class TestJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String[] s = {"aa","bb","cc"};
	        List<String> strlist = Arrays.asList(s);
	        for(String str:strlist){
	            System.out.println(str);
	        }
	        System.out.println("------------------------");
	        //基本数据类型结果打印为一个元素
	        int[] i ={11,22,33}; 
	        List intlist = Arrays.asList(i);
	        for(Object o:intlist){
	            System.out.println(o.toString());
	        }
	        System.out.println("------------------------");
	        Integer[] ob = {11,22,33};
	        List<Integer> oblist = Arrays.asList(ob);
	        for(int a:oblist){
	            System.out.println(a);
	        }
	        System.out.println("------------------------");
	    }

	}
