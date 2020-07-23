package mar3;

public class floodfill {

	public static void ff(int[][] arr,int cr,int cc,String psf){
		if(cr==arr.length-1 && cc==arr[0].length-1){
			System.out.println(psf);
			return;
		}
		
		if(cr<0 || cc<0 || cr>arr.length-1 || cc>arr[0].length-1 || arr[cr][cc]==1 || arr[cr][cc]==2)
			return;
		arr[cr][cc]=2;//visited=true
		ff(arr,cr+1,cc,psf+"D");
		ff(arr,cr,cc-1,psf+"L");
		ff(arr,cr-1,cc,psf+"T");
		ff(arr,cr,cc+1,psf+"R");
		arr[cr][cc]=0;//visited=false
	}
	public static void main(String[] args) {
		int[][] maze={{0,1,0,0,0,0},
					  {0,1,0,1,1,0},
					  {0,0,0,0,0,0},
					  {0,1,0,1,1,0},
					  {0,1,0,0,0,0}};
		ff(maze,0,0,"");

	}

}
