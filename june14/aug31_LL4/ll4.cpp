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

int size(Node* head)
{
    int size = 0;
    for(Node* i = head; i != NULL; i = i->next)
        size++;
    return size;
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

int midNode(Node* head)//O(n)
{
    Node* slow = head;
    Node* fast = head;
    while(fast->next != NULL && fast->next->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow->data;
}

int kthfromLast(Node* head, int k)
{
    Node* slow = head;
    Node* fast = head;
    for(int i = 0; i < k; i++)
        fast = fast->next;

    while(fast != NULL)
    {
        slow = slow->next;
        fast = fast->next;
    }

    return slow->data;
}

Node* krevHelp(Node* th, int k)
{
    Node* prev = NULL;
    Node* curr = th;
    for(int i = 0; i < k; i++)
    {
        Node* next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }

    th->next = curr;
    return prev;
}

void kreverse(Node*& head, Node*& tail, int k)
{
    int sz = size(head);
    if(k > sz)
        return;

    //list is abcdefghijk
    int counter = 0;
    Node* t1 = head;//a
    Node* t2 = krevHelp(t1, k);//c
    head = t2;//c
    counter += k;
    while(sz - counter >= k)
    {
        Node* nt1 = t1->next;//d
        Node* nt2 = krevHelp(nt1, k);//f
        t1->next = nt2;// cbaf
        t1 = nt1;//d
        counter += k;
    }

    if(counter == sz)
        tail = t1;

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

void addFirst(Node*& head, Node*& tail, int data)
{
    if(head == NULL)
        head = tail = new Node(data, NULL);
    else
    {
        Node* nn = new Node(data, head);
        head = nn;
    }
}

void kreverse2(Node*& head, Node*& tail, int k)
{
    int sz = size(head);
    if(k > sz)
        return;

    Node* phead = NULL;
    Node* ptail = NULL;
    while(head != NULL && sz >= k)
    {
        Node* chead = NULL;
        Node* ctail = NULL;

        for(int i = 0; i < k; i++)
        {
            int temp = head->data;
            removeFirst(head, tail);
            addFirst(chead, ctail, temp);
            sz--;
        }

        if(phead == NULL)
        {
            phead = chead;
            ptail = ctail;
        }
        else
        {
            ptail->next = chead;
            ptail = ctail;
        }
    }

    if(head == NULL)
    {
        head = phead;
        tail = ptail;
    }
    else
    {
        ptail->next = head;
        head = phead;   
    }

}

void oddEven(Node*& head, Node*& tail)
{
    Node* ohead = NULL;
    Node* otail = NULL;
    Node* ehead = NULL;
    Node* etail = NULL;

    while(head != NULL)
    {
        int temp = head->data;
        removeFirst(head, tail);
        if(temp % 2 == 0)
            addLast(ehead, etail, temp);
        else
            addLast(ohead, otail, temp);
    }

    if(ohead == NULL)
    {
        head = ehead;
        tail = etail;
    }
    else if(ehead == NULL)
    {
        head = ohead;
        tail = otail;
    }
    else
    {
        otail->next = ehead;
        head = ohead;
        tail = etail;
    }
}

void removeDuplicates(Node*& head, Node*& tail)//sorted list milegi
{
    Node* nhead = NULL;
    Node* ntail = NULL;

    while(head != NULL)
    {
        int temp = head->data;
        removeFirst(head, tail);
        if(nhead == NULL || ntail->data != temp)
            addLast(nhead, ntail, temp);
    }

    head = nhead;
    tail = ntail;
}

int main(int argc, char** argv)
{
    Node* head = NULL;
    Node* tail = NULL;
    addLast(head, tail, 10);
    addLast(head, tail, 20);
    addLast(head, tail, 30);
    addLast(head, tail, 40);
    addLast(head, tail, 50);
    addLast(head, tail, 60);
    addLast(head, tail, 70);
    addLast(head, tail, 80);
    addLast(head, tail, 90);
    // cout << midNode(head) << endl;
    // cout << kthfromLast(head, 3) << endl;
    // kreverse(head, tail, 3);
    // kreverse2(head, tail, 3);
    // addLast(head, tail, 100);
    // oddEven(head, tail);
    // removeDuplicates(head, tail);
    display(head);


}