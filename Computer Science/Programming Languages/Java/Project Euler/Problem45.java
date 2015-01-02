package de.itter.euler;

public class Problem45 {

	public static void main(String[] args) {
		for(long n=1;;n++){
			long hex = hexagonalNumber(n);
			for(long i=1;;i++){
				long penta = pentagonalNumber(i);
				if(penta > hex){
					break;
				}
				if(penta == hex){
					for(int j=1;;j++){
						long tri = triangleNumber(j);
						if(tri > hex){
							break;
						}
						if(tri == hex){
							System.out.println("t("+j+")="+tri);
							if(tri > 40755){
								return;
							}
						}
					}
				}
			}
		}
	}
	
	public static long hexagonalNumber(long n){
		return n*(2*n -1);
	}
	
	public static long pentagonalNumber(long n){
		return n*(3*n -1)/2;
	}
	
	public static long triangleNumber(long n){
		return n*(n+1)/2;
	}

}
