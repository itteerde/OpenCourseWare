package de.itter.euler;

public class Problem17 {

	public static void main(String[] args) {
		for(int i=1; i<= 999;i++){
			System.out.println(i+": "+ read(i));
		}
		
		int sum = new String("onethousand").length();
		for(int i=1;i<=999;i++){
			sum += read(i).length();
		}
		System.out.println(sum);
	}
	
	public static String read(int x){
		StringBuffer res = new StringBuffer();
		
		if(x > 99){
			readDigit(res, x/100);
			res.append("hundred");
			if(x % 100 != 0){
				res.append("and");
			}
		}
		
		if( x > 19){
			readTens(res, (x % 100) / 10);
		}
		
		int rest = x - (x/100) * 100;
		if( rest > 19){
			rest -= (rest / 10) * 10;
			readDigit(res, rest);
		}else{
			if( rest < 11){
				if( rest == 10){
					res.append("ten");
				}else{
					readDigit(res, rest);
				}
			}else{
				switch(rest){
					case 11:
						res.append("eleven");
						break;
					case 12:
						res.append("twelve");
						break;
					case 13:
						res.append("thirteen");
						break;
					case 14:
						res.append("fourteen");
						break;
					case 15:
						res.append("fifteen");
						break;
					case 16:
						res.append("sixteen");
						break;
					case 17:
						res.append("seventeen");
						break;
					case 18:
						res.append("eighteen");
						break;
					case 19:
						res.append("nineteen");
						break;
				}
			}
		}
		
		return res.toString();
	}

	private static void readTens(StringBuffer res, int i) {
		switch(i){
			case 2:
				res.append("twenty");
				break;
			case 3:
				res.append("thirty");
				break;
			case 4:
				res.append("fourty");
				break;
			case 5:
				res.append("fifty");
				break;
			case 6:
				res.append("sixty");
				break;
			case 7:
				res.append("seventy");
				break;
			case 8:
				res.append("eighty");
				break;
			case 9:
				res.append("ninety");
		}
	}

	private static void readDigit(StringBuffer res, int i) {
		switch(i){
			case 1:
				res.append("one");
				break;
			case 2:
				res.append("two");
				break;
			case 3:
				res.append("three");
				break;
			case 4:
				res.append("four");
				break;
			case 5:
				res.append("five");
				break;
			case 6:
				res.append("six");
				break;
			case 7:
				res.append("seven");
				break;
			case 8:
				res.append("eight");
				break;
			case 9:
				res.append("nine");
				break;
		}
	}

}
