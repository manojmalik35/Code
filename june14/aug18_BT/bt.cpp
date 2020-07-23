#include<iostream>
#include<vector>
#include<list>
#include<climits>

using namespace std;

class Node
{
    public:
        int data;
        Node* left = NULL;
        Node* right= NULL;

        Node(int data)
        {
            this->data = data;
        }
};

Node* construct(vector<int>& dlist)
{
    Node* root = NULL;
    list<Node*> stack;
    for(int i = 0; i < dlist.size(); i++)
    {
        if(dlist[i] == -1)
            stack.pop_front();
        else
        {
            Node* nn = new Node(dlist[i]);
            if(stack.size() == 0)
                root = nn;
            else
            {
                Node* top = stack.front();
                if(top->left == NULL)
                    top->left = nn;
                else
                    top->right = nn;
            }
            stack.push_front(nn);
        }
    }
    return root;
}

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
        cout << "." << endl;
    else
        cout << root->right->data << endl;

    display(root->left);
    display(root->right);
}

int size(Node* root)
{
    if(root == NULL)
        return 0;
    int s = 0;
    int left = size(root->left);
    int right = size(root->right);
    s = left + right + 1;
    return s;
}

int sum(Node* root)
{
    if(root == NULL)
        return 0;
    int s = root->data;
    int ls = sum(root->left);
    int rs = sum(root->right);
    s += ls + rs; 
    return s;
}

int max(Node* root)
{
    if(root == NULL)
        return INT_MIN;
    int m = root->data;
    int ml = max(root->left);
    int mr = max(root->right);
    m = max(m, max(ml, mr));
    return m;
}

int height(Node* root)
{
    if(root == NULL)
        return 0;//-1 for edges
    int h = 0;
    int hl = height(root->left);
    int hr = height(root->right);
    h = max(hl, hr) + 1;
    return h;
}

bool find(Node* root, int k)
{
    if(root == NULL)
        return false;
    if(root->data == k)
        return true;
    bool fl = find(root->left, k);
    if(fl)
        return true;
    bool fr = find(root->right, k);
    if(fr)
        return true;
    return false;
}

vector<Node*>* node2rootPath(Node* root, int data)
{
    if(root == NULL)
        return NULL;

    if(root->data == data)
    {
        vector<Node*>* bres = new vector<Node*>();
        bres->push_back(root);
        return bres;
    }

    vector<Node*>* rresl = node2rootPath(root->left, data);
    if(rresl != NULL)
    {
        rresl->push_back(root);
        return rresl;
    }

    vector<Node*>* rresr = node2rootPath(root->right, data);
    if(rresr != NULL)
    {
        rresr->push_back(root);
        return rresr;
    }
    return NULL;
}

Node* removeLeaves(Node* root)
{
    if(root == NULL)
        return NULL;

    if(root->left == NULL && root->right == NULL)
        return NULL;

    root->left = removeLeaves(root->left);
    root->right = removeLeaves(root->right);
    return root;
}

void printSingleChild(Node* root)
{
    if(root == NULL)
        return;
    if(root->left != NULL && root->right == NULL)
        cout << root->left->data << " ";
    if(root->left == NULL && root->right != NULL)
        cout << root->right->data << " ";
    printSingleChild(root->left);
    printSingleChild(root->right);
}

int main(int argc, char** argv)
{
    vector<int> dlist {50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60,
                       -1, 70, -1, -1, 87, -1, -1, -1};
    Node* root = construct(dlist);
    // display(root);
    // cout << size(root) << endl;
    // cout << sum(root) << endl;
    // cout << max(root) << endl;
    // cout << height(root) << endl;
    // cout << find(root, 80) << endl;
    // vector<Node*>* n2r = node2rootPath(root, 30);
    // for(auto itr : *n2r)
    //     cout << itr->data << " ";
    // cout << endl;
    Node* node = removeLeaves(root);
    // display(node);
    printSingleChild(root);
}