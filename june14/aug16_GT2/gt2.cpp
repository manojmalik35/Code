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

int size(Node* root)
{
    int count = 0;
    for(Node* child : root->children)
    {
        int rres = size(child);
        count += rres;
    }
    return count + 1;//1 for root
}

int height(Node* root)
{
    int h = 0;
    for(Node* child : root->children)
    {
        int rres = height(child);
        h = max(h, rres);
    }
    return h + 1;//1 for root
}

vector<Node*>* node2rootpath(Node* root, int data)
{
    if(root->data == data)
    {
        vector<Node*>* bres = new vector<Node*>();
        bres->push_back(root);
        return bres;
    }
    for(auto child : root->children)
    {
        vector<Node*>* rres = node2rootpath(child, data);
        if(rres != NULL)
        {
            rres->push_back(root);
            return rres;
        }
    }

    return NULL;   
}

int lca(Node* root, int n1, int n2)//least common ancestor
{
    if(find(root, n1) == false || find(root, n2) == false)
        return -1;
    
    vector<Node*>* n1p = node2rootpath(root, n1);
    vector<Node*>* n2p = node2rootpath(root, n2);
    int i = n1p->size() - 1;
    int j = n2p->size() - 1;
    while (i >=0 || j >= 0)
    {
        if((*n1p)[i]->data == (*n2p)[j]->data)
        {
            i--;
            j--;
        }
        else
            break;  
    }
    return (*n1p)[i + 1]->data;
}

void levelOrder(Node* root)
{
    list<Node*> queue;
    queue.push_back(root);
    while(queue.size() > 0)
    {
        Node* rem = queue.front();
        queue.pop_front();

        cout << rem->data << " ";

        for(auto child : rem->children)
            queue.push_back(child);
    }
}

void levelOrderLinewise(Node* root)//Technique 1
{
    list<Node*>* cq = new list<Node*>();
    list<Node*>* nq = new list<Node*>();
    cq->push_back(root);
    while(cq->size() > 0)
    {
        Node* rem = cq->front();
        cq->pop_front();

        cout << rem->data << " ";

        for(auto child : rem->children)
            nq->push_back(child);

        if(cq->size() == 0)
        {
            cq = nq;
            nq = new list<Node*>();
            cout << endl;
        }
    }

}

int main(int argc, char** argv)
{
    vector<int> dlist {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
                       -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
    Node* root = construct(dlist);
    // display(root);
    // cout << size(root) << endl;
    // cout << height(root) << endl;
    // vector<int>* res = node2rootpath(root, 110);
    // for(auto itr : *res)
    //     cout << itr << " ";
    // cout << endl;
    // cout << lca(root, 70, 120) << endl;
    // levelOrder(root);
    levelOrderLinewise(root);
}