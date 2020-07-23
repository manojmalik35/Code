#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    int n;
    cin >> n;
    int space = -1;
    int d = n;
    for(int i = 0; i < n; i++)
    {
        char tp = 'A';
        for(int j = 0; j < d; j++)
        {
            cout << tp;
            tp++;
        }
        for(int j = 0; j < space; j++)
            cout << " ";
        tp = i == 0 ? tp - 2 : tp - 1;
        for(int j = i == 0 ? 1 : 0; j < d; j++)
        {
            cout << tp;
            tp--;
        }

        space += 2;
        cout << endl;
        d--;
    }
}