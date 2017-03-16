package DateHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by che2 on 2016/12/29.
 */
public class DataUtils {

    public static void main(String[] args){
        Calendar calendar=Calendar.getInstance();

        Date date=new Date();

        System.out.println(date);

        Date date1=new Date(System.currentTimeMillis());

        System.out.println(date1);

        calendar.setTime(date1);

        calendar.add(Calendar.WEEK_OF_YEAR, +3);

        System.out.println(calendar.getTime());

        calendar.add(Calendar.DAY_OF_MONTH, +2);

        Date time = calendar.getTime();

        System.out.println(time);

        System.out.println(1+2+2+4);

    }

}
