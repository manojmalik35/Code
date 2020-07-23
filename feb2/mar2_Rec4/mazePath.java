package mar2;
import java.util.*;
public class mazePath {

	public static ArrayList<String> gmp1(int cr,int cc,int dr,int dc){ //can move only horizontally and vertically
		if(cr==dr && cc==dr){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		if(cr>dr || cc>dc){
			ArrayList<String> bres=new ArrayList<>();
			return bres;
		}
		ArrayList<String> mres=new ArrayList<>();
		ArrayList<String> rres1=gmp1(cr,cc+1,dr,dc);
		ArrayList<String> rres2=gmp1(cr+1,cc,dr,dc);
		for(int i=0;i<rres1.size();i++)
			mres.add("H"+rres1.get(i));
		for(int i=0;i<rres2.size();i++)
			mres.add("V"+rres2.get(i));
		return mres;
	}
	public static ArrayList<String> gmp2(int cr,int cc,int dr,int dc){ //can move only horizontally and vertically and diagonally
		if(cr==dr && cc==dr){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		ArrayList<String> mres=new ArrayList<>();
		ArrayList<String> rres1=new ArrayList<>();
		ArrayList<String> rres2=new ArrayList<>();
		ArrayList<String> rres3=new ArrayList<>();
		for(int i=1;cc+i<=dc;i++){
			 rres1=gmp2(cr,cc+i,dr,dc);
			 for(int j=0;j<rres1.size();j++)
				 mres.add("H"+i+rres1.get(j));
		}
		for(int i=1;cr+i<=dr;i++){
			rres2=gmp2(cr+i,cc,dr,dc);
			for(int j=0;j<rres2.size();j++)
				mres.add("V"+i+rres2.get(j));
		}
		for(int i=1; cr+i<=dr && cc+i<=dc;i++){
			rres3=gmp2(cr+i,cc+i,dr,dc);
			for(int j=0;j<rres3.size();j++)
				mres.add("D"+i+rres3.get(j));
		}
		return mres;
	}
	
	public static void pmp1(int cr,int cc,int dr,int dc,String psf){ //can move only horizontally and vertically
		if(cr==dr && cc==dc){
			System.out.println(psf);
			return;
		}
		
		if(cr>dr || cc>dc)
			return;
		pmp1(cr,cc+1,dr,dc,psf+"H");
		pmp1(cr+1,cc,dr,dc,psf+"V");
	}
	public static void pmp2(int cr,int cc,int dr,int dc,String psf){ //can move only horizontally and vertically and diagonally
		if(cr==dr && cc==dc){
			System.out.print(psf+" ");
			return;
		}
		if(cr>dr || cc>dc)
			return;
		
		for(int i=1;cc+ i<=dc;i++)
			pmp2(cr,cc+i,dr,dc,psf+"H"+i);
		for(int i=1;cr + i<=dr;i++)
			pmp2(cr+i,cc,dr,dc,psf+"V"+i);
		for(int i=1;cr+i<=dr && cc+i <=dc;i++)
			pmp2(cr+i,cc+i,dr,dc,psf+"D"+i);
	}
	public static void main(String[] args) {
//		pmp1(0,0,2,2,"");
//		System.out.println(gmp1(0,0,2,2));
		pmp2(0,0,2,2,"");
		System.out.println();
		System.out.println(gmp2(0,0,2,2));

	}

}
