#include<iostream>
#include<vector>
#include<climits>
#include<bits/stdc++.h>

using namespace std;

int lis(vector<int>& arr)//longest increasing subset
{
    vector<int> strg (arr.size(), 0);
    strg[0] = 1;
    int mymax = max(INT_MIN, strg[0]);
    for(int i = 1; i < strg.size(); i++)
    {
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[i] > arr[j])
                strg[i] = max(strg[i], strg[j]);
        }
        strg[i]++;
        mymax = max(mymax, strg[i]);
    }
    return mymax;
}

int lbs(vector<int>& arr)//longest bitonic subset
{
    vector<int> lis (arr.size(), 0);
    vector<int> lds (arr.size(), 0);
    lis[0] = 1;
    for(int i = 1; i < lis.size(); i++)
    {
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[i] > arr[j])
                lis[i] = max(lis[i], lis[j]);
        }
        lis[i]++;
    }

    lds[lds.size() - 1] = 1;
    for(int i = lds.size() - 2; i >= 0; i--)
    {
        for(int j = i + 1; j < lds.size(); j++)
        {
            if(arr[i] > arr[j])
                lds[i] = max(lds[i], lds[j]);
        }
        lds[i]++;
    }

    int mymax = INT_MIN;
    for(int i = 0; i < lis.size(); i++)
        mymax = max(mymax, lis[i] + lds[i]);
    return mymax - 1;
}

int rodCut(vector<int>& arr)
{
    vector<int> strg (arr.size(), 0);
    strg[0] = arr[0];
    strg[1] = arr[1];
    for(int i = 2; i < strg.size(); i++)
    {
        int left = 1;
        int right = i - 1;
        while(left <= right)
        {
            strg[i] = max(strg[i], strg[left] + strg[right]);
            left++;
            right--;
        }
        strg[i] = max(strg[i], arr[i]);
    }
    return strg[strg.size() - 1];
}

int maxSumNoAdjElem(vector<int>& arr)
{
    int include = arr[0];
    int exclude = 0;
    for(int i = 1; i < arr.size(); i++)
    {
        int oldinc = include;
        include = exclude + arr[i];
        exclude = max(oldinc, exclude);
    }
    return max(include,exclude);
}

class Interval
{
    public:
        int start;
        int end;
        Interval()
        {
        }

        Interval(int start, int end)
        {
            this->start = start;
            this->end = end;
        }

        bool operator<(const Interval& other) const
        {
            // return this->start < other.start; //for lis approach
            return this-> end < other.end; //for greedy approach
        }
};

int activitySelection(vector<int>& starts, vector<int>& ends)// by lis (n^2)
{
    vector<Interval> intvs (starts.size());
    for(int i = 0; i < intvs.size(); i++)
        intvs[i] = Interval(starts[i], ends[i]);

    sort(intvs.begin(), intvs.end());
    vector<int> lis (intvs.size(), 0);
    lis[0] = 1;
    int mymax = 0;
    for(int i = 1; i < lis.size(); i++)
    {
        for(int j = i - 1; j >= 0; j--)
        {
            if(intvs[j].end <= intvs[i].start)
                lis[i] = max(lis[i], lis[j]);
        }
        lis[i]++;
        mymax = max(mymax, lis[i]);
    }
    return mymax;
}

// int activitySelection2(vector<int>& starts, vector<int>& ends)
// {
//     vector<Interval>* intvs = new vector<Interval> (starts.size());
//     for(int i = 0; i < starts.size(); i++)
//         intvs->push_back(Interval(starts[i], ends[i]));

//     sort(intvs->begin(), intvs->end());
//     vector<int>* lis = new vector<int> (intvs->size(), 0);
//     lis[0] = 1;
//     int mymax = 0;
//     for(int i = 1; i < lis.size(); i++)
//     {
//         for(int j = i - 1; j >= 0; j--)
//         {
//             if(intvs[j].end <= intvs[i].start)
//                 lis[i] = max(lis[i], lis[j]);
//         }
//         lis[i]++;
//         mymax = max(mymax, lis[i]);
//     }
//     return mymax;
// }

int activitySelectionGreedy(vector<int>& starts, vector<int>& ends)// O(n)
{
    vector<Interval> intvs (starts.size());
    for(int i = 0; i < intvs.size(); i++)
        intvs[i] = Interval(starts[i], ends[i]);

    sort(intvs.begin(), intvs.end());
    int count = 1;  //kyuki pehli activity to krni hi h
    int last = 0; //last activity done index
    for(int i = 1; i < intvs.size(); i++)
    {
        if(intvs[i].start > intvs[last].end)
        {
            count++;
            last = i;
        }
    }
    return count;
}

int main(int argc, char** argv)
{
    // vector<int> arr {10, 21, 9, 33, 22, 50, 41, 60, 80, 7};
    // cout << lis(arr) << endl;
    // cout << lbs(arr) << endl;
    // vector<int> rodPrices {0, 3, 5, 6, 15, 10, 25, 12, 24};
    // cout << rodCut(rodPrices) << endl;
    // vector<int> arr {5, 6, 10, 100, 10, 5};
    // cout << maxSumNoAdjElem(arr) << endl;
    vector<int> starts {12, 2, 6, 7, 9, 3, 3};
    vector<int> ends {14, 5, 8, 10, 11, 4, 7};
    // cout << activitySelection(starts, ends) << endl;
    cout << activitySelectionGreedy(starts, ends) << endl;
}   