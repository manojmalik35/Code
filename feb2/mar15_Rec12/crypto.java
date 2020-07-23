package mar15;

public class crypto {

	static String s1="send";
	static String s2="more";
	static String s3="money";
	
	public static int gnas(String s,int[] map){
		int p=1;
		int as=0;
		for(int i=s.length()-1;i>=0;i--){
			as += map[s.charAt(i)-'a'] *p;
			p=p*10;
		}
		return as;
	}
	static int counter=0;
	public static void crypto(String qs,int[] amap,boolean[] dused){
		if(qs.length()==0){
			int nfs1=gnas(s1,amap);
			int nfs2=gnas(s2,amap);
			int nfs3=gnas(s3,amap);
			if(nfs1+nfs2==nfs3){
				counter++;
				System.out.println("************"+counter+"************");
				System.out.println(nfs1);
				System.out.println("+"+nfs2);
				System.out.println("="+nfs3);
				System.out.println("************"+counter+"************");
			}
			return;
		}
		
		char c=qs.charAt(0);
		String rest=qs.substring(1);
		for(int d=0;d<10;d++){
			if(dused[d]==false){
				dused[d]=true;
				amap[c-'a']=d;
				crypto(rest,amap,dused);
				amap[c-'a']=-1;
				dused[d]=false;
			}
		}
	}
	public static void main(String[] args) {
		boolean[] cused=new boolean[26];
		String qs="";
		for(int i=0;i<s1.length();i++){
			char c=s1.charAt(i);
			if(cused[c-'a']==false){
				cused[c-'a']=true;
				qs=qs+c;
			}
		}
		for(int i=0;i<s2.length();i++){
			char c=s2.charAt(i);
			if(cused[c-'a']==false){
				cused[c-'a']=true;
				qs=qs+c;
			}
		}
		for(int i=0;i<s3.length();i++){
			char c=s3.charAt(i);
			if(cused[c-'a']==false){
				cused[c-'a']=true;
				qs=qs+c;
			}
		}
		int[] am=new int[26];
		for(int i=0;i<am.length;i++)
			am[i]=-1;
		boolean[] dused=new boolean[10];
		crypto(qs,am,dused);

	}

}
