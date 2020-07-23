#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

void addFirst(ListNode*& head, ListNode*& tail, int& sz, ListNode*& node)
{
    if(sz == 0)
    {
        head = node;
        tail = node;
    }
    else
    {
        node->next = head;
        head = node;
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

ListNode* reverseKGroup(ListNode* head, int k)//leetcode 25
{
    ListNode* itr = head;
    ListNode* tail = nullptr;
    int sz = 0;
    while(itr != nullptr)
    {
        sz++;
        tail = itr;
        itr = itr->next;
    }

    ListNode* nhead = nullptr;
    ListNode* ntail = nullptr;
    int nsz = 0;
    while(k <= sz)
    {
        ListNode* thead = nullptr;
        ListNode* ttail = nullptr;
        int tsz = 0;
        for(int i = 0; i < k; i++)
        {
            ListNode* removed = removeFirst(head, tail, sz);
            addFirst(thead, ttail, tsz, removed);           
        }

        if(nhead == nullptr)
        {
            nhead = thead;
            ntail = ttail;
        }
        else
        {
            ntail->next = thead;
            ntail = ttail;
        }
        nsz += tsz;
    }

    ntail->next = head;
    return nhead;
}