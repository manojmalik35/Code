#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *getMid2(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast->next != nullptr && fast->next->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

ListNode *reverseItr(ListNode *head)
{
    ListNode *prev = nullptr;
    ListNode *curr = head;
    while (curr != nullptr)
    {
        ListNode *forw = curr->next;
        curr->next = prev;
        prev = curr;
        curr = forw;
    }

    return prev;
}

void reorderList(ListNode *head) //leetcode 143
{
    if(head == nullptr)
        return;
    ListNode *mid = getMid2(head);
    ListNode *nhead = reverseItr(mid->next);
    mid->next = nullptr;
    while (nhead != nullptr)
    {
        ListNode *fnext = head->next;
        ListNode *lnext = nhead->next;

        head->next = nhead;
        nhead->next = fnext;

        head = fnext;
        nhead = lnext;
    }
}