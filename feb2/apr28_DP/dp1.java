package apr28;

public class dp1 {

	public static int fibo(int n) {
		if (n == 0 || n == 1)
			return n;
		int fnm1 = fibo(n - 1);
		int fnm2 = fibo(n - 2);
		int fn = fnm1 + fnm2;
		return fn;
	}

	// Technique -> Memorization
	public static int fib_mem(int n, int[] qb) {// question bank
		if (n == 0 || n == 1)
			return n;
		else if (qb[n] != 0)
			return qb[n];
		int fnm1 = fib_mem(n - 1, qb);
		int fnm2 = fib_mem(n - 2, qb);
		int fn = fnm1 + fnm2;
		qb[n] = fn;
		return fn;

	}

	public static int climbStairs(int n) {
		if (n == 0) {
			return 1;
		}
		if (n < 0)
			return 0;

		int nm1 = climbStairs(n - 1);
		int nm2 = climbStairs(n - 2);
		int nm3 = climbStairs(n - 3);
		int na = nm1 + nm2 + nm3;
		return na;
	}

	public static int climbStairs_mem(int n, int[] qb) {
		if (n == 0)
			return 1;
		if (n < 0)
			return 0;
		if (qb[n] != 0)
			return qb[n];

		int nm1 = climbStairs_mem(n - 1, qb);
		int nm2 = climbStairs_mem(n - 2, qb);
		int nm3 = climbStairs_mem(n - 3, qb);
		int na = nm1 + nm2 + nm3;
		qb[n] = na;
		return na;
	}

	public static int fib_tab(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		for (int x = 2; x <= n; x++)
			f[x] = f[x - 1] + f[x - 2];
		return f[n];
	}

	public static int climbStairs_tab(int n) {
		int[] c = new int[n + 1];
		c[0] = 1;
		for (int x = 1; x <= n; x++) {
			c[x] += c[x - 1];
			c[x] += x >= 2 ? c[x - 2] : 0;
			c[x] += x >= 3 ? c[x - 3] : 0;
		}
		return c[n];
	}

	public static int findallpathsinmatrix(int r, int c) {// source style
		// one cell means vha se last tk jaane k kitne tarike h
		int[][] storage = new int[r + 1][c + 1];
		for (int i = r; i >= 0; i--) {
			for (int j = c; j >= 0; j--) {
				if (i == r && j == c)// last cell
					storage[i][j] = 1;
				else if (i == r)
					storage[i][j] = storage[i][j + 1];
				else if (j == c)
					storage[i][j] = storage[i + 1][j];
				else
					storage[i][j] = storage[i][j + 1] + storage[i + 1][j];
			}
		}
		return storage[0][0];
	}

	public static int findallpathsinmatrix2(int r, int c) {// destination style
		// one cell means ki vha tk kitne rasto se pocha ja skta h
		int[][] storage = new int[r + 1][c + 1];
		for (int i = 0; i <= r; i++) {
			for (int j = 0; j <= c; j++) {
				if (i == 0 && j == 0)// first cell
					storage[i][j] = 1;
				else if (i == 0)
					storage[i][j] = storage[i][j - 1];
				else if (j == 0)
					storage[i][j] = storage[i - 1][j];
				else
					storage[i][j] = storage[i][j - 1] + storage[i - 1][j];
			}
		}
		return storage[r][c];
	}

	private static void printminimumpaths(int cr, int cc, int[][] strg, String psf) {
		if (cr == strg.length - 1 && cc == strg[0].length - 1)
			System.out.println(psf);
		else if (cr == strg.length - 1)
			printminimumpaths(cr, cc + 1, strg, psf + "H");
		else if (cc == strg[0].length - 1)
			printminimumpaths(cr + 1, cc, strg, psf + "V");
		else {
			if (strg[cr][cc + 1] < strg[cr + 1][cc])
				printminimumpaths(cr, cc + 1, strg, psf + "H");
			else if (strg[cr + 1][cc] < strg[cr][cc + 1])
				printminimumpaths(cr + 1, cc, strg, psf + "V");
			else {
				printminimumpaths(cr, cc + 1, strg, psf + "H");
				printminimumpaths(cr + 1, cc, strg, psf + "V");
			}
		}
	}

	public static void minpathCost(int[][] cost) {
		int r = cost.length - 1, c = cost[0].length - 1;
		int[][] strg = new int[r + 1][c + 1];
		for (int i = r; i >= 0; i--) {
			for (int j = c; j >= 0; j--) {
				if (i == r && j == c)// last cell
					strg[i][j] = cost[i][j];
				else if (i == r)
					strg[i][j] = cost[i][j] + strg[i][j + 1];
				else if (j == c)
					strg[i][j] = cost[i][j] + strg[i + 1][j];
				else
					strg[i][j] = cost[i][j] + Math.min(strg[i][j + 1], strg[i + 1][j]);
			}
		}
		System.out.println(strg[0][0]);
		printminimumpaths(0, 0, strg, "");
	}

	private static void printMinepaths(int cr, int cc, int[][] gold, String psf) {
		if (cc == gold[0].length - 1)
			System.out.println(psf);
		else if (cr == 0) {
			int max = Math.max(gold[cr][cc + 1], gold[cr + 1][cc + 1]);
			if (max == gold[cr][cc + 1])
				printMinepaths(cr, cc + 1, gold, psf + "R");
			if (max == gold[cr + 1][cc + 1])
				printMinepaths(cr + 1, cc + 1, gold, psf + "D");
		} else if (cr == gold.length - 1) {
			int max = Math.max(gold[cr][cc + 1], gold[cr - 1][cc + 1]);
			if (max == gold[cr][cc + 1])
				printMinepaths(cr, cc + 1, gold, psf + "R");
			if (max == gold[cr - 1][cc + 1])
				printMinepaths(cr - 1, cc + 1, gold, psf + "U");
		} else {
			int max = Math.max(gold[cr][cc + 1], Math.max(gold[cr + 1][cc + 1], gold[cr - 1][cc + 1]));
			if (max == gold[cr][cc + 1])
				printMinepaths(cr, cc + 1, gold, psf + "R");
			if (max == gold[cr - 1][cc + 1])
				printMinepaths(cr - 1, cc + 1, gold, psf + "U");
			;
			if (max == gold[cr + 1][cc + 1])
				printMinepaths(cr + 1, cc + 1, gold, psf + "D");
		}
	}

	public static void maxgoldDigger(int[][] mine) {
		int r = mine.length, c = mine[0].length;
		int[][] gold = new int[r][c];
		for (int j = c - 1; j >= 0; j--) {
			for (int i = 0; i <= r - 1; i++) {
				if (j == c - 1)
					gold[i][j] = mine[i][j];
				else if (i == 0)
					gold[i][j] = mine[i][j] + Math.max(gold[i][j + 1], gold[i + 1][j + 1]);
				else if (i == r - 1)
					gold[i][j] = mine[i][j] + Math.max(gold[i][j + 1], gold[i - 1][j + 1]);
				else
					gold[i][j] = mine[i][j]
							+ Math.max(gold[i][j + 1], Math.max(gold[i + 1][j + 1], gold[i - 1][j + 1]));
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < r; i++) {
			if (gold[i][0] > max)
				max = gold[i][0];
		}
		System.out.println(max);
		for (int i = 0; i < r; i++) {
			if (gold[i][0] == max)
				printMinepaths(i, 0, gold, i + "");
		}
	}
	private static void printminjumpPaths(int[] global,int i,int[] local,String psf){
		//Do yourself
	}
	public static void minJumps(int[] local){
		int[] global=new int[local.length];
		global[local.length-1]=0;
		for(int i=local.length-2;i>=0;i--){
			int mins=Integer.MAX_VALUE;
			for(int j=1;j<=local[i] && i+j<local.length;j++){
				int stepsf=global[i+j];
				if(stepsf!=Integer.MAX_VALUE){
					if(stepsf<mins)
						mins=stepsf;
				}
			}
			if(mins!=Integer.MAX_VALUE)
				global[i]=mins+1;
			else
				global[i]=Integer.MAX_VALUE;
		}
		System.out.println(global[0]);
		printminjumpPaths(global,0, local, "");
	}
	public static void main(String[] args) {
		// int n=7;
		// System.out.println(fibo(n));
		// int[] qb=new int[n+1];
		// System.out.println(fib_mem(n, qb));
		// System.out.println(climbStairs(n));
		// System.out.println(fib_tab(n));
		// System.out.println(climbStairs_mem(n, qb));
		// System.out.println(climbStairs_tab(n));
		// System.out.println(findallpathsinmatrix(2,3));
		// System.out.println(findallpathsinmatrix2(2, 3));
		// int[][] cost={{2,1,3,0,4,3},
		// {1,0,1,2,7,1},
		// {0,1,0,4,1,9},
		// {1,2,1,1,4,2},
		// {7,0,3,9,5,8}};
		// minpathCost(cost);
//		int[][] mine = { { 1, 7, 3, 7, 5, 3 }, { 3, 6, 2, 0, 8, 2 }, { 2, 1, 5, 1, 3, 1 }, { 0, 4, 3, 2, 0, 4 },
//				{ 4, 1, 4, 6, 1, 0 }, { 5, 4, 0, 7, 9, 6 } };
//		maxgoldDigger(mine);
		int[] arr={3,4,0,1,3,1,0,3,0,1,1};
		minJumps(arr);
	}

}
