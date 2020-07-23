import java.util.*;

public class dupProducts{

    static class Product{
        String name;
        int wt;
        int price;

        Product(String name, int wt, int price){
            this.name = name;
            this.wt = wt;
            this.price = price;
        }

        void display(){
            System.out.println(this.name + " " + this.wt + " " + this.price);
        }

        public int hashCode(){
            return name.length() + wt + price;
        }

        public boolean equals(Object o){
            Product p = (Product)o;
            return this.name.equals(p.name) && this.wt == p.wt && this.price == p.price;
        }
    }

    static int solve(String[] names, int[] wts, int[] prices){
        int c = 0;
        HashMap<Product, Integer> fmap = new HashMap<>();
        for(int i = 0; i < names.length; i++){
            Product p = new Product(names[i], wts[i], prices[i]);
            if(fmap.containsKey(p))
                fmap.put(p, fmap.get(p) + 1);
            else
                fmap.put(p, 1);
        }

        ArrayList<Product> keys = new ArrayList<>(fmap.keySet());
        for(int i = 0; i < keys.size(); i++){
            Product key = keys.get(i);
            if(fmap.get(key) > 1)
                c++;
        }

        return c;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] wts = new int[n];
        int[] prices = new int[n];
        for(int i = 0; i < n; i++)
            names[i] = sc.next();

        for(int i = 0; i < n; i++)
            wts[i] = sc.nextInt();

        for(int i = 0; i < n; i++)
            prices[i] = sc.nextInt();

        sc.close();
        int count = solve(names, wts, prices);
        System.out.println(count);
    }
}