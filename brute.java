import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;


public class brute{
    static void print_array(int arr[],int n)  
    {  
        int [][] board = new int[n][n];
        for (int i = 0; i < n; i++)  
        {  
            for (int j = 0; j < n; j++)  
            {
                if(arr[i] == j)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }      
        }

        for (int i = 0; i < n; i++)  
        {  
            for (int j = 0; j < n; j++)  
                System.out.printf(" %d ", board[i][j]);  
            System.out.printf("\n");  
        }  
        System.out.printf("\n");  
    }

    static String toString(int[] arr){
        String r = "{ ";
        for(int i = 0; i < arr.length - 1; i++){
            r += arr[i] + " , ";
        }
        return  r + arr[arr.length - 1] + " }";
    }

    static int[] copyFromSet(LinkedHashSet<Integer> perm){
        return perm.stream()
            .mapToInt(
                Integer::intValue
            ).toArray();
    }

    static void removeLastElement(LinkedHashSet<Integer> perm){
        //removes last element in linked list
        Iterator<Integer> i = perm.iterator();
        for(; i.hasNext(); i.next());
        i.remove();
    }

    static void generate(List <int[]>permList, LinkedHashSet<Integer> perm, int n){
        //generates arrays with all the possible numbers and saves it to linked list
        if(perm.size() == n) {
            permList.add(copyFromSet(perm));
            return;
        }
        for(int i = 0; i < n; i++){
            if(!perm.contains(i)) {
                perm.add(i);
                generate(permList, perm, n);
                removeLastElement(perm);
            }
        }
    }

    static boolean is_safe(int []perm){
        //https://www.coursera.org/lecture/what-is-a-proof/n-queens-brute-force-search-optional-OPyaT
        for(int i = 0; i < perm.length - 1; i++) {
            for(int j = i + 1; j < perm.length; j++){
                if(Math.abs(i - j) == Math.abs(perm[i] - perm[j])){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        int n = 8;
        int sol = 1;
        var permutations = new LinkedList<int[]>();
        generate(permutations, new LinkedHashSet<Integer>(), n);
        for(int []arr : permutations) {
            if(is_safe(arr)) {
                System.out.printf("%d-\n", sol++);  
                System.out.println(toString(arr));
                print_array(arr,n);
            }
        }
    }

}