#include<iostream>
#include<vector>
#include<stack>

using namespace std;

class Node
{
    public:
        int data;
        Node* left = NULL;
        Node* right = NULL;
        int ht = 1;
        int bal = 0;

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
    cout << " -> " << root->data << "_" << root->ht << "_" << root->bal << " <- ";
    if(root->right == NULL)
        cout << ".";
    else
        cout << root->right->data;
    cout << endl;
    display(root->left);
    display(root->right);
}

int getht(Node* node)
{
    int lh = node->left != NULL ? node->left->ht : 0;
    int rh = node->right != NULL ? node->right->ht : 0;
    return max(lh, rh) + 1;
}

int getbal(Node* node)
{
    int lh = node->left != NULL ? node->left->ht : 0;
    int rh = node->right != NULL ? node->right->ht : 0;
    return lh - rh;
}

Node* construct(vector<int>& sa, int lo, int hi)
{
    if(lo > hi)
        return NULL;

    int mid = (lo + hi) / 2;
    Node* root = new Node(sa[mid]);
    root->left = construct(sa, lo, mid - 1);
    root->right = construct(sa, mid + 1, hi);
    root->ht = getht(root);
    root->bal = getbal(root);
    return root;
}

int max(Node* root)
{
    if(root->right == NULL)
        return root->data;
    else
        return max(root->right);
}

Node* rightRotation(Node* x)
{
    Node* y = x->left;
    Node* t = y->right;
    y->right = x;
    x->left = t;

    x->ht = getht(x);
    x->bal = getbal(x);
    y->ht = getht(y);
    y->bal = getbal(y);
    return y;
}

Node* leftRotation(Node* x)
{
    Node* y = x->right;
    Node* t = y->left;
    y->left = x;
    x->right = t;

    x->ht = getht(x);
    x->bal = getbal(x);
    y->ht = getht(y);
    y->bal = getbal(y);
    return y;
}

Node* add(Node* root, int data)
{
    if(root == NULL)
    {
        Node* node = new Node(data);
        return node;
    }

    if(data < root->data)
        root->left = add(root->left, data);
    else if(data > root->data)
        root->right = add(root->right, data);
    else
    {
        //kuch nhi
    }

    root->ht = getht(root);
    root->bal = getbal(root);
    if(root->bal > 1)
    {
        //left problem
        if(root->left->bal >= 0)//ll problem
            root = rightRotation(root);
        else//lr problem
        {
            root->left = leftRotation(root->left);
            root = rightRotation(root);
        }
    }
    else if(root->bal < -1)
    {
        //right rotation
        if(root->right->bal <= 0)//rr problem
            root = leftRotation(root);
        else//rl problem
        {
            root->right = rightRotation(root->right);
            root = leftRotation(root);
        }

    }

    return root;
}

Node* remove(Node* root, int data)
{
    if(root == NULL)
        return NULL;

    if(data < root->data)
        root->left = remove(root->left, data);
    else if(data > root->data)
        root->right = remove(root->right, data);
    else
    {
        if(root->left == NULL || root->right == NULL)
            root = root->left == NULL ? root->right : root->left;
        else
        {
            int lmax = max(root->left);
            root->data = lmax;
            root->left = remove(root->left, lmax);
        }
    }

    if(root != NULL)
    {
        root->ht = getht(root);
        root->bal = getbal(root);
        if(root->bal > 1)
        {
            //left problem
            if(root->left->bal >= 0)//ll problem
                root = rightRotation(root);
            else//lr problem
            {
                root->left = leftRotation(root->left);
                root = rightRotation(root);
            }
        }
        else if(root->bal < -1)
        {
            //right rotation
            if(root->right->bal <= 0)//rr problem
                root = leftRotation(root);
            else//rl problem
            {
                root->right = rightRotation(root->right);
                root = leftRotation(root);
            }

        }
    }

    return root;
}

int main(int argc, char** argv)
{
    vector<int> sa {12, 25, 37, 50, 62, 75, 87};
    Node* root = construct(sa, 0, sa.size() - 1);
    add(root, 30);
    add(root, 28);
    add(root, 29);
    remove(root, 12);
    remove(root, 25);
    remove(root, 37);
    display(root);
}