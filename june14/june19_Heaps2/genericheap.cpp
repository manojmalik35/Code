#include<iostream>
#include<vector>
#include<string>
using namespace std;

template<class T>
class heap{
    private:
        vector<T> list;
        bool (*comptor)(const T& o1,const T& o2)=NULL;

        void swap(int i,int j)
        {
            T ith=list[i];
            T jth=list[j];
            list[i]=jth;
            list[j]=ith;
        }
        bool ishp(int i,int j)
        {
            if(this->comptor==NULL)
                return list[j]<list[i];//bcoz in cpp default is maxheap
            else
                return comptor(list[i],list[j]);
        }
        void upheapify(int i)
        {
            if(i==0)
                return;
            int pi=(i-1)/2;
            if(ishp(i,pi)){
                swap(i,pi);
                upheapify(pi);
            }
        }
        void downheapify(int i)
        {
            int li=2*i+1;
            int ri=2*i+2;
            int hpi=i;
            if(li<list.size() && ishp(li,hpi))
                hpi=li;
            if(ri<list.size() && ishp(ri,hpi))
                hpi=ri;

            if(hpi!=i)
            {
                swap(i,hpi);
                downheapify(hpi);
            }
        }
    public:
        heap()
        {
        }
        heap(bool (*comptor)(const T& o1,const T& o2))
        {
            this->comptor=comptor;
        }
        heap(vector<T>& arr)
        {
            for(int i=0;i<arr.size();i++)
                list.push_back(arr[i]);

            for(int i=list.size()-1;i>=0;i--)
                downheapify(i);
        }
        void push(T val)
        {
            list.push_back(val);
            upheapify(list.size()-1);
        }
        void pop()
        {
            swap(0,list.size()-1);
            list.pop_back();
            downheapify(0);
        }
        T top()
        {
            return list[0];
        }
        int size()
        {
            return list.size();
        }
};
class Student
{
    public:
        string name;
        int marks;
        int extra;
        Student(string name,int marks,int extra)
        {
            this->name=name;
            this->marks=marks;
            this->extra=extra;
        }
        void display()
        {
            cout<<"["<<this->name<< " ," <<this->marks<< " ," <<this->extra<< " ]" <<endl;
        }
        bool operator<(const Student& o)const
        {
            return this->name.compare(o.name)<0;
        }
};
bool StudentMarksComparator(const Student& o1,const Student& o2)
{
    return o2.marks<o1.marks;
}
bool StudentExtraComparator(const Student& o1,const Student& o2)
{
    return o2.extra<o1.extra;
}
bool StudentMarksExtraComparator(const Student& o1,const Student& o2)
{
    if(o1.marks!=o2.marks)
        return o2.marks<o1.marks;
    else
        return o2.extra<o1.extra;
}
bool StudentExtraMarksComparator(const Student& o1,const Student& o2)
{
    if(o1.extra!=o2.extra)
        return o2.extra<o1.extra;
    else
        return o2.marks<o1.marks;
}
int main(int argc,char** argv)
{
    // heap<Student> pq(StudentMarksExtraComparator);
    // heap<Student> pq(&StudentExtraComparator);//& is a choice bcoz vse b address hi pass hota h function ka
    // pq.push(Student("D",10,2));
    // pq.push(Student("C",5,5));
    // pq.push(Student("E",8,8));
    // pq.push(Student("A",2,10));
    // pq.push(Student("B",5,2));

    // while(pq.size()>0)
    // {
    //     Student val=pq.top(); pq.pop();
    //     val.display();
    // }
    vector<int> arr {2,3,6,8,4,5,1,20,8};
        heap<int> pq(arr);
        while(pq.size()>0){
            int val=pq.top(); pq.pop();
            cout<<val<< " ";
        }
}