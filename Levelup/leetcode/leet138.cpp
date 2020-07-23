#include <iostream>

using namespace std;

class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

Node *copyRandomList(Node *head)
{
    //Step 1 : create a copy of each node and place it in front of each node
    Node* curr = head;
    while(curr != nullptr)
    {
        Node* forw = curr->next;
        Node* newNode = new Node(curr->val);
        curr->next = newNode;
        newNode->next = forw;
        curr = forw;
    }

    curr = head;
    while(curr != nullptr)
    {
        if(curr->random != nullptr)
            curr->next->random = curr->random->next;
        curr = curr->next->next;
    }

    curr = head;
    Node* dummy = new Node(-1);
    Node* ncurr = dummy;
    while(curr != nullptr)
    {
        Node* copied = curr->next;
        curr->next = copied->next;
        ncurr->next = copied;
        ncurr = copied;
        curr = curr->next;
    }

    Node* nhead = dummy->next;
    delete dummy;
    return nhead;
}