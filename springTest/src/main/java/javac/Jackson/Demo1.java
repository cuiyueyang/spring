package javac.Jackson;

import com.example.demo.domain.StudentInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>@date 2021/12/27 15:56</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class Demo1 {

    public static void main(String[] args) throws JsonProcessingException {
        StudentInfo studentInfo = new StudentInfo();
        List<StudentInfo> list = Lists.newArrayList();
        list.add(studentInfo);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(studentInfo);
        System.out.println(json);
    }


}
