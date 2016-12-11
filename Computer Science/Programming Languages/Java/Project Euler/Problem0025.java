import java.math.BigInteger;

public class Problem0025 {

	public static void main(String[] args) {
		
		int n = 3;
		BigInteger[] memory = new BigInteger[3];
		memory[1]=new BigInteger("1");
		memory[2]=new BigInteger("1");
		
		for(;;n++){
			memory[0] = memory[1].add(memory[2]);
			if(memory[0].toString().length() >= 1000){
				System.out.println(n);
				return;
			}
			memory[1]=memory[2];
			memory[2]=memory[0];
		}

	}

}
