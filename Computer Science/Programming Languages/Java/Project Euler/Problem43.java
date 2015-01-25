package de.itter.euler;

public class Problem43 {

	public static void main(String[] args) {
		solve(0);
		//test();
	}
	
	public static void solve(int debuglevel){
		long sum = 0;
		for(int t17=1; (t17 * 17)<1000;t17++){
			long x890 = 17 * t17;
			for(int i=0;i<10;i++){
				long x7890 = x890 + i * 1000L;
				if((x7890/10)%13 == 0){
					for(int j=0;j<10;j++){
						long x67890 = x7890 + j * 10000L;
						if((x67890/100)%11 == 0){
							for(int k=0;k<10;k++){
								long x567890 = x67890 + k * 100000L;
								if((x567890/1000)%7 == 0){
									for(int l=0;l<10;l++){
										long x4567890 = x567890 + l * 1000000L;
										if((x4567890/10000)%5 == 0){
											for(int m=0;m<10;m++){
												long x34567890 = x4567890 + m * 10000000L;
												if((x34567890/100000)%3 == 0){
													for(int n=0;n<10;n++){
														long x234567890 = x34567890 + n * 100000000L;
														if((x234567890/1000000)%2 == 0){
															for(long o=0;o<10;o++){//leading zeros allowed?
																long candidate = x234567890 + o * 1000000000L;
																if(debuglevel > 0){
																	System.out.println(candidate);
																}
																if(isPandigital(candidate,9)){
																	sum+= candidate;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("Problem 43: "+sum);
		
	}

	public static boolean deleteDigits(int[] digits, long x){
		for(long i=1;;i*=10){
			if(i>x){
				break;
			}
			int digit = (int) ((x % (i*10)) / i);
			if( digits[digit]==-1){
				return false;
			} else{
				digits[digit] = -1;
			}
		}
		return true;
	}

	public static boolean isPandigital(long x){
		return isPandigital(x, 0);
	}
	
	public static boolean isPandigital(long x, int n){
		int[] digits = {0,1,2,3,4,5,6,7,8,9};
		
		if(!deleteDigits(digits, x)){
			return false;
		}
		
		boolean phase1 = true;
		for(int i=0;i<digits.length;i++){
			digits[0] = -1;//leading zero, does not hurt to overwrite it if there was a non-leading zero
			if(phase1){
				if(digits[i]!= -1){
					if(n>i-1){
						return false;
					}
					phase1 = false;
				}
			} else{
				if(digits[i]== -1){
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void test(){
		System.out.println(isPandigital(1406357289L,9));
		System.out.println(isPandigital(1430952867L,9));
		System.out.println(isPandigital(1460357289L,9));
		System.out.println(isPandigital(4106357289L,9));
		System.out.println(isPandigital(4130952867L,9));
		System.out.println(isPandigital(4160357289L,9));
		System.out.println(isPandigital(16695334890L,9));		
		System.out.println(isPandigital(1023459678L,9));
	}
}
