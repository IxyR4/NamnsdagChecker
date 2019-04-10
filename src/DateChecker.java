import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {

    public static boolean valDOB(String DOB){
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date BOD = null;
        df.setLenient(false);


        try {
            BOD = df.parse(DOB);
            return true;
        }
        catch (Exception e) {
        return false;
        }

    }


}
