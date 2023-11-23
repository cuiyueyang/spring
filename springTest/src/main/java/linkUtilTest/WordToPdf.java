package linkUtilTest;

import com.example.demo.util.ConvertToPdfUrlUtil;

import static com.example.demo.util.ConvertToPdfUtil.convertToPdfAsAspose;

/**
 * <p>Description: </p>
 * <p>Date: 2023/11/22 23:46</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class WordToPdf {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * 文件转
     */
    private static void test1() {
        String path1 = "/Users/cuiyueyang/Desktop/1.docx";
        String path2 = "/Users/cuiyueyang/Desktop/1.pdf";
        convertToPdfAsAspose (path1,path2);
    }

    /**
     * url转
     */
    private static void test2() {
        String path1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/12761699516046140.docx?Expires=2014876046&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=rQZBe67pkSCvnTsUnVAw0sYVVak%3D";
        String path2 = "/Users/cuiyueyang/Desktop/1.pdf";
        ConvertToPdfUrlUtil.convertToPdfAsAspose(path1,path2);
    }

}
