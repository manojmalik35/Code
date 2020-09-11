#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *mergeTwoLists(ListNode *A, ListNode *B) //leetcode 21
{
    if (A == nullptr)
        return B;
    else if (B == nullptr)
        return A;

    ListNode *dummy = new ListNode(-1);
    ListNode *curr = dummy;
    while (A != nullptr && B != nullptr)
    {
        if (A->val <= B->val)
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

    if (A != nullptr)
        curr->next = A;
    else
        curr->next = B;

    ListNode *head = dummy->next;
    delete dummy;
    return head;
}

ListNode *mergeKLists(vector<ListNode *> &lists, int si, int ei)
{
    if (si == ei)
        return lists[si];

    if (si + 1 == ei)
        return mergeTwoLists(lists[si], lists[ei]);

    int mid = (si + ei) >> 1;
    ListNode *left = mergeKLists(lists, si, mid);
    ListNode *right = mergeKLists(lists, mid + 1, ei);

    return mergeTwoLists(left, right);
}

ListNode *mergeKLists(vector<ListNode *> &lists) //leetcode 23
{
    if (lists.size() == 0)
        return nullptr;
    return mergeKLists(lists, 0, lists.size() - 1);
}

ListNode *mergeKLists2(vector<ListNode *> &lists) //leetcode 23
{
    if (lists.size() == 0)
        return nullptr;
    priority_queue<pair<int, ListNode *>, vector<pair<int, ListNode *>>, greater<pair<int, ListNode *>>> pq;

    for (int i = 0; i < lists.size(); i++)
    {
        if (lists[i] != nullptr)
            pq.push({lists[i]->val, lists[i]});
    }

    ListNode *dummy = new ListNode(-1);
    ListNode *curr = dummy;
    while (pq.size() > 0)
    {
        pair<int, ListNode *> t = pq.top();
        pq.pop();

        curr->next = t.second;
        curr = t.second;
        if (t.second->next != nullptr)
        {
            ListNode *nn = t.second->next;
            int val = nn->val;
            pq.push({val, nn});
        }
    }

    ListNode *nhead = dummy->next;
    delete dummy;
    return nhead;
}
