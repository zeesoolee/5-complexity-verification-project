package snippet_splitter_out.ds_1;
public class ds_1_snip_9_main9 {
public static void main9(String[] args){
        int number = 11;
        boolean result = true;
        for(int i = 2; i < number; i++) {
            if(number % i == 0) {
                result = false;
                break;
            }
        }
        System.out.println(result);
    }
}