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

//not complete
Node* construct3(vector<int>& pre, vector<int>& post, int prsi, int prei, int pssi, int psei)
{
    if(prsi == prei || pssi == psei)
    {
        Node* bn = new Node(pre[prsi]);
        return bn;
    }
    Node* root = new Node(pre[prsi]);

    int x = 0;
    for(int i = pssi; i <= psei; i++)
    {
        if(post[i] == pre[prsi + 1])
        {
            x = i;
            break;
        }
    }

    int lhs = x + 1;
    root->left = construct3(pre, post, prsi + 1, prsi + lhs, pssi, pssi + lhs - 1);
    root->right = construct3(pre, post, prsi + lhs + 1, prei, pssi + lhs, psei - 1);
    return root;
}

class Pair
{
    public:
        Node* node = NULL;
        int state;

        Pair(Node* node, int state)
        {
            this->node = node;
            this->state = state;
        }
};

void iterativeDFTraversals(Node* root)
{
    vector<int> pre;
    vector<int> in;
    vector<int> post;

    Pair* r = new Pair(root, 0);
    stack<Pair*> st;
    st.push(r);
    while(st.size() > 0)
    {
        Pair* top = st.top();
        if(top->node == NULL)
        {
            st.pop();
            continue;
        }

        // cout << top->node->data << endl;
        if(top->state == 0)
        {
            pre.push_back(top->node->data);
            top->state++;
            st.push(new Pair(top->node->left, 0));
        }
        else if(top->state == 1)
        {
            in.push_back(top->node->data);
            top->state++;
            st.push(new Pair(top->node->right, 0));
        }
        else if(top->state == 2)
        {
            post.push_back(top->node->data);
            st.pop();
        }
    }   

    for(auto itr : pre)
        cout << itr << " ";
    cout << endl;
    for(auto itr : in)
        cout << itr << " ";
    cout << endl;
    for(auto itr : post)
        cout << itr << " ";
    cout << endl;
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

int diameter(Node* root)//O(n^2)
{
    if(root == NULL)
        return 0;

    //case 1 : when the farthest nodes exist in different left and right subtrees
    int lh = height(root->left);//max distance between left and the deepest leaf
    int rh = height(root->right);//max distance between right and the deepest leaf
    int factor = lh + rh + 1;//lh + rh + 2 for edges and return -1 in height function

    //case 2 : when the farthest nodes exist in the same left subtree
    int ld = diameter(root->left);//max distance b/w any two nodes in the left subtree

    //case 2 : when the farthest nodes exist in the same right subtree
    int rd = diameter(root->right);//max distance b/w any two nodes in the right subtree

    return max(factor, max(ld, rd));   
}

class DiaPair
{
    public:
        int ht;
        int dia;
};

DiaPair* diameter2(Node* root)
{
    if(root == NULL)
    {
        DiaPair* bp = new DiaPair();
        bp->ht = 0;
        bp->dia = 0;
        return bp;
    }

    DiaPair* lp = diameter2(root->left);
    DiaPair* rp = diameter2(root->right);
    DiaPair* mp = new DiaPair();

    mp->ht = max(lp->ht, rp->ht) + 1;
    mp->dia = max(lp->ht + rp->ht + 1, max(lp->dia, rp->dia));
    return mp;
}

int main(int argc, char** argv)
{
    // vector<int> pre {50, 25, 12, 37, 30, 75, 62, 70, 87};
    // vector<int> in {12, 25, 30, 37, 50, 62, 70, 75, 87};
    // vector<int> post {12, 30, 37, 25, 70, 62, 87, 75, 50};
    // Node* root = construct2(pre, in, 0, pre.size() - 1, 0, in.size() - 1);

    vector<int> pre {50, 25, 12, 37, 30, 40, 75, 62, 60, 70, 87};
    vector<int> post {12, 30, 40, 37, 25, 60, 70, 62, 87, 75, 50};
    Node* root= construct3(pre, post, 0, pre.size() - 1, 0, post.size() - 1);
    display(root);
    // iterativeDFTraversals(root);
    // cout << diameter(root) << endl;
    // DiaPair* res = diameter2(root);
    // cout << res->dia << endl;

}