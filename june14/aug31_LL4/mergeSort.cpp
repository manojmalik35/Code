#include<iostream>

using namespace std;

class Node
{
    public:
        int data;
        Node* next;

        Node()
        {
            this->data = 0;
            this->next = NULL;
        }

        Node(int data, Node* next)
        {
            this->data = data;
            this->next = next;
        }
};

void display(Node* head)
{
    for(Node* i = head; i != NULL; i = i->next)
        cout << i->data << " ";
    cout << endl;
}

void addLast(Node*& head, Node*& tail, int data)
{
    if(head == NULL)
        head = tail = new Node(data, NULL);
    else
    {
        Node* nn = new Node(data, NULL);
        tail->next = nn;
        tail = nn;
    }
}

void removeFirst(Node*& head, Node*& tail)
{
    if(head == NULL)
        return;

    Node* temp = head;
    if(head == tail)
        head = tail = NULL;
    else
        head = head->next;

    delete temp;
}

Node* midNode(Node* head, Node* tail)//O(n)
{
    Node* slow = head;
    Node* fast = head;
    while(fast != tail && fast->next != tail)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

void merge2SortedLists(Node* oh, Node* sh, Node*& nh, Node*& nt)
{
    while(oh != NULL && sh != NULL)
    {    
        if(oh->data < sh->data)
        {
            addLast(nh, nt, oh->data);
            oh = oh->next;
            // removeFirst(oh) //isse b kam ho jayga pr vo list khtm ho jaygi
            // iska use mergesort me memory leak rokne k liye kr skte h
        }
        else
        {
            addLast(nh, nt, sh->data);
            sh = sh->next;
        }
    }

    while(sh != NULL)
    {
        addLast(nh, nt, sh->data);
        sh = sh->next;
    }

    while(oh != NULL)
    {
        addLast(nh, nt, oh->data);
        oh = oh->next;
    }

}

void clear(Node*& head, Node*& tail)
{
    for(Node* itr = head; itr != tail;)
    {
        Node* temp = itr;
        itr = itr->next;
        delete temp;
    }
    delete tail;
    head = tail = NULL;
}

void mergeSort(Node*& head, Node*& tail)
{
    if(head == tail)
    {
        // head->next = NULL;//isse original list me changes aa jayenge
        Node* nn = new Node(head->data, NULL);//isse original list preserve rahegi
        head = tail = nn;
        return;
    }

    Node* mid = midNode(head, tail);

    Node* lh = head;
    Node* lt = mid;
    Node* rh = mid->next;
    Node* rt = tail;

    mergeSort(lh, lt);
    mergeSort(rh, rt);
    
    Node* fh = NULL;
    Node* ft = NULL;

    merge2SortedLists(lh, rh, fh, ft);

    clear(lh, lt);//to remove memory leak kyuki m2sl fxn nayi list bana k deta h to purani list
    clear(rh, rt);//ka kya kaam
    head = fh;
    tail = ft;
}

void inplacem2sl(Node* lh, Node* lt, Node* rh, Node* rt, Node*& oh, Node*& ot)
{
    Node* t1 = lh;
    Node* t2 = rh;
    while(t1 != NULL && t2 != NULL)
    {
        Node* rm = NULL;//node to be removed
        if(t1->data < t2->data)
        {
            rm = t1;
            t1 = t1->next;
            rm->next = NULL;
        }
        else
        {
            rm = t2;
            t2 = t2->next;
            rm->next = NULL;
        }

        if(oh == NULL)
            oh = ot = rm;
        else
        {
            ot->next = rm;
            ot = rm;
        }
    }

    if(t1 != NULL)
    {
        ot->next = t1;
        ot = lt;
    }
    else
    {
        ot->next = t2;
        ot = rt;
    }
}

void inplacems(Node*& head, Node*& tail)
{
    if(head == tail)
        return;
        
    Node* mid = midNode(head, tail);

    Node* lh = head;
    Node* lt = mid;
    Node* rh = mid->next;
    Node* rt = tail;

    mid->next = NULL;
    inplacems(lh, lt);
    inplacems(rh, rt);

    Node* fh = NULL;
    Node* ft = NULL;
    inplacem2sl(lh, lt, rh, rt, fh, ft);
    head = fh;
    tail = ft;
}

int main(int argc, char** argv)
{
    // Node* oh = NULL;
    // Node* ot = NULL;
    // Node* sh = NULL;
    // Node* st = NULL;

    Node* head = NULL;
    Node* tail = NULL;

    addLast(head, tail, 20);
    addLast(head, tail, 10);
    // addLast(oh, ot, 30);
    // addLast(oh, ot, 40);

    addLast(head, tail, 15);
    addLast(head, tail, 35);
    addLast(head, tail, 25);
    addLast(head, tail, 11);
    addLast(head, tail, 65);
    addLast(head, tail, 17);
    addLast(head, tail, 85);
    addLast(head, tail, 55);

    // merge2SortedLists(oh, sh, head, tail);
    // display(head);
    Node* sh = head;
    Node* st = tail;
    // mergeSort(sh, st);
    display(head);
    // display(sh);

    inplacems(sh, st);
    display(sh);
}