package linkUtilTest;

import com.example.demo.domain.StudentInfo;
import com.example.demo.linkUtils.time.ClockUtil;
import com.example.demo.linkUtils.time.DateUtil;

import java.time.Clock;
import java.util.Date;

/**
 * <p>Description: </p>
 * <p>@date 2021/12/21 10:52</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class Test1 {
    public static void main(String[] args) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId("id");

        StudentInfo studentInfo2 = null;
        studentInfo2 = studentInfo;


        System.out.println(ClockUtil.elapsedTime(System.currentTimeMillis()));
        System.out.println(ClockUtil.useDummyClock());
        System.out.println(ClockUtil.currentDate());
        System.out.println(DateUtil.isSameDay(new Date(), new Date()));
        System.out.println(DateUtil.isSameTime(new Date(), new Date()));
    }


}