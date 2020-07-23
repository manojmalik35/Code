#include<iostream>
#include<climits>
using namespace std;

int getLog(int n)//ceil of log
{
    int x=0;
    while((1<<x)<=n)
        x++;
    return x;
}

class stree
{
    private:
        int* oa;
        int oas;
        int* sa;
        int sas;
        int* la;
    public:
        stree(int* oa,int oas)
        {
            this->oa=oa;
            this->oas=oas;
            int h=getLog(oas)+1;
            sas=(1<<h)-1;
            sa=new int[sas];
            la=new int[sas];
            build(0,oas-1,0);
        }
        void build(int ss,int se,int si)
        {
            if(ss==se)
            {
                sa[si]=oa[ss];
                la[si]=0;
                return;
            }
            int mid=(ss+se)/2;
            build(ss,mid,2*si+1);//left call
            build(mid+1,se,2*si+2);//right call

            sa[si] = sa[2*si+1] + sa[2*si+2];
            la[si]=0;
        }

        void update(int qs,int qe,int delta,int ss,int se,int si)
        {
            handlependingwork(ss,se,si);
            if(ss>=qs && se<=qe)
            {
                la[si] += delta;
                handlependingwork(ss,se,si);
            }
            else if(qe<ss || qs>se)
                return;
            else
            {
                int mid=(ss+se)/2;
                update(qs,qe,delta,ss,mid,2*si+1);   
                update(qs,qe,delta,mid+1,se,2*si+2);
                
                sa[si] = sa[2*si+1] + sa[2*si+2];
            }
        }

        int query(int qs,int qe,int ss,int se,int si)
        {
            handlependingwork(ss,se,si);
            if(ss>=qs && se<=qe)
                return sa[si];
            else if(qe<ss || qs>se)
                return 0;
            else
            {
                int mid=(ss+se)/2;
                int lc=query(qs,qe,ss,mid,2*si+1);   
                int rc=query(qs,qe,mid+1,se,2*si+2);
                return lc+rc;   
            }
        }
        void handlependingwork(int ss,int se,int si)
        {
            sa[si] += la[si] * (se-ss+1);
            if(ss!=se)
            {
                la[2*si+1] += la[si];
                la[2*si+2] += la[si];
            }
            la[si] = 0;
        }
};
int main(int argc,char** argv)
{
    int* oa=new int[8]{1,2,3,4,5,6,7,8};
    stree st(oa,8);
    cout << st.query(2,5,0,7,0) << endl;
    st.update(3,7,10,0,7,0);
    cout << st.query(2,5,0,7,0) << endl;
}