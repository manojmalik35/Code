#include<iostream>
#include<vector>
#include<climits>

using namespace std;

class Person
{
    public:
        int nod;//no of drinks liked
        vector<int> drinks;

        Person()
        {
        }

        Person(int nod, vector<int>& drinks)
        {
            this->nod = nod;
            this->drinks = drinks;
        }

        void display()
        {
            cout << nod << " ";
            for(auto itr : drinks)
                cout << itr << " ";
            cout << endl;
        }
};

int main(int argc, char** argv)
{
    int n;//no. of types of drinks
    cin >> n;
    int c;//no. of customers
    cin >> c;
    vector<Person> persons;
    for(int i = 0; i < c; i++)
    {
        int nod;
        cin >> nod;
        vector<int> drinks(nod);
        for(int j = 0; j < nod; j++){
            cin >> drinks[j];
        }
        persons.push_back(Person(nod, drinks));
    }

    int mdi = 0;
    for(int i = 1; i < persons.size(); i++)
    {
        if(persons[i].nod < persons[i - 1].nod)
            mdi = i;
    }

    int minc = 0;
    for(int i = 0; i < persons[mdi].drinks.size(); i++)
    {
        int count = 1;
        int d = persons[mdi].drinks[i];
        for(int j = 0; j < persons.size(); j++)
        {
            if(j != mdi)
            {
                for(int k = 0; k < persons[j].drinks.size(); k++)
                {
                    if(d == persons[j].drinks[k])
                    {
                        break;
                    }
                }
            }   
        }
    }

    
}