package com.googlecode.jbridges.lib.problemas;

import java.util.*;

public class Ranking {
	
	int max;//tiempo maximo
	public static List ranking=new LinkedList();
	
	public Ranking (int max){
		super();
		this.max=max;
	}
	
	public static List getRanking() {
		return ranking;
	}
	public static void setRanking(List ranking) {
		Ranking.ranking = ranking;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
	
	

}
