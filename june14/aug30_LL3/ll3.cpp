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

bool isPalindrome(Node*& left, Node* right)
//floor or size isliye ni liya kyuki palindrome me 2 baar check ho b gya to koi dikkat ni h
{
    if(right == NULL)
        return true;

    bool rres = isPalindrome(left, right->next);
    rres = rres && left->data == right->data;
    left = left->next;
    return rres;
}

void fold(Node*& left, Node* right, int floor, int& size, Node*& tail)
{
    if(right == NULL)
        return;

    size++;
    fold(left, right->next, floor + 1, size, tail);
    if(floor > size / 2)
    {
        Node* temp = left->next;
        left->next = right;
        right->next = temp;
        left = temp;
    }
    else if(floor == size / 2)
    {
        tail = right;
        tail->next = NULL;
    }
}

//Bonus
void unfold(Node* left)
{
    if(left->next == NULL || left->next->next == NULL)
    {

    }

    Node* right = left->next;
    unfold(left->next->next);

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
    // addLast(head, tail, 70);
    Node* left = head;
    Node* right = head;
    int sz = 0;
    // cout << isPalindrome(left, right) << endl;
    fold(left, right, 0, sz, tail);
    addLast(head, tail, 80);
    display(head);
}