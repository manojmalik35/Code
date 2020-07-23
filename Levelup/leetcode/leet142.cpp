#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

bool hasCycle(ListNode *head) //leetcode 141
{
    if (head == nullptr || head->next == nullptr)
        return false;
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast)
            return true;
    }

    return false;
}

ListNode *detectCycle(ListNode *head)//leetcode 142
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

    if(fast == nullptr || fast->next == nullptr)
        return nullptr;

    if(slow == head)
        return head;
    
    fast = head;
    while(slow != fast)
    {
        slow = slow->next;
        fast = fast->next;
    }

    return slow;
}