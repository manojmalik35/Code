#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *detectCycle(ListNode *head) //leetcode 142
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            break;
    }

    if (fast == nullptr || fast->next == nullptr)
        return nullptr;

    if (slow == head)
        return head;

    fast = head;
    while (slow != fast)
    {
        slow = slow->next;
        fast = fast->next;
    }

    return slow;
}

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)//leetcode 160
{
    if(headA == nullptr || headB == nullptr)
        return nullptr;
    ListNode *temp = headA;
    while(temp->next != nullptr)
        temp = temp->next;

    temp->next = headA;
    ListNode *ans = detectCycle(headB);
    temp->next = nullptr;
    return ans;
}