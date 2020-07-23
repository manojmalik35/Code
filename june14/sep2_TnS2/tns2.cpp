#include<iostream>
#include<vector>
#include<bitset>

using namespace std;

//1.x^n + 2.x^n-1 + 3.x^n-2 + .......... + n.x
int polynomial(int x, int n)
{
    int coeff = n;
    int sum = 0;
    int px = x;
    while(coeff >= 1)
    {
        sum += coeff * px;
        px = px * x;
        coeff--;
    }

    return sum;
}

void soe(int n)//primes till n, complexity => nlog(logn)
{
    bool arr[n + 1];
    arr[0] = arr[1] = false;//not prime
    for(int i = 2; i < n + 1; i++)
        arr[i] = true;//prime

    for(int i = 2; i * i <= n; i++)
    {
        if(arr[i] == false)
            continue;

        for(int j = i; i * j < n + 1; j++)
                arr[i * j] = false;
    }

    for(int i = 2; i < n + 1; i++)
    {
        if(arr[i])
            cout << i << " ";
    }
    cout << endl;

}

int* getspf(int n)//smallest prime factor
{
    int* res = new int[n + 1];
    bool arr[n + 1];
    for(int i = 0; i <= n; i++)
    {
        arr[i] = true;//prime
        res[i] = i;
    }
    arr[0] = arr[1] = false;//not prime

    for(int i = 2; i * i <= n; i++)
    {
        if(arr[i] == false)
            continue;

        for(int j = i; i * j <= n; j++)
        {
            if(arr[i * j])
            {
                arr[i * j] = false;
                res[i * j] = i;
            }
        }
    }
    return res;
}

void factorization(int* spf, int n)
{
    cout << n << " => ";
    while(n > 1)
    {
        cout << spf[n] << " * ";
        n = n / spf[n];
    }
    cout << "1" << endl;
}

void setaBit(int n, int k)
{
    bitset<10> old(n);
    cout << n << " = " << old << endl;

    int bm = 1 << k;
    n = n | bm;
    
    bitset<10> ne(n);
    cout << n << " = " << ne << endl;
}

void unsetaBit(int n, int k)
{
    bitset<10> old(n);
    cout << n << " = " << old << endl;

    int bm = 1 << k;
    n = n & ~bm;
    
    bitset<10> ne(n);
    cout << n << " = " << ne << endl;
}

void toggleaBit(int n, int k)
{
    bitset<10> old(n);
    cout << n << " = " << old << endl;

    int bm = 1 << k;
    n = n ^ bm;
    
    bitset<10> ne(n);
    cout << n << " = " << ne << endl;
}

int main(int argc, char** argv)
{
    // cout << polynomial(2, 3) << endl;
    // soe(100);
    // int* spf = getspf(1000);
    // factorization(spf, 1000);
    // factorization(spf, 360);
    // factorization(spf, 971);
    // factorization(spf, 325);
    // factorization(spf, 548);
    // setaBit(57, 2);
    unsetaBit(57, 4);
    // toggleaBit(57, 3);

}