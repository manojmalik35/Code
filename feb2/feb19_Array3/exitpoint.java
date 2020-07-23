package feb19;

public class exitpoint {

	public static void main(String[] args) {
		int[][] mat = { { 0, 0, 1, 0 },
					  { 1, 0, 0, 0 },
					  { 0, 0, 0, 0 },
					  { 1, 0, 1, 0 } };
		int rmax = mat.length;
		int cmax = mat[0].length;
		int r = 0, c = 0, d = 0;
		while (r >= 0 && r < rmax && c >= 0 && c < cmax) {
			d = (d + mat[r][c]) % 4;
			
			if(d==0)
				c++;
			else if(d==1)
				r++;
			else if(d==2)
				c--;
			else 
				r--;
		}
		if(d==0)
			c--;
		else if(d==1)
			r--;
		else if(d==2)
			c++;
		else 
			r++;
		
		
		System.out.println(r + " " + c);

	}

}
