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