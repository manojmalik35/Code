#include<iostream>
#include<vector>

using namespace std;

vector<int>* merge2sortedarrays(vector<int>& one, vector<int>& two)
{
    vector<int>* merged = new vector<int> (one.size() + two.size(), 0);
    int i = 0, j = 0, k = 0;
    while(i < one.size() && j < two.size())
    {
        if(one[i] < two[j])
            merged->at(k++) = one[i++];
        else
            merged->at(k++) = two[j++];
    }

    while(i < one.size())
        merged->at(k++) = one[i++];

    while(j < two.size())
        merged->at(k++) = two[j++];

    return merged;
}

vector<int>* mergeSort(vector<int>& arr, int lo, int hi)
{
    if(lo == hi)
    {
        return new vector<int> {arr[lo]};
    }

    int mid = (lo + hi) / 2;
    vector<int>* lh = mergeSort(arr, lo, mid);
    vector<int>* rh = mergeSort(arr, mid + 1, hi);
    vector<int>* narr = merge2sortedarrays(*lh, *rh);
    return narr;
}

int mergeforCI(vector<int>& arr, int lo, int mid, int hi)
{
    vector<int> narr (hi - lo + 1);
    int i = lo, j = mid + 1, k = 0;
    int inv = 0;
    while(i <= mid && j <= hi)
    {
        if(arr[i] < arr[j])
            narr[k++] = arr[i++];
        else
        {
            inv += mid - i + 1;
            narr[k++] = arr[j++];
        }
    }

    while(i <= mid)
        narr[k++] = arr[i++];

    while(j <= hi)
        narr[k++] = arr[j++];

    for(int i = 0; i < narr.size(); i++)
        arr[lo + i] = narr[i];

    return inv;
}

int countInversions(vector<int>& arr, int lo, int hi)
{
    if(lo == hi)
        return 0;

    int mid = (lo + hi) / 2;
    int lh = countInversions(arr, lo, mid);
    int rh = countInversions(arr, mid + 1, hi);
    int merinv = mergeforCI(arr, lo, mid, hi);
    return lh + rh + merinv;
}

int main(int argc, char** argv)
{
    // vector<int> arr {2, 1, 5, 8, 9, 6, 4, 0};
    // vector<int>* sorted = mergeSort(arr, 0, arr.size() - 1);
    // for(auto itr : *sorted)
    //     cout << itr << " ";
    // cout << endl;

    vector<int> arr {2, 7, 5, 4, 6, 8, 9, 1};
    int inversions = countInversions(arr, 0, arr.size() - 1);
    cout << inversions << endl;
}