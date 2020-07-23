package mar3;

public class queenPermutation {

	public static void qpermute(boolean[] boxes,int cq,int tq,String asf){
		if(cq>tq){
			System.out.println(asf);
			return;
		}
		
		for(int cb=0;cb<boxes.length;cb++){
			if(boxes[cb]==false){
				boxes[cb]=true;
				qpermute(boxes,cq+1,tq,asf+"q"+cq+"b"+cb+"+");
				boxes[cb]=false;
			}
		}
	}
	public static void main(String[] args) {
		boolean[] boxes=new boolean[4];
		qpermute(boxes,1, 2, "");

	}

}
