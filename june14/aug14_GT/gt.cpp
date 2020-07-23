#include<iostream>
#include<vector>
#include<list>
#include<climits>

using namespace std;

class Node
{
    public:
        int data = 0;//default value dalna zruri ni h pr c++ chutiya h garbage value na daal de isliye
        vector<Node*> children;

        Node(int data)
        {
            this->data = data;
        }
};

Node* construct(vector<int>& dlist)
{
    Node* root = NULL;
    list<Node*> nlist;
    // vector<Node> nlist;
    for(int i = 0; i < dlist.size(); i++)
    {
        if(dlist[i] == -1)
            nlist.pop_front();
        else
        {
            Node* nn = new Node(dlist[i]);
            if(nlist.size() == 0)
                root = nn;
            else
            {
                Node* top = nlist.front();
                top->children.push_back(nn);
            }
            nlist.push_front(nn);
        }
    }
    return root;
}

void display(Node* root)
{
    cout << root->data << " -> ";
    for(Node* child : root->children)
        cout << child->data << ", ";
    cout << "." << endl;
    for(Node* child : root->children)
        display(child);
}

bool find(Node* root, int val)
{
    if(root->data == val)
        return true;
    for(auto child : root->children)
    {
        bool rres = find(child, val);
        if(rres)
            return true;
    }
    return false;
}

int gtmax(Node* root)
{
    int mymax = root->data;
    for(auto child : root->children)
    {
        int rres = gtmax(child);
        if(rres > mymax)
            mymax = rres;
    }
    return mymax;
}

int gtmin(Node* root)
{
    int mymin = root->data;
    for(auto child : root->children)
    {
        int rres = gtmax(child);
        if(rres < mymin)
            mymin = rres;
    }
    return mymin;
}

int main(int argc, char** argv)
{
    vector<int> dlist {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
                       -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
    Node* root = construct(dlist);
    display(root);
    // // cout << find(root, 11) << endl;
    // cout << gtmax(root) << endl;
    // cout << gtmin(root) << endl;
}