#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *removeNthFromEnd(ListNode *head, int n)//leetcode 19
{
    if(n == 0 || head == nullptr) 
        return head;
    ListNode* slow = head;
    ListNode* fast = head;
    while(n-- > 0)
        fast = fast->next;

    if(fast == nullptr)
        return head->next;

    while(fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next;
    }

    slow->next = slow->next->next;
    return head;
}