#include<iostream>

using namespace std;

int main(int argc,char** argv)
{
    int n=25; // no. of bulbs and persons
    // 1-> off~on or on~off
    // 2-> toggle 2,4,6,8,......
    // 3-> toggle 3,6,9,12,........
    // 4-> toggle 4,8,12,16,.....
    // After the turn of n persons, how many bulbs are left on???
    for(int i=1;i*i<=n;i++)
    {
        cout << i*i << endl;
    }
}