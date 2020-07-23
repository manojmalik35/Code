package mar4;

public class qpermutewithBitmask {

	public static void qpermute(int boxbm,int cq,int tq,int tb,String asf){
		if(cq>tq){
			System.out.println(asf);
			return;
		}
		
		for(int b=0;b<tb;b++){
			if((boxbm&(1<<b))==0){
				boxbm=boxbm^(1<<b);
				qpermute(boxbm,cq+1,tq,tb,asf+"q"+cq+"b"+b+"+");
				boxbm=boxbm^(1<<b);
			}
		}
			
	}
	public static void main(String[] args) {
		qpermute(0,1, 2, 4,"");

	}

}
