#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    int n = 21;
    int mtp = 4;//matchsticks to pick
    int i = 0;
    while(i <= mtp)
    {
        if(n == 1){
            cout << endl;
            break;
        }
        
        cout << "Enter how much matchsticks out of " << n << " you want to pick" << endl;
        int pm = 0;
        cin >> pm;
        if(pm > 4)
        {
            cout << "Invalid input. Enter in the range 1-4" << endl;
            continue;
        }

        n -= pm;
        int cp = 5 - pm;
        cout << "I pick " << cp << " matchsticks. "; 
        n -= cp;
        if(n > 1)
            cout << "Now its your turn. " << endl;
        i++;
    }    
    cout << "Now you have " << n << " matchsticks remaining." << endl;
    cout << "I win. You lose. Hahahahahaha" << endl;
}