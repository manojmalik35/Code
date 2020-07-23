#include <iostream>

using namespace std;

void test1()
{
    int a = 10;
    int *b = &a;
    int **c = &b;

    cout << a << " " << &a << endl;
    cout << b << " " << &b << " " << *b << endl;
    cout << c << " " << &c << " " << *c << " " << **c << endl;
}

int* test2()
{
    // int* a = new int[5];
    // for(int i = 0; i < 5; i++)
    //     cin >> a[i];

    int ar[5] = {2, 3, 4, 5, 6};
    int *a = ar;
    int (*b)[5] = &ar; //b is pointer to a pointer of size 5
    cout << b << " " << endl;
    for(int i = 0; i < 5; i++,b++){
        cout << b << " ";
        cout << *b << endl;
    }
    return *b;
}

int* findTypeOfStack(){
    int a = 40;
    return &a;
}

int main()
{
    // test1();
    int *a = test2();
    cout << a << " " << *a << endl;
    // for(int i = 0; i < 10; i++,a++){
    //     cout << a << " ";
    //     cout << *a << endl;
    // }
    // cout << endl;

    // int a = 10;
    // int* b = findTypeOfStack();
    // if(b > &a)
    //     cout << "Bottom to top" << endl;
    // else
    //     cout << "Top to bottom" << endl;



    return 0;
}
