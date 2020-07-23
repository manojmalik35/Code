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

//my method
void floydCycle(Node* head)
{
    Node* slow = head;
    Node* fast = head;
    do
    {
        slow = slow ->next;
        fast = fast->next->next;
    }while(fast != slow && fast != NULL && fast->next != NULL);

    if(slow != fast)
    {
        cout << "No cycle" << endl;
        return;
    }
    else
    {
        cout << "Cycle detected" << endl;
        if(slow == head)//Self loop
        {
            while(slow->next != head)
                slow = slow->next;
        }
        else
        {
            fast = head;
            while(slow->next != fast->next)
            {
                fast = fast->next;
                slow = slow->next;
            }
        }

        slow->next = NULL;//cycle removed
    }
}

void detectandRemoveLoop(Node*& head)
{
    Node* slow = head;
    Node* fast = head;
    cout<<"loop detection"<<endl;
    while(fast != NULL && fast->next != NULL)
    {
        slow = slow ->next;
        fast = fast->next->next;
        if(slow == fast)
        {
            cout << "Cycle detected" << endl;
            if(slow == head)//Self loop
            {
                while(slow->next != head)
                    slow = slow->next;
            }
            else
            {
                fast = head;
                while(slow->next != fast->next)
                {
                    fast = fast->next;
                    slow = slow->next;
                }
            }

            slow->next = NULL;//cycle removed
            display(head);
            break;
        }
    }

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
    addLast(head, tail, 100);
    tail->next = getNodeAt(head, 5);
    

    // floydCycle(head);
    // display(head);
    detectandRemoveLoop(head);
    // display(head);
}