#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *mergeTwoLists(ListNode *A, ListNode *B)//leetcode 21
{
    if(A == nullptr)
        return B;
    else if(B == nullptr)
        return A;
        
    ListNode* dummy = new ListNode(-1);
    ListNode* curr = dummy;
    while(A != nullptr && B != nullptr)
    {
        if(A->val <= B->val)
        {
            curr->next = A;
            curr = A;
            A = A->next;
        }
        else
        {
            curr->next = B;
            curr = B;
            B = B->next;
        }
    }

    if(A != nullptr)
        curr->next = A;
    else
        curr->next = B;

    ListNode* head = dummy->next;
    delete dummy;
    return head;
}

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

ListNode *sortList(ListNode *head)//leetcode 148
{
    if(head == nullptr || head->next == nullptr)
        return head;

    ListNode* mid = getMid2(head);
    ListNode* nhead = mid->next;
    mid->next = nullptr;

    return mergeTwoLists(sortList(head), sortList(nhead));
}