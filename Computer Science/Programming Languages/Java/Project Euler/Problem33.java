package de.itter.euler;

import org.apache.commons.math3.fraction.Fraction;

public class Problem33 {

	public static void main(String[] args) {
		Fraction res = new Fraction(1,1);
		for(int a=1;a<=9;a++){
			for(int b=1;b<=9;b++){
				for(int c=1;c<=9;c++){
					for(int d=1;d<=9;d++){
						if(isCancellingFraction(a, b, c, d)){
							System.out.println(a+","+b+","+c+","+d);
							res = res.multiply(new Fraction(a*10+b,c*10+d));
						}
					}
				}
			}
		}
		System.out.println(res);
	}
	
	public static boolean isCancellingFraction(int a,int b,int c,int d){
		
		Fraction f = new Fraction(a*10+b,c*10+d);
		if(f.compareTo(Fraction.ONE)>=0){
			return false;
		}
		
		if(a==c){
			if(f.compareTo(new Fraction(b,d))==0){
				return true;
			}
		}
		
		if(b==d){
			if(f.compareTo(new Fraction(a,c))==0){
				return true;
			}
		}
		
		if(a==d){
			if(f.compareTo(new Fraction(b,c))==0){
				return true;
			}
		}
		
		if(b==c){
			if(f.compareTo(new Fraction(a,d))==0){
				return true;
			}
		}
		
		return false;
	}

}
