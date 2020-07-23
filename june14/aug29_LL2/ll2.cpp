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

void removeLast(Node*& head, Node*& tail)
{
    if(head == NULL)
        return;

    Node* temp = tail;
    if(head == tail)
        head = tail = NULL;
    else
    {
        int sz = size(head);
        Node* temp = getNodeAt(head, sz - 2);
        temp->next = NULL;
        tail = temp;
    }
    delete temp;
}

void removeAt(Node*& head, Node*& tail, int idx)
{
    int sz = size(head);
    if(idx < 0 || idx >= sz)
        cout << "Invalid index" << endl;
    else if(idx == 0)
        removeFirst(head, tail);
    else if(idx == sz - 1)
        removeLast(head, tail);
    else
    {
        Node* prev = getNodeAt(head, idx - 1);
        Node* temp = prev->next;
        prev->next = temp->next;
        delete temp;
    }
}

void reverseDataIter(Node* head, Node* tail)//O(n^2)
{
    int left = 0;
    int right = size(head) - 1;
    while(left < right)
    {
        Node* l = getNodeAt(head, left);
        Node* r = getNodeAt(head, right);
        int temp = l->data;
        l->data = r->data;
        r->data = temp;
        left++;
        right--;
    }
}

void reversePointerIter(Node*& head, Node*& tail)//O(n)
{
    Node* prev = NULL;
    Node* curr = head;
    tail = curr;
    while(curr != NULL)
    {
        Node* temp = curr->next;
        curr->next = prev;
        prev = curr;
        curr = temp;
    }
    head = prev;
}

void displayReverse(Node* head)//O(n)
{
    if(head == NULL)
        return;
    displayReverse(head->next);
    cout << head->data << " ";
}

void reversePointerRecur(Node* left, Node*& head, Node*& tail)//O(n)
{
    if(left->next == NULL)
    {
        head = left;
        return;
    }

    reversePointerRecur(left->next, head, tail);
    left->next->next = left;
    tail = left;
    tail->next = NULL;
}

void reverseDataRecur(Node*& left, Node* right, int floor, int& size)//O(n)
{
    if(right == NULL)
        return;

    size++;
    reverseDataRecur(left, right->next, floor + 1, size);
    if(floor >= size / 2)
    {
        int temp = left->data;
        left->data = right->data;
        right->data = temp;

        left = left->next;
    }
}

int main(int argc, char** argv)
{
    Node* head = NULL;
    Node* tail = NULL;
    // addAt(head, tail, 100, 0);
    // removeFirst(head, tail);
    // addFirst(head, tail, 70);
    // removeFirst(head, tail);
    addLast(head, tail, 10);
    addLast(head, tail, 20);
    addLast(head, tail, 30);
    // removeAt(head, tail, 2);
    addLast(head, tail, 40);
    addLast(head, tail, 50);
    // addFirst(head, tail, 60);
    // addAt(head, tail, 1000, 8);
    // addAt(head, tail, 200, 3);
    // removeFirst(head, tail);
    // removeLast(head, tail);
    // removeAt(head, tail, 3);
    // reverseDataIter(head, tail);
    // reversePointerIter(head, tail);
    // display(head);
    // displayReverse(head);
    // HelperinReverse(head, tail);
    Node* left = head;
    Node* right = head;
    int sz = 0;
    // reverseDataRecur(left, right, 0, sz);
    reversePointerRecur(left, head, tail);
    display(head);
}