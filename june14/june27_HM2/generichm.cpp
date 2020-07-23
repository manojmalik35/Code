#include<iostream>
#include<list>
#include<string>
using namespace std;

template<class k,class v>
class hm
{
    private:
        class hmnode
        {
            public:
                k key;
                v value;
                hmnode(k key,v value)
                {
                    this->key=key;
                    this->value=value;
                }
        };
        int n;
        int N;
        list<hmnode>* buckets=NULL;

        int hashFn(k key)
        {
            int hc=hash<k>()(key);
            hc=abs(hc);
            return hc%N;
        }

        typename list<hmnode>::iterator getDataIndex(int bi,k key)
        {
            for(auto itr=buckets[bi].begin();itr!=buckets[bi].end();itr++)
            {
                if(itr->key==key)
                    return itr;
            }
            return buckets[bi].end();
        }
        void rehash()
        {
            int oldN=N;
            list<hmnode>* oba=buckets;
            N=N*2;
            buckets=new list<hmnode>[N];
            n=0;

            for(int bi=0;bi<oldN;bi++)
            {
                for(auto ditr=oba[bi].begin();ditr!=oba[bi].end();ditr++)
                    (*this)[ditr->key] = ditr->value;
            }
        }
    public:
        hm()
        {
            n=0;
            N=4;
            buckets=new list<hmnode>[N];
        }

        int count(k key)
        {
            int bi=hashFn(key);
            auto ditr=getDataIndex(bi,key);
            if(ditr==buckets[bi].end())
                return 0;
            else
                return 1;
        }

        v& operator[](const k& key)
        {
            int bi=hashFn(key);
            auto ditr=getDataIndex(bi,key);
            if(ditr==buckets[bi].end())
            {
                hmnode node(key,v());
                buckets[bi].push_back(node);
                n++;

                double lambda=n*1.0/N;
                if(lambda>2.0)
                {
                    rehash();
                    bi=hashFn(key);
                    ditr=getDataIndex(bi,key);
                }
                else
                {
                    return buckets[bi].back().value;
                }    
            }
            return ditr->value;
        }
        void erase(k key)
        {
            int bi=hashFn(key);
            auto ditr=getDataIndex(bi,key);
            if(ditr!=buckets[bi].end())
            {
                buckets[bi].erase(ditr);
                n--;
            }
        }
        int size()
        {
            return n;
        }
        void display()
        {
            cout << "-------------------------" << endl;
            for(int bi=0;bi<N;bi++)
            {
                cout<< bi << " -> " ;
                for(auto ditr=buckets[bi].begin();ditr!=buckets[bi].end();ditr++)
                {
                    cout<< "[" << ditr->key << "=" << ditr->value << "]";
                }
                cout<< endl;
            }
            cout << "-------------------------" << endl;
        }
};  

class point
{
    public:
        int x;
        int y;
        point()
        {
        }
        point(int x,int y)
        {
            this->x=x;
            this->y=y;
        }

        bool operator==(const point& p)const
        {
            return this->x==p.x && this->y==p.y;
        }

        friend ostream& operator<<(ostream& out,const point& p)
        {
            out<< p.x << " " << p.y;
            return out;
        }
};
namespace std
{
    template<>
    struct hash<point>
    {
        public:
            int operator()(const point& p)
            {
                return p.x+p.y;
            }
    };
}
int main(int argc,char** argv)
{
    // hm<string,int> map;
    // map["India"]=100;
    // map["Pak"]=80;
    // cout<< map.count("India") << " " << map.count("Pak") <<endl;
    // cout<< map["India"] << " " << map["Pak"] << endl;
    // cout<< map.count("Sus") << endl;
    // cout<< map["Sus"] << endl;
    // cout<< map.count("Sus") << endl;

    // map.erase("Sus");
    // cout<< map.count("Sus") << endl;
    // map["India"]=135;
    // map["Norway"]=10;
    // map["China"]=200;
    // map["US"]=123;
    // map["UK"]=150;
    // map["Bangladesh"]=12;
    // map["California"]=120;
    // map["Afghanistan"]=1;
    // map.display();

    hm<point,string> map;
    point p1(0, 2);
    point p2(3, 4);
    point p3(2, 2);
    point p4(4, 7);
    point p5(2, 1);
    point p6(2, 1);

    map[p1]= "Delhi";
    map[p2]="Chandigarh";
    map[p3]="Mumbai";
    map[p4]="Shimla";
    map[p5]="Murthal";

    cout << p6 << endl;

    // cout << "abc" << 123 << "abc" << endl;
    // cout.operator<<("abc").operator<<(123).operator<<("abc");
    // above two lines have same functionality
    map.display();

}