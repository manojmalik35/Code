package apr27;

//sieve of eratosthenes
//used to find prime numbers in less complexity
public class soe {

	public static void main(String[] args) {
		// first 30 prime numbers
		int n = 49;
		boolean[] primes = new boolean[n + 1];
		for (int i = 2; i < n; i++)
			primes[i] = true;

		for (int i = 2; i * i < n; i++) {
			if (primes[i]) {
				for (int j = i; j * i < n; j++)
					primes[i * j] = false;
			}
		}
		for(int i=0;i<primes.length;i++){
			if(primes[i])
				System.out.print(i+" ");
		}
	}
}
