package de.itter.euler;

public class Problem44 {

	public static void main(String[] args) {
		int d = Integer.MAX_VALUE;
		long tick = System.currentTimeMillis();
		int jstart = 1;
		
		for(int limit = 100; ; limit*=2){
			for(int j=jstart;j<limit;j++){
				jstart = j;
				if(pentagonNumber(j+1)-pentagonNumber(j) > d){
					System.out.println(d);
					return;
				}

				for(int k=j+1;k<j+1+limit;k++){
					if(System.currentTimeMillis()-tick > 10000){
						tick = System.currentTimeMillis();
						System.out.println(j+", "+k);
					}
					
					if(isPentagonNumber(pentagonNumber(j)+pentagonNumber(k))){
						if(isPentagonNumber(pentagonNumber(k)-pentagonNumber(j))){
							if(pentagonNumber(k)-pentagonNumber(j) < d){
								d = pentagonNumber(k)-pentagonNumber(j);
								System.out.println(d+"= p("+k+")-p("+j+") = "+pentagonNumber(k)+" - "+pentagonNumber(j));
							}
						}
					}
				}
			}
		}
	}
	
	public static boolean isPentagonNumber(int x){
		for(int i=1;;i++){
			if(pentagonNumber(i)>x){
				return false;
			}
			
			if(pentagonNumber(i)==x){
				return true;
			}
		}
	}
	
	public static int pentagonNumber(int n){
		return (n*(3*n -1))/2;
	}

}
