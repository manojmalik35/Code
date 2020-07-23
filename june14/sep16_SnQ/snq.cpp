#include<iostream>
#include<stack>
#include<cmath>
#include<vector>
#include<algorithm>

using namespace std;

int getPrior(char ch)
{
    switch(ch)
    {
        case '+' :
        case '-' :
            return 1;
        case '*' :
        case '/' :
        case '%' :
            return 2;
        case '^' :
            return 3;
        default :
            cout << "Wrong operator" << endl;
            break;
    }
}

int getValue(int v1, int v2, char op)
{
    switch(op)
    {
        case '+' :
            return v1 + v2;
        case '-' :
            return v1 - v2;
        case '*' :
            return v1 * v2;
        case '/' :
            return v1 / v2;
        case '%' :
            return v1 % v2;
        case '^' :
            return pow(v1, v2);
        default :
            cout << "Wrong operator" << endl;
            break;
    }
}

int infix123(string exp)
{
    stack<int> vs;
    stack<int> os;
    stack<string> post;
    stack<string> pre;

    for(int i = 0; i < exp.length(); i++)
    {
        char ch = exp[i];
        if(ch >= '0' && ch <= '9')//operand
        {
            vs.push(ch - '0');
            post.push(to_string(ch - '0'));
            pre.push(to_string(ch - '0'));
        }
        else if(ch == '(')
            os.push(ch);
        else if(ch == ')')
        {
            while(os.top() != '(')
            {
                int v2 = vs.top(); vs.pop();
                int v1 = vs.top(); vs.pop();
                char op = os.top(); os.pop();
                int res = getValue(v1, v2, op);
                vs.push(res);

                string postv2 = post.top(); post.pop();
                string postv1 = post.top(); post.pop();
                post.push(postv1 + postv2 + op);

                string prev2 = pre.top(); pre.pop();
                string prev1 = pre.top(); pre.pop();
                pre.push(op + prev1 + prev2);
            }
            os.pop();
        }
        else//operator
        {
            while(os.size() > 0 && os.top() != '(' && getPrior(os.top()) >= getPrior(ch))
            {
                int v2 = vs.top(); vs.pop();
                int v1 = vs.top(); vs.pop();
                char op = os.top(); os.pop();
                int res = getValue(v1, v2, op);
                vs.push(res);

                string postv2 = post.top(); post.pop();
                string postv1 = post.top(); post.pop();
                post.push(postv1 + postv2 + op);

                string prev2 = pre.top(); pre.pop();
                string prev1 = pre.top(); pre.pop();
                pre.push(op + prev1 + prev2);
            }
            os.push(ch);
        }
    }

    while(os.size() > 0)
    {
        int v2 = vs.top(); vs.pop();
        int v1 = vs.top(); vs.pop();
        char op = os.top(); os.pop();
        int res = getValue(v1, v2, op);
        vs.push(res);

        string postv2 = post.top(); post.pop();
        string postv1 = post.top(); post.pop();
        post.push(postv1 + postv2 + op);

        string prev2 = pre.top(); pre.pop();
        string prev1 = pre.top(); pre.pop();
        pre.push(op + prev1 + prev2);
    }

    cout << "Post order => " << post.top() << endl;
    cout << "Pre order => " << pre.top() << endl;
    return vs.top();
}

int postfix123(string exp)
{
    stack<int> vs;
    stack<string> pre;
    stack<string> in;

    for(int i = 0; i < exp.length(); i++)
    {
        char ch = exp[i];
        if(ch >= '0' && ch <= '9')
        {
            vs.push(ch - '0');
            pre.push(to_string(ch - '0'));
            in.push(to_string(ch - '0'));
        }
        else
        {
            int v2 = vs.top(); vs.pop();   
            int v1 = vs.top(); vs.pop();   
            vs.push(getValue(v1, v2, ch));

            string prev2 = pre.top(); pre.pop();
            string prev1 = pre.top(); pre.pop();
            pre.push(ch + prev1 + prev2);

            string inv2 = in.top(); in.pop();
            string inv1 = in.top(); in.pop();
            in.push("(" + inv1 + ch + inv2 + ")");
        }
    }

    cout << "Preorder => " << pre.top() << endl;
    cout << "Inorder => " << in.top() << endl;
    return vs.top();
}

int prefix123(string exp)
{
    stack<int> vs;
    stack<string> post;
    stack<string> in;

    for(int i = exp.length() - 1; i >= 0; i--)
    {
        char ch = exp[i];
        if(ch >= '0' && ch <= '9')
        {
            vs.push(ch - '0');
            post.push(to_string(ch - '0'));
            in.push(to_string(ch - '0'));
        }
        else
        {
            int v1 = vs.top(); vs.pop();   
            int v2 = vs.top(); vs.pop();   
            vs.push(getValue(v1, v2, ch));

            string post1 = post.top(); post.pop();
            string post2 = post.top(); post.pop();
            post.push(post1 + post2 + ch);

            string inv1 = in.top(); in.pop();
            string inv2 = in.top(); in.pop();
            in.push("(" + inv1 + ch + inv2 + ")");
        }
    }

    cout << "Postorder => " << post.top() << endl;
    cout << "Inorder => " << in.top() << endl;
    return vs.top();
}

class Interval
{
    public:
        int start;
        int end;

        Interval(int start, int end)
        {
            this->start = start;
            this->end = end;
        }

        void display()
        {
            cout << this->start << "-" << this->end << endl;
        }

        bool operator<(const Interval& other) const
        {
            return this->start < other.start;
        }
};

void mergeOverlappingIntervals(vector<int>& starts, vector<int>& ends)
{
    vector<Interval> intvs;
    for(int i = 0; i < starts.size(); i++)
    {
        Interval temp (starts[i], ends[i]);
        intvs.push_back(temp);
    }

    sort(intvs.begin(), intvs.end());

    stack<Interval> st;
    st.push(intvs[0]);
    for(int i = 1; i < intvs.size(); i++)
    {
        if(intvs[i].start > st.top().end)
            st.push(intvs[i]);
        else
            st.top().end = max(st.top().end, intvs[i].end);
    }

    while(st.size() > 0)
    {
        Interval t = st.top(); st.pop();
        t.display();
    }
}

int main(int argc, char** argv)
{
    // string in = "3-2*4/1+3^2";
    // cout << infix123(in) << endl;
    // string post = "324*1/-32^+";
    // cout << postfix123(post) << endl;
    // string pre = "+-3/*241^32";
    // cout << prefix123(pre) << endl;

    vector<int> starts {1, 6, 5, 2};
    vector<int> ends {3, 8, 7, 4};
    mergeOverlappingIntervals(starts, ends);
}
