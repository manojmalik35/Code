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

class IOperator
{
    public:
        virtual int operation(int lhs,int rhs) = 0;
        virtual int identity() = 0;
};

class SumOperator : public IOperator
{
    public:
        int operation(int lhs,int rhs)
        {
            return lhs+rhs;
        }
        int identity()
        {
            return 0;
        }
};

class ProductOperator : public IOperator
{
    public:
        int operation(int lhs,int rhs)
        {
            return lhs*rhs;
        }
        int identity()
        {
            return 1;
        }
};

class MaxOperator : public IOperator
{
    public:
        int operation(int lhs,int rhs)
        {
            return max(lhs,rhs);
        }
        int identity()
        {
            return INT_MIN;
        }
};

class MinOperator : public IOperator
{
    public:
        int operation(int lhs,int rhs)
        {
            return min(lhs,rhs);
        }
        int identity()
        {
            return INT_MAX;
        }
};

class GCDOperator : public IOperator
{
    public:
        int operation(int divs,int divd)
        {
            if(divs==0)
                return divd;
            return operation(divd%divs,divs);
        }
        int identity()
        {
            return 0;
        }
};

class stree
{
    private:
        int* oa;
        int oas;
        int* sa;
        int sas;
        IOperator* opr = NULL;
    public:
        void build(int ss,int se,int si)
        {
            if(ss==se)
            {
                sa[si]=oa[ss];
                return;
            }
            int mid=(ss+se)/2;
            build(ss,mid,2*si+1);//left call
            build(mid+1,se,2*si+2);//right call

            sa[si] = opr->operation(sa[2*si+1] , sa[2*si+2]);
        }
        stree(int* oa,int oas,IOperator* opr)
        {
            this->oa=oa;
            this->oas=oas;
            this->opr=opr;
            int h=getLog(oas)+1;
            sas=(1<<h)-1;
            sa=new int[sas];
            build(0,oas-1,0);
        }
        void update(int idx,int nv,int ss,int se,int si)
        {
            if(ss==se)
            {
                sa[si] = nv;
                return;
            }
            int mid=(ss+se)/2;
            if(idx<=mid)
            {
                update(idx,nv,ss,mid,2*si+1);
            }
            else
            {
                update(idx,nv,mid+1,se,2*si+2);
            }

            sa[si] = opr->operation(sa[2*si+1] , sa[2*si+2]);
        }

        int query(int qs,int qe,int ss,int se,int si)
        {
            if(ss>=qs && se<=qe)
                return sa[si];
            else if(qe<ss || qs>se)
                return opr->identity();
            else
            {
                int mid=(ss+se)/2;
                int lc=query(qs,qe,ss,mid,2*si+1);   
                int rc=query(qs,qe,mid+1,se,2*si+2);
                return opr->operation(lc,rc);   
            }
        }
};
int main(int argc,char** argv)
{
    int* oa=new int[8]{2,5,-3,6,7,14,1,9};
    stree* st=NULL;
    IOperator* opr=NULL;
    int choice;
    cin >> choice;
    if(choice==1)
        opr=new SumOperator();
    else if(choice==2)
        opr=new ProductOperator();
    else if(choice==3)
        opr=new MaxOperator();
    else if(choice==4)
        opr=new MinOperator();
    else
        opr=new GCDOperator();
    st=new stree(oa,8,opr);
    cout << st->query(2,5,0,7,0) << endl;
    cout << st->query(5,7,0,7,0) << endl;
    st->update(4,10,0,7,0);
    cout << st->query(2,5,0,7,0) << endl;
    cout << st->query(5,7,0,7,0) << endl;
    cout << st->query(4,6,0,7,0) << endl;
}