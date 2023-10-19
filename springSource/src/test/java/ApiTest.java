import bean.UserService;
import com.example.day1.BeanDefinition;
import com.example.day1.BeanFactory;
import org.junit.Test;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/19 14:05</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class ApiTest {

    @Test
    public void Test1() {
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("userService", beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
