#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

void addLast(ListNode *&head, ListNode *&tail, int &sz, ListNode *node)
{
    if (sz == 0)
    {
        head = node;
        tail = node;
    }
    else
    {
        tail->next = node;
        tail = node;
    }
    sz++;
}

ListNode *removeFirst(ListNode *&head, ListNode *&tail, int &sz)
{
    if (sz == 0)
        return nullptr;

    ListNode *temp = head;
    if (sz == 1)
    {
        head = nullptr;
        tail = nullptr;
    }
    else
        head = head->next;

    sz--;
    temp->next = nullptr;
    return temp;
}

ListNode *rotateRight(ListNode *head, int k)
{
    if(head == nullptr)
        return head;
    ListNode *tail = head;
    int sz = 0;
    ListNode *temp = head;
    while (temp != nullptr)
    {
        tail = temp;
        sz++;
        temp = temp->next;
    }

    k = k % sz;
    if (k < 0)
        k += sz;

    tail->next = head;
    temp = head;
    for (int i = 0; i < sz - k - 1; i++)
        temp = temp->next;

    head = temp->next;
    temp->next = nullptr;
    return head;
}

ListNode *rotateRight2(ListNode *head, int k) //2nd method
{
    if(head == nullptr)
        return head;
    ListNode *tail = head;
    int sz = 0;
    ListNode *temp = head;
    while (temp != nullptr)
    {
        tail = temp;
        sz++;
        temp = temp->next;
    }

    k = k % sz;
    if (k < 0)
        k += sz;

    for(int i = 0; i < sz - k; i++)
    {
        ListNode* temp = removeFirst(head, tail, sz);
        addLast(head, tail, sz, temp);
    }

    return head;
}