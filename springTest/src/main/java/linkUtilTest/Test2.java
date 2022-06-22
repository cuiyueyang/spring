package linkUtilTest;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Description: </p>
 * <p>@date 2022/3/23 13:50</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class Test2 {
    public static void main(String[] args) {
        String str = "浙江123（钉钉端111）";
        String[] sz = str.split("（");
        System.out.println(sz[0] + sz[1]);
        int num = StringUtils.ordinalIndexOf(str, "（", 1);
        System.out.println(num);
        System.out.println(str.substring(0, num));
        System.out.println(str.substring(num+1, str.length()-1));
    }
}
