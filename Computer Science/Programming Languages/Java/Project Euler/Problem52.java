package de.itter.euler;

public class Problem52 {

	public static void main(String[] args) {
		for(int i=1;;i++){
			if(isPermutation(i, i*6))
				if(isPermutation(i, i*5))
					if(isPermutation(i, i*4))
						if(isPermutation(i, i*3))
							if(isPermutation(i, i*2)){
								System.out.println(i);
								return;
							}
		}
	}

	private static boolean isPermutation(int i, int j) {
		
		int magI = 1;
		for(int ii=i;ii>0;ii/=10){
			magI++;
		}
		int magJ = 1;
		for(int ii=j;ii>0;ii/=10){
			magJ++;
		}
		
		if(magI != magJ){
			return false;
		}
		
		int[] iv = new int[magI];
		int[] jv = new int[magJ];
		
		for(int k = 0; k<magI;k++){
			iv[iv.length-1-k] = i % 10;
			i /= 10;
		}
				
		for(int k = 0; k<magJ;k++){
			jv[jv.length-1-k] = j % 10;
			j /= 10;
		}
		return isPermutation(iv, jv);
	}

	private static boolean isPermutation(int[] iv, int[] jv) {
		if(iv.length != jv.length){
			return false;
		}
		
		for(int i=0;i<iv.length;i++){
			for(int j=0;j<iv.length;j++){
				if(iv[i] == jv[j]){
					iv[i] = -1;
					jv[j] = -1;
					break;
				}
			}
			if(iv[i] != -1){
				return false;
			}
		}
		
		return true;
	}
	

}
