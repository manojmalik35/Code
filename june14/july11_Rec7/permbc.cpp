#include<iostream>
#include<vector>
#include<string>

using namespace std;

int count=0;
void printPermbc(int bno,int tb,int co,int to,string asf,vector<bool>& qpsf)
{
    if(bno == tb)
    {
        if(co == to)
        {
            cout << asf << endl;
            count++;
        }
        return;
    }
    printPermbc(bno+1,tb,co,to,asf,qpsf);
    for(int i=0;i<qpsf.size();i++)
    {    
        if(qpsf[i] == false)
        {
        qpsf[i] = true;
        printPermbc(bno+1,tb,co+1,to,asf+"O"+to_string(i)+"B"+to_string(bno)+" ",qpsf);
        qpsf[i] = false;
        }
    }
}

int main(int argc,char** argv)
{
    vector<bool> objects(2,false);
    printPermbc(0,4,0,2,"",objects);
    cout << count << endl;
}