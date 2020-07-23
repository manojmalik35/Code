#include <iostream>
#include <vector>
#include <queue>
#include <list>
#include <unordered_map>

using namespace std;

struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Node
{
public:
    int val;
    Node *next;
    Node *random;

    Node(int _val)
    {
        val = _val;
        next = NULL;
        random = NULL;
    }
};

void display(ListNode *head)
{
    while (head != nullptr)
    {
        cout << head->val << " ";
        head = head->next;
    }
    cout << "\n";
}

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

void reorderList(ListNode *head) //leetcode 143
{
    if (head == nullptr)
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

ListNode *rotateRight(ListNode *head, int k) //leetcode 61
{
    if (head == nullptr)
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
    if (head == nullptr)
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

    for (int i = 0; i < sz - k; i++)
    {
        ListNode *temp = removeFirst(head, tail, sz);
        addLast(head, tail, sz, temp);
    }

    return head;
}

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

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) //leetcode 160
{
    if (headA == nullptr || headB == nullptr)
        return nullptr;
    ListNode *temp = headA;
    while (temp->next != nullptr)
        temp = temp->next;

    temp->next = headA;
    ListNode *ans = detectCycle(headB);
    temp->next = nullptr;
    return ans;
}

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

ListNode *sortList(ListNode *head) //leetcode 148
{
    if (head == nullptr || head->next == nullptr)
        return head;

    ListNode *mid = getMid2(head);
    ListNode *nhead = mid->next;
    mid->next = nullptr;

    return mergeTwoLists(sortList(head), sortList(nhead));
}

Node *copyRandomList(Node *head) //leetcode 138
{
    //Step 1 : create a copy of each node and place it in front of each node
    Node *curr = head;
    while (curr != nullptr)
    {
        Node *forw = curr->next;
        Node *newNode = new Node(curr->val);
        curr->next = newNode;
        newNode->next = forw;
        curr = forw;
    }

    curr = head;
    while (curr != nullptr)
    {
        if (curr->random != nullptr)
            curr->next->random = curr->random->next;
        curr = curr->next->next;
    }

    curr = head;
    Node *dummy = new Node(-1);
    Node *ncurr = dummy;
    while (curr != nullptr)
    {
        Node *copied = curr->next;
        curr->next = copied->next;
        ncurr->next = copied;
        ncurr = copied;
        curr = curr->next;
    }

    Node *nhead = dummy->next;
    delete dummy;
    return nhead;
}

ListNode *removeNthFromEnd(ListNode *head, int n) //leetcode 19
{
    if (n == 0 || head == nullptr)
        return head;
    ListNode *slow = head;
    ListNode *fast = head;
    while (n-- > 0)
        fast = fast->next;

    if (fast == nullptr)
        return head->next;

    while (fast->next != nullptr)
    {
        slow = slow->next;
        fast = fast->next;
    }

    slow->next = slow->next->next;
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

ListNode* oddEvenList(ListNode* head) //leetcode 328
{
    if(head == nullptr) return head;
    ListNode* ohead = head;
    ListNode* ehead = head->next;
    ListNode* temp = ehead;
    while(ohead != nullptr && ehead != nullptr)
    {
        ListNode* nov = ehead->next; //next odd value
        ohead->next = nov;
        if(nov != nullptr)
            ehead->next = nov->next;
        if(ohead->next != nullptr)
            ohead = ohead->next;
        ehead = ehead->next;
    }

    ohead->next = temp;
    return head;
}

ListNode* oddEvenByValues(ListNode* head)
{
    ListNode* ohead = new ListNode(-1);
    ListNode* ehead = new ListNode(-1);
    ListNode* oitr = ohead;
    ListNode* eitr = ehead;

    while(head != nullptr)
    {
        if((head->val & 1) == 0)//even
        {
            eitr->next = head;
            eitr = head;
        }
        else
        {
            oitr->next = head;
            oitr = head;
        }

        head = head->next;
    }

    eitr->next = nullptr;
    oitr->next = ehead->next;
    ListNode* ans = ohead->next;
    delete ohead;
    delete ehead;
    return ans;
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

Node* flatten(Node* head)//leetcode 430
{
    return head;
}

//LRU Cache leetcode 146
class LRUCache {
private:
    int capacity;
    list<int>* ll;//key
    unordered_map<int, int>* map;//key,value
public:
    LRUCache(int capacity)
    {
        this->capacity = capacity;
        this->ll = new list<int>();
        this->map = new unordered_map<int, int>();
    }
    
    int get(int key)
    {
        int rv = -1;
        if(this->map->count(key) != 0)//present
        {
            rv = this->map->at(key);
            this->ll->remove(key);//not O(1)
            this->ll->push_front(key);
        }
        
        return rv;    
    }
    
    void put(int key, int value)
    {
        if(this->map->count(key) != 0)//present
            this->ll->remove(key);//not O(1)
        else if(this->ll->size() == this->capacity)
        {
            int removed = this->ll->back();
            this->ll->pop_back();
            this->map->erase(removed);
        }
        
        this->ll->push_front(key);
        (*this->map)[key] = value;
    }
};

int main()
{
    ListNode *one = new ListNode(1);
    ListNode *two = new ListNode(2);
    ListNode *three = new ListNode(3);
    ListNode *four = new ListNode(4);
    ListNode *five = new ListNode(5);
    

    one->next = two;
    two->next = three;
    three->next = four;
    four->next = five;
    // reorderList(one);
    // one = rotateRight(one, 2);
    // one = rotateRight2(one, 2);
    // one = oddEvenByValues(one);
    one = reverseKGroup(one, 2);
    display(one);

    delete one;
    delete two;
    delete three;
    delete four;
    delete five;
}