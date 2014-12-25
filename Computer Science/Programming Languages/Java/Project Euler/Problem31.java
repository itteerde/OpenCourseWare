package de.itter.euler;

public class Problem31 {

	public static void main(String[] args) {
		int count = 0;
		int tries = 0;
		
		for(int p200=1;p200>=0;p200--){
			for(int p100=2;p100>=0;p100--){
				for(int p50=4;p50>=0;p50--){
					for(int p20=10;p20>=0;p20--){
						if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 > 200){
							continue;
						}
						for(int p10=20;p10>=0;p10--){
							if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 + p10 * 10 > 200){
								continue;
							}
							for(int p5=40;p5>=0;p5--){
								if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 + p10 * 10 + p5 * 5 > 200){
									continue;
								}
								for(int p2=100;p2>=0;p2--){
									if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 + p10 * 10 + p5 * 5 + p2 * 2 > 200){
										continue;
									}
									for(int p1=200;p1>=0;p1--){
										if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 + p10 * 10 + p5 * 5 + p2 * 2 + p1 > 200){
											continue;
										}
										tries++;
										if(p200 * 200 + p100 * 100 + p50 * 50 + p20 * 20 + p10 * 10 + p5 * 5 + p2 * 2 + p1 == 200){
											count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(count+ " ("+tries+")");
	}
	

}
