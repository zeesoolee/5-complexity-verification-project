package snippet_splitter_out.ds_1;
public class ds_1_snip_20_main20 {
public static void main20(String[] args) {
        int i=14;
        String result="";

        while (i>0) {
            if (i%2 ==0)
                result="0"+result;
            else
                result="1"+result;
                i=i/2;
        }

        System.out.println(result); }
}