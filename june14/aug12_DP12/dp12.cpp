#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

class Product
{
    public:
        int wt;
        int price;
        double ratio;
        Product()
        {
        }

        Product(int wt, int price)
        {
            this->wt = wt;
            this->price = price;
            this->ratio = price * 1.0 / wt;
        }

        bool operator<(const Product& other)const
        {
            return this->ratio < other.ratio;
        }
};

double fractionalKnapsack(vector<int>& wts, vector<int>& prices, int cap)
{
    vector<Product> prdts (wts.size());
    for(int i = 0; i < prdts.size(); i++)
        prdts[i] = Product(wts[i], prices[i]);
    
    sort(prdts.begin(), prdts.end());
    double vib = 0;//value in bag
    int rc = cap;
    for(int i = prdts.size() - 1; i >= 0 && rc > 0; i--)
    {
        if(prdts[i].wt < rc)
        {
            rc -= prdts[i].wt;
            vib += prdts[i].price;
        }
        else
        {
            vib += rc * prdts[i].ratio;
            rc = 0;
        }
    }
    return vib;
}

int minPlatforms(vector<int>& arrivals, vector<int>& departures)
{
    sort(arrivals.begin(), arrivals.end());
    sort(departures.begin(), departures.end());
    int count = 0, maxc = 0;
    int i = 0, j = 0;
    while(i < arrivals.size())
    {
        if(arrivals[i] <= departures[j])
        {
            count++;
            i++;
        }
        else
        {
            count--;
            j++;
        }
        maxc = max(maxc, count);
    }
    return maxc;
}

class Job
{
    public:
        char name;
        int dead;
        int profit;
        Job()
        {
        }

        Job(char name, int dead, int profit)
        {
            this->name = name;
            this->dead = dead;
            this->profit = profit;
        }

        bool operator<(const Job& other)const
        {
            return this->profit < other.profit;
        }
};

int jobSequencing(string names, vector<int>& deadlines, vector<int>& profits)
{
    vector<Job> jobs (names.length());
    int noda = 0;//no of days available
    for(int i = 0; i < jobs.size(); i++){
        jobs[i] = Job(names[i], deadlines[i], profits[i]);
        noda = max(noda, deadlines[i]);
    }

    sort(jobs.begin(), jobs.end());
    vector<char> occupied (noda + 1);
    int maxp = 0;
    for(int i = jobs.size() - 1; i >= 0; i--)
    {
        int spot = jobs[i].dead;
        while(spot > 0)
        {
            if(occupied[spot] == 0)//in java default value in char array is \0
            {
                occupied[spot] = jobs[i].name;
                maxp += jobs[i].profit;  
                break;  
            }
            else
                spot--;
        }
    }
    for(int i = 1; i < occupied.size(); i++)
        cout << occupied[i] << " ";
    cout << endl;
    return maxp;
}

int maxsizeSquareof1s(vector<vector<int>>& grid)
{
    vector<vector<int>> strg (grid.size(), vector<int> (grid.size()));
    int mymax = 0;
    for(int i = strg.size() - 1; i >= 0; i--)
    {
        for(int j = strg[0].size() - 1; j >= 0; j--)
        {
            if(i == strg.size() || j == strg[0].size())
                strg[i][j] = grid[i][j];
            else if(grid[i][j] == 0)
                strg[i][j] = grid[i][j];
            else
                strg[i][j] = min(strg[i + 1][j + 1] , min(strg[i + 1][j] , strg[i][j + 1])) + 1;
            mymax = max(mymax, strg[i][j]);
        }
    }
    return mymax;
}

int find(vector<int>& pa, int idx)
{
    if(pa[idx] == idx)
        return idx;
    else
    {
        pa[idx] = find(pa, pa[idx]);//path compression
        return pa[idx]; 
    }
}

int jobSequencing2(string names, vector<int>& deadlines, vector<int>& profits)//asli vla O(n)
{
    vector<Job> jobs (names.length());
    int maxd = 0;
    for(int i = 0; i < jobs.size(); i++)
    {
        jobs[i] = Job(names[i], deadlines[i], profits[i]);
        maxd = max(maxd, deadlines[i]);
    }
    int maxprof = 0;
    sort(jobs.begin(), jobs.end());
    
    vector<int> pa (maxd + 1);//parent array
    for(int i = 0; i < pa.size(); i++)
        pa[i] = i;

    vector<char> res (maxd + 1);
    for(int i = jobs.size() - 1; i >= 0; i--)
    {
        int sl = find(pa, jobs[i].dead);//set leader
        if(sl != 0)
        {
            res[sl] = jobs[i].name;
            maxprof += jobs[i].profit;
            int slm1 = find(pa, sl - 1);//minus 1 vle ka set leader
            pa[sl] = slm1;
        }
    }
    for(int i = 1; i < res.size(); i++)
        cout << res[i] << " ";
    cout << endl;
    return maxprof;
}

int main(int argc, char** argv)
{
    // vector<int> wts {10, 40, 20, 30};
    // vector<int> prices {60, 40, 100, 120};
    // int cap = 50;
    // cout << fractionalKnapsack(wts, prices, cap) << endl;
    // vector<int> arrivals {900, 1800, 1500, 950, 1100, 940};
    // vector<int> departures {910, 1200, 1130, 2000, 1120, 1900};
    // cout << minPlatforms(arrivals, departures) << endl;
    // string names = "abcdefghijklmno";
    // vector<int> deadlines {4, 2, 3, 2, 4, 5, 1, 1, 2, 3, 5, 2, 3, 5, 4};
    // vector<int> profits {37, 64, 98, 70, 80, 40, 54, 76, 42, 89, 27, 92, 38, 77, 46};
    // cout << jobSequencing(names, deadlines, profits) << endl;
    // vector<vector<int>> grid {{1, 0, 0, 1, 0, 0, 1, 0},
    //                           {1, 1, 1, 1, 1, 1, 1, 1},
    //                           {1, 1, 0, 1, 1, 1, 1, 1},
    //                           {1, 0, 1, 1, 1, 1, 1, 0},
    //                           {0, 1, 1, 1, 1, 1, 1, 1},
    //                           {1, 0, 1, 0, 1, 1, 0, 1},
    //                           {1, 0, 0, 1, 1, 1, 1, 1}};
    // cout << maxsizeSquareof1s(grid) << endl;
    string names = "abdecfghijklmnopqrstuv";
    vector<int> deadlines {4, 2, 3, 1, 8, 3, 2, 1, 2, 6, 5, 7, 9, 2, 6, 5, 2, 7, 6, 9, 1, 6};
    vector<int> profits {100, 67, 97, 44, 33, 77, 11, 99, 37, 76, 34, 47, 78, 44, 39,
                         75, 49, 33, 48, 92, 43, 90};
    cout << jobSequencing2(names, deadlines, profits) << endl;
}