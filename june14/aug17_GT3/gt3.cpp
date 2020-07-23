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

void levelOrderLinewise2(Node* root)//Technique 2
{
    list<Node*> queue;
    queue.push_back(root);
    queue.push_back(NULL);
    while(queue.size() > 1)//bcoz last me null reh jayga
    {
        Node* rem = queue.front();
        queue.pop_front();

        if(rem == NULL)
        {
            queue.push_back(NULL);
            cout << endl;
        }
        else
        {
            cout << rem->data << " ";
            for(auto child : rem->children)
                queue.push_back(child);
        }
    }
}

void levelOrderLinewise3(Node* root)//Technique 3
{
    list<Node*> queue;
    queue.push_back(root);
    while(queue.size() > 0)
    {
        int size = queue.size();
        while(size-- > 0)
        {
            Node* rem = queue.front();
            queue.pop_front();

            cout << rem->data << " ";
            for(auto child : rem->children)
                queue.push_back(child);
        }
        cout << endl;
    }
}

void levelOrderZigzag(Node* root)
{
    list<Node*>* st1 = new list<Node*>();//stack 1
    list<Node*>* st2 = new list<Node*>();//stack 2
    bool flag = false;
    st1->push_front(root);
    while(st1->size() > 0)
    {
        Node* rem = st1->front();
        st1->pop_front();

        cout << rem->data << " ";

        if(flag)
        {
            for(auto child : rem->children)
                st2->push_front(child);
        }
        else
        {
            for(int i = rem->children.size() - 1; i >= 0; i--)
                st2->push_front(rem->children[i]);
        }

        if(st1->size() == 0)
        {
            st1 = st2;
            st2 = new list<Node*>();
            flag = !flag;
            cout << endl;
        }
    }
}

class HeapMover
{
    public:
        int tmax = INT_MIN;
        int tmin = INT_MAX;
        bool find = false;
        int pred = -1;//predecessor
        int succ = -1;//successor
        int prev = -1;
        int floor = INT_MIN;
        int ceil = INT_MAX;
};

void multisolver(Node* root, int data, HeapMover& hm)
{
    if(hm.find == false && root->data == data)
    {
        hm.find = true;
        hm.pred = hm.prev;
    }

    if(hm.find && hm.prev == data)
        hm.succ = root->data;

    if(root->data < data && root->data > hm.floor)
        hm.floor = root->data;
    
    if(root->data > data && root->data < hm.ceil)
        hm.ceil = root->data;

    hm.tmax = max(hm.tmax, root->data);
    hm.tmin = min(hm.tmin, root->data);
    hm.prev = root->data;

    for(auto child : root->children)
        multisolver(child, data, hm);
}

int kthlargest(Node* root, int k, HeapMover& hm)
{
    int res = INT_MAX;
    for(int i = 0; i < k; i++)
    {
        hm.floor = INT_MIN;
        multisolver(root, res, hm);
        res = hm.floor;
    }
    return res;
}

int kthsmallest(Node* root, int k, HeapMover& hm)
{
    int res = INT_MIN;
    for(int i = 0; i < k; i++)
    {
        hm.ceil = INT_MAX;
        multisolver(root, res, hm);
        res = hm.ceil;
    }
    return res;
}

Node* linearize(Node* root)
{
    if(root->children.size() == 0)
        return root;

    Node* lastChild = root->children.back();
    Node* lastCTail = linearize(lastChild);    
    while(root->children.size() > 1)
    {
        Node* lsSecond = root->children[root->children.size() - 2];
        Node* tail = linearize(lsSecond);

        Node* last = root->children.back();
        root->children.pop_back();
        tail->children.push_back(last);
    }
    return lastCTail;
}

int main(int argc, char** argv)
{
    vector<int> dlist {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
                       -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
    Node* root = construct(dlist);
    // display(root);
    // levelOrderLinewise2(root);
    // levelOrderLinewise3(root);
    // levelOrderZigzag(root);
    HeapMover hm;
    multisolver(root, 80, hm);
    cout << hm.tmax << " " << hm.tmin << " " << hm.find << endl;
    cout << hm.pred << " " << hm.succ << endl;
    cout << hm.floor << " " << hm.ceil << endl;
    // cout << kthlargest(root, 3, hm) << endl;
    // cout << kthsmallest(root, 3, hm) << endl;
    // linearize(root);
    // display(root);
}