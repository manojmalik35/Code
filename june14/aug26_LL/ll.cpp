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

void display(Node* head)
{
    for(Node* i = head; i != NULL; i = i->next)
        cout << i->data << " ";
    cout << endl;
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

int getFirst(Node* head)
{
    if(head == NULL)
        return 0;
    return head->data;
}

int getLast(Node* tail)
{
    if(tail== NULL)
        return 0;
    return tail->data;
}

int getAt(Node* head, int idx)
{
    if(head == NULL)
        return 0;
    
    int j = 0;
    for(Node* i = head; i != NULL; i = i->next)
    {
        if(j == idx)
            return i->data;
        j++;
    }
    return 0;
}

Node* getNodeAt(Node* head, int idx)
{
    if(head == NULL)
        return NULL;
    
    int j = 0;
    for(Node* i = head; i != NULL; i = i->next)
    {
        if(j == idx)
            return i;
        j++;
    }
    return NULL;
}

int size(Node* head)
{
    int size = 0;
    for(Node* i = head; i != NULL; i = i->next)
        size++;
    return size;
}

void addAt(Node*& head, Node*& tail, int data, int idx)
{
    int sz = size(head);
    if(idx < 0 || idx > sz)
        cout << "Invalid index" << endl;
    else if(idx == 0)
        addFirst(head, tail, data);
    else if(idx == sz)
        addLast(head, tail, data);
    else
    {
        Node* temp = getNodeAt(head, idx - 1);
        temp->next = new Node(data, temp->next);
    }
}

int main(int argc, char** argv)
{
    Node* head = NULL;
    Node* tail = NULL;
    addAt(head, tail, 100, 0);
    addFirst(head, tail, 70);
    addLast(head, tail, 10);
    addLast(head, tail, 20);
    addLast(head, tail, 30);
    addLast(head, tail, 40);
    addLast(head, tail, 50);
    addFirst(head, tail, 60);
    addAt(head, tail, 1000, 8);
    addAt(head, tail, 200, 3);
    display(head);
}