#include<iostream>
#include<list>
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

Node* construct(vector<int>& dlist)
{
    Node* root = NULL;
    list<Node*> nlist;
    for(int i = 0; i < dlist.size(); i++)
    {
        if(dlist[i] != -1)
        {
            Node* nn = new Node(dlist[i]);
            if(nlist.size() == 0)
                root = nn;
            else
            {
                Node* top = nlist.front();
                if(top->left == NULL)
                    top->left = nn;
                else
                    top->right = nn;
            }
            nlist.push_front(nn);
        }
        else
            nlist.pop_front();
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
        cout << ".";
    else
        cout << root->right->data;
    cout << endl;
    display(root->left);
    display(root->right);
}

void root2leafprintinRange(Node* node, int lo, int hi, int ssf, string psf)//ssf = sum so far
{
    if(node == NULL)
        return;
    if(node->left == NULL && node->right == NULL)
    {
        ssf += node->data;
        psf += to_string(node->data) + " ";
        if(ssf >= lo && ssf <= hi)
            cout << psf << endl;
        return;
    }

    root2leafprintinRange(node->left, lo, hi, ssf + node->data, psf + to_string(node->data) + " ");
    root2leafprintinRange(node->right, lo, hi, ssf + node->data, psf + to_string(node->data) + " ");
}

Node* transformLD(Node* root)//left duplicates
{
    if(root == NULL)
        return NULL;

    root->left = transformLD(root->left);
    root->right = transformLD(root->right);
    Node* nn = new Node(root->data);
    nn->left = root->left;
    root->left = nn;
    return root;
}

Node* reverseTransformLD(Node* root)
{
    if(root == NULL)
        return NULL;

    Node* temp = root->left;//jis node ko skip kr k call lga rahe h vo to bkl free ho k pada h
    root->left = reverseTransformLD(root->left->left);
    root->right = reverseTransformLD(root->right);
    delete temp;//isliye delete krenge
    return root;
}

Node* construct2(vector<int>& pre, vector<int>& in, int psi, int pei, int isi, int iei)
{
    if(psi > pei || isi > iei)
        return NULL;

    Node* root = new Node(pre[psi]);

    int x = 0;
    for(int i = isi; i <= iei; i++)
    {
        if(in[i] == pre[psi])
        {
            x = i;
            break;
        }
    }

    int lhs = x - isi;
    root->left = construct2(pre, in, psi + 1, psi + lhs, isi, x - 1);
    root->right = construct2(pre, in, psi + lhs + 1, pei, x + 1, iei);
    return root;
}

Node* construct3(vector<int>& post, vector<int>& in, int psi, int pei, int isi, int iei)
{
    if(psi > pei || isi > iei)
        return NULL;
        
    Node* root = new Node(post[pei]);
    
    int x = 0;
    for(int i = iei; i >= isi; i--)
    {
        if(in[i] == post[pei])
        {
            x = i;
            break;
        }
    }

    int lhs = iei - x;
    root->left = construct3(post, in, psi, pei - lhs - 1, isi, x - 1);
    root->right = construct3(post, in, pei - lhs, pei - 1, x + 1, iei);
    return root;
}

int main(int argc, char** argv)
{
    vector<int> dlist {50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60,
                       -1, 70, -1, -1, 87, -1, -1, -1};
    Node* root = construct(dlist);
    // display(root);
    // root2leafprintinRange(root, 150, 250, 0, "");
    Node* node = transformLD(root);
    display(node);
    // Node* node2 = reverseTransformLD(node);
    // display(node2);
    // vector<int> pre {50, 25, 12, 37, 30, 75, 62, 70, 87};
    // vector<int> in {12, 25, 30, 37, 50, 62, 70, 75, 87};
    // vector<int> post {12, 30, 37, 25, 70, 62, 87, 75, 50};
    // Node* root = construct2(pre, in, 0, pre.size() - 1, 0, in.size() - 1);
    // Node* root = construct3(post ,in, 0, post.size() - 1, 0, in.size() - 1);
    // display(root);
}