
#include<iostream>
#include<vector>

using namespace std;

void swap(vector<int>& arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

int partitioning(vector<int>& arr, int lo, int hi)
{
    int pivot = arr[hi];
    int i = 0, j = 0;
    // 0 to j - 1 = less than equal to
    // j to i - 1 = greater than
    // i to hi = unknown
    while(i <= hi)
    {
        if(arr[i] > pivot)
            i++;
        else
        {
            swap(arr, i, j);
            i++;
            j++;
        }
    }
    return j - 1;
}

//kth smallest in O(n)
int quickselect(vector<int>& arr, int lo, int hi, int k)
{
    while(true)
    {
        int pi = partitioning(arr, lo, hi);
        if(k < pi)
            hi = pi - 1;
        else if(k > pi)
            lo = pi + 1;
        else
            return arr[pi];
    }
}

void quicksort(vector<int>& arr, int lo, int hi)
{
    if(lo >= hi)
        return;

    int pi = partitioning(arr, lo, hi);
    quicksort(arr, lo, pi - 1);
    quicksort(arr, pi + 1, hi);
}

vector<int>* countSort(vector<int>& arr)
{
    int n = arr.size();
    int r = 10;//assumed
    vector<int> fmap (r, 0);
    for(int val : arr)
        fmap[val]++;

    for(int i = 1; i < fmap.size(); i++)
        fmap[i] += fmap[i - 1];

    vector<int>* output = new vector<int> (n, 0);
    for(int i = arr.size() - 1; i >= 0; i--)
    {
        int val = arr[i];
        fmap[val]--;
        output->at(fmap[val]) = val;
    }
    return output;
}

vector<int>* countSortforRS(vector<int>& arr, int div)
{
    int n = arr.size();
    int r = 10;//assumed
    vector<int> fmap (r, 0);
    for(int val : arr)
    {
        val = val / div % 10;
        fmap[val]++;
    }

    for(int i = 1; i < fmap.size(); i++)
        fmap[i] += fmap[i - 1];

    vector<int>* output = new vector<int> (n, 0);
    for(int i = arr.size() - 1; i >= 0; i--)
    {
        int val = arr[i];
        val = val / div % 10;
        fmap[val]--;
        output->at(fmap[val]) = arr[i];
    }
    return output;
}

void radixSort(vector<int>& input)
{
    int mymax = 0; 
    for(int val : input)
        mymax = max(mymax, val);

    int div = 1;
    while(mymax / div > 0) 
    {
        input = *countSortforRS(input, div);
        div = div * 10;
    }
}

int main(int argc, char** argv)
{
    // vector<int> arr {9, 8, 3, 1, 7, 5, 2, 6};
    // for(int i = 0; i < arr.size(); i++)
    //     cout << quickselect(arr, 0, arr.size() - 1, i) << endl;
    // quicksort(arr, 0, arr.size() - 1);
    // for(auto itr : arr)
    //     cout << itr << " ";
    // vector<int>* res = countSort(arr);
    // for(int val : *res)
    //     cout << val << " ";
    vector<int> arr {576, 282, 77, 34, 981, 62, 11, 348, 7, 412, 69, 438, 992, 324, 287, 76};
    radixSort(arr);
    for(int val : arr)
        cout << val << " ";
}