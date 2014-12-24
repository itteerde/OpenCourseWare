package de.itter.euler;

public class Problem27 {

	public static void main(String[] args) {
		int maxLength = 0;
		int bestA = 0;
		int bestB = 0;
		
		for(int a=-999;a<1000;a++){
			for(int b= -999;b<1000; b++){
				int sequence = 0;
				for(int n = 0; ; n++){
					if(isPrime(n*n+a*n+b)){
						sequence++;
					}else{
						if(sequence > maxLength){
							maxLength = sequence;
							bestA = a;
							bestB = b;
							System.out.println("a: "+a+", b: "+b+" ("+sequence+")");
						}
						break;
					}
				}
			}
		}
		System.out.println(bestA*bestB);
	}

	public static boolean isPrime(int x){
		int limit = ((int) Math.sqrt(x))+1;
		
		if(x < 2){
			return false;
		}
		
		if(x == 2){
			return true;
		}
		
		for(int i = 2; i<= limit;i++){
			if(x % i == 0){
				return false;
			}
		}
		return true;
	}
}
