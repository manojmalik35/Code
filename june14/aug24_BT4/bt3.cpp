#include<iostream>
#include<vector>
#include<stack>
#include<climits>

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

class BalPair
{
    public:
        bool isBal;
        int ht;
};

BalPair* isBalanced(Node* root)
{
    if(root == NULL)
    {
        BalPair* bp = new BalPair();
        bp->ht = 0;
        bp->isBal = true;
        return bp;
    }

    BalPair* mp = new BalPair();
    BalPair* lp = isBalanced(root->left);
    BalPair* rp = isBalanced(root->right);
    
    mp->isBal = lp->isBal && rp->isBal && abs(lp->ht - rp->ht) <= 1;
    mp->ht = max(lp->ht, rp->ht) + 1;
    
    return mp;
}

//style2 (return and impact) return something else and impact will be on something else
bool isBal = true;
int heightforIsBal(Node* root)
{
    if(root == NULL)
        return 0;

    int lh = heightforIsBal(root->left);
    int rh = heightforIsBal(root->right);

    if(abs(lh - rh) > 1)
        isBal = false;

    return max(lh, rh) + 1;
}

//diameter in 2nd style
int dia = 0;
int heightforDia(Node* root)
{
    if(root == NULL)
        return 0;

    int lh = heightforDia(root->left);
    int rh = heightforDia(root->right);

    if(lh + rh + 1 > dia)
        dia = lh + rh + 1;

    return max(lh, rh) + 1;
}

class bstPair
{
    public:
        bool isbst;
        int bmax;
        int bmin;
        Node* lbstroot = NULL;
        int lbstsize;
};

bstPair* isBST(Node* root)
{
    if(root == NULL)
    {
        bstPair* bp = new bstPair();
        bp->isbst = true;
        bp->bmax = INT_MIN;
        bp->bmin = INT_MAX;
        return bp;
    }

    bstPair* lp = isBST(root->left);
    bstPair* rp = isBST(root->right);
    bstPair* mp = new bstPair();

    mp->bmax = max(root->data, max(lp->bmax, rp->bmax));
    mp->bmin = min(root->data, min(lp->bmin, rp->bmin));
    mp->isbst = lp->isbst && rp->isbst && root->data > lp->bmax && root->data < rp->bmin;
    return mp;
}

bstPair* largestBST(Node* root)
{
    if(root == NULL)
    {
        bstPair* bp = new bstPair();
        bp->isbst = true;
        bp->bmax = INT_MIN;
        bp->bmin = INT_MAX;
        bp->lbstroot = NULL;
        bp->lbstsize = 0;
        return bp;
    }

    bstPair* lp = largestBST(root->left);
    bstPair* rp = largestBST(root->right);
    bstPair* mp = new bstPair();

    mp->bmax = max(root->data, max(lp->bmax, rp->bmax));
    mp->bmin = min(root->data, min(lp->bmin, rp->bmin));
    mp->isbst = lp->isbst && rp->isbst && root->data > lp->bmax && root->data < rp->bmin;
    if(mp->isbst)
    {
        mp->lbstsize = lp->lbstsize + rp->lbstsize + 1;
        mp->lbstroot = root; 
    }
    else
    {
        if(lp->lbstsize > rp->lbstsize)
        {
            mp->lbstsize = lp->lbstsize;
            mp->lbstroot = lp->lbstroot;
        }
        else
        {
            mp->lbstsize = rp->lbstsize;
            mp->lbstroot = rp->lbstroot;
        }
    }
    
    return mp;
}

void printkdown(Node* tnode, int k)
{
    if(tnode == NULL)
        return;

    if(k == 0)
    {
        cout << tnode->data << " ";
        return;
    }

    printkdown(tnode->left, k - 1);
    printkdown(tnode->right, k - 1);
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

void printkdown2(Node* tnode, Node* blocker, int k)//for kfar
{
    if(tnode == NULL || tnode == blocker || k < 0)
        return;

    if(k == 0)
    {
        cout << tnode->data << " ";
        return;
    }

    printkdown2(tnode->left, blocker, k - 1);
    printkdown2(tnode->right, blocker, k - 1);
}

void printkfar(Node* root, int data, int k)
{
    vector<Node*>* n2rPath = node2rootPath(root, data);
    printkdown2(n2rPath->at(0), NULL, k);
    for(int i = 1; i < n2rPath->size(); i++)
        printkdown2(n2rPath->at(i), n2rPath->at(i - 1), k - i);
}

int main(int argc, char** argv)
{
    vector<int> pre {50, 25, 12, 37, 30, 51, 75, 62, 60, 70, 87, 80, 90};
    vector<int> in {12, 25, 30, 37, 51, 50, 60, 62, 70, 75, 80, 87, 90};
    // vector<int> post {12, 30, 37, 25, 70, 62, 87, 75, 50};
    Node* root = construct2(pre, in, 0, pre.size() - 1, 0, in.size() - 1);
    // display(root);
    // BalPair* res = isBalanced(root);
    // cout << res-> isBal << endl;
    // heightforIsBal(root);
    // heightforDia(root);
    // cout << dia << endl;
    // bstPair* res = isBST(root);
    // cout << res->isbst << endl;
    // bstPair* res = largestBST(root);
    // cout << res->lbstroot->data << " " << res->lbstsize << endl; 
    // printkdown(root, 3);
    printkfar(root, 75, 3);
}