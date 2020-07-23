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

int max(Node* root)
{
    if(root->right == NULL)
        return root->data;
    else
        return max(root->right);
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
        if(root->left == NULL && root->right == NULL)
            return NULL;
        else if(root->left == NULL)
            return root->right;
        else if(root->right == NULL)
            return root->left;
        else
        {
            int lmax = max(root->left);
            root->data = lmax;
            remove(root->left, lmax);
        }
    }
    return root;
}

int maxSum = 0;
void transformSogt(Node* root)//sum of greater tree
{
    if(root == NULL)
        return;
    transformSogt(root->right);
    int temp = root->data;
    root->data = maxSum;
    maxSum += temp;
    transformSogt(root->left);
}

int lca(Node* root, int d1, int d2)
{
    if(root == NULL)
        return 0;

    if(root->data < d1 && root->data < d2)
        return lca(root->right, d1, d2);
    else if(root->data > d1 && root->data > d2)
        return lca(root->left, d1, d2);
    else
        return root->data;
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

void printTargetSum1(Node* node, int tar, Node* root)//O(nh) && space O(h)
{
    if(node == NULL)
        return;

    int rm = tar - node->data;
    if(rm > root->data && find(root, rm))
        cout << node->data << " " << rm << endl;
    printTargetSum1(node->left, tar, root);
    printTargetSum1(node->right, tar, root);
}

void filler(Node* root, vector<int>& sorted)
{
    if(root == NULL)
        return;

    filler(root->left, sorted);
    sorted.push_back(root->data);
    filler(root->right, sorted);
}

void printTargetSum2(Node* root, int tar)//O(n) && space O(n)
{
    vector<int> sorted;
    filler(root, sorted);
    int left = 0;
    int right = sorted.size() - 1;
    while(left < right)
    {
        if(sorted[left] + sorted[right] < tar)
            left++;
        else if(sorted[left] + sorted[right] > tar)
            right--;
        else
        {
            cout << sorted[left] << " " << sorted[right] << endl;
            left++;
            right--;
        }
    }
}

class Euler
{
    public:
        Node* node = NULL;
        int state = 0;

        Euler(Node* node)
        {
            this->node = node;
        }
};

void printTargetSum3(Node* root, int tar)//time O(n) && space O(h)
{
    Euler* r1 = new Euler(root);
    stack<Euler*> ls;
    ls.push(r1);

    //separate objects isliye daale h kyuki ek stack change krega to dusre me b show honge
    //kyuki vo address stored h

    Euler* r2 = new Euler(root);
    stack<Euler*> rs;
    rs.push(r2);

    int lv = 0;//left value
    int rv = 0;//right value
    bool ml = true;//move left
    bool mr = true;//move right

    while(true)
    {
        while(ml && ls.size() > 0)
        {
            Euler* top = ls.top();
            if(top->node == NULL)
            {
                ls.pop();
                continue;
            }

            if(top->state == 0)
            {
                top->state++;
                ls.push(new Euler(top->node->left));
            }
            else if(top->state == 1)
            {
                top->state++;
                ls.push(new Euler(top->node->right));
                lv = top->node->data;
                break;
            }
            else
                ls.pop();
        }

        while(mr && rs.size() > 0)
        {
            Euler* top = rs.top();
            if(top->node == NULL)
            {
                rs.pop();
                continue;
            }

            if(top->state == 0)
            {
                top->state++;
                rs.push(new Euler(top->node->right));
            }
            else if(top->state == 1)
            {
                top->state++;
                rs.push(new Euler(top->node->left));
                rv = top->node->data;
                break;
            }
            else
                rs.pop();
        }

        if(lv > rv)
            break;
        else if(lv + rv < tar)
        {
            ml = true;
            mr = false;
        }
        else if(lv + rv > tar)
        {
            ml = false;
            mr =true;
        }
        else
        {
            cout << lv << " " << rv << endl;
            ml = true;
            mr = true;
        }
    }
}

void intersectionof2BSTs(Node* root1, Node* root2)
{
    Euler* r1 = new Euler(root1);
    stack<Euler*> st1;
    st1.push(r1);

    Euler* r2 = new Euler(root2);
    stack<Euler*> st2;
    st2.push(r2);

    int lv = 0;
    int rv = 0;
    int ml = true;
    int mr = true;

    while(true)
    {
        while(ml && st1.size() > 0)
        {
            Euler* top = st1.top();
            if(top->node == NULL)
            {
                st1.pop();
                continue;
            }

            if(top->state == 0)
            {
                top->state++;
                st1.push(new Euler(top->node->left));
            }
            else if(top->state == 1)
            {
                top->state++;
                st1.push(new Euler(top->node->right));
                lv = top->node->data;
                break;
            }
            else
                st1.pop();
        }

        while(mr && st2.size() > 0)
        {
            Euler* top = st2.top();
            if(top->node == NULL)
            {
                st2.pop();
                continue;
            }

            if(top->state == 0)
            {
                top->state++;
                st2.push(new Euler(top->node->left));
            }
            else if(top->state == 1)
            {
                top->state++;
                st2.push(new Euler(top->node->right));
                rv = top->node->data;
                break;
            }
            else
                st2.pop();
        }

        if(st1.size() == 0 || st2.size() == 0)
            break;

        if(lv < rv)
        {
            ml = true;
            mr = false;
        }
        else if(lv > rv)
        {
            ml = false;
            mr =true;
        }
        else
        {
            cout << lv << " ";
            ml = true;
            mr = true;
        }
    }
}

int main(int argc, char** argv)
{
    // vector<int> sa {12, 25, 37, 50, 62, 75, 87};
    // Node* root = construct(sa, 0, sa.size() - 1);
    // display(root);
    // add(root, 30);
    // display(root);
    // cout << "=====================" << endl;
    // remove(root, 75);
    // display(root);
    // transformSogt(root);
    // display(root);
    // add(root, 60);
    // add(root, 80);
    // add(root, 30);
    // cout << lca(root, 30, 12) << endl;
    vector<int> sa {10, 20 ,30, 50, 60, 70, 80};
    Node* root = construct(sa, 0, sa.size() - 1);
    add(root, 25); add(root, 35); add(root, 55); add(root, 65);
    // printTargetSum1(root, 105, root);
    // printTargetSum2(root, 105);
    // printTargetSum3(root, 105);
    vector<int> sa1 {10, 25, 36, 40, 65, 70, 90};
    Node* root1 = construct(sa1, 0, sa1.size() - 1);
    intersectionof2BSTs(root, root1);
}