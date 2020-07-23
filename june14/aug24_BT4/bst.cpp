#include<iostream>
#include<vector>

using namespace std;

class Node
{
    public:
        int data;
        Node* left = NULL;
        Node* right = NULL;

        Node(int data)
        {
            this->data = data;
        }
};

void display(Node* root)
{
    if(root == NULL)
        return;
    if(root->left == NULL)
        cout << ".";
    else
        cout << root->left->data;
    cout << " -> " << root->data << " <- ";
    if(root->right == NULL)
        cout << ".";
    else
        cout << root->right->data;
    cout << endl;
    display(root->left);
    display(root->right);
}

Node* construct(vector<int>& sa, int lo, int hi)
{
    if(lo > hi)
        return NULL;

    int mid = (lo + hi) / 2;
    Node* root = new Node(sa[mid]);
    root->left = construct(sa, lo, mid - 1);
    root->right = construct(sa, mid + 1, hi);
    return root;
}

bool find(Node* root, int data)
{
    if(root == NULL)
        return false;

    if(root->data == data)
        return true;
    else if(data < root->data)
        return find(root->left, data);
    else
        return find(root->right, data);
}

int max(Node* root)
{
    if(root->right == NULL)
        return root->data;
    else
        return max(root->right);
}

int min(Node* root)
{
    if(root->left == NULL)
        return root->data;
    else
        return min(root->left);
}

int main(int argc, char** argv)
{
    vector<int> sa {12, 25, 37, 50, 62, 75, 87};
    Node* root = construct(sa, 0, sa.size() - 1);
    // display(root);
    cout << find(root, 87) << endl;
    cout << max(root) << " " << min(root) << endl;
}