#include <iostream>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *getMid(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;
    while (fast != nullptr && fast->next != nullptr)
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

bool isPalindrome(ListNode *head) //leetcode 234
{
    ListNode *mid = getMid(head);
    ListNode *nhead = reverseItr(mid);
    while (nhead != nullptr && head != nhead) //1st condin even k liye or 2nd odd k liye
    {
        if (head->val != nhead->val)
            return false;
        head = head->next;
        nhead = nhead->next;
    }

    return true;
}