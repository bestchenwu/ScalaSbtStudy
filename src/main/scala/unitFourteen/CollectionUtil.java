package unitFourteen;

import java.io.IOException;
import unitFourteen.Printer;
public class CollectionUtil {
    public static int countSum(java.util.List<Integer> list){
        int sum =0;
        for(int i : list){
            sum+=i;
        }
        return sum;
    }

    public static void main(){
        Printer printer = new Printer();
        printer.print("test","haha");
        try{
            JavaInteraction.decode();
        }catch(IOException e){

        }

    }
}
