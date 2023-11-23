package linkUtilTest;

import com.example.demo.util.SignDocUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <p>Description: </p>
 * <p>Date: 2023/11/10 09:51</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignTest {

    public static void main(String[] args) throws Exception{
        //添加图片
        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/12761699516046140.docx?Expires=2014876046&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=rQZBe67pkSCvnTsUnVAw0sYVVak%3D";
        String word2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/12561699516092921.docx?Expires=2014876092&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=jr9bLY2Bl5sqeKlzFfMPl7e6E0k%3D";
        String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/95201699510728989.jpeg?Expires=2014870729&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=ZeedvnskKSxIQCDIp6RfYmyW69U%3D";
//        test1(word1, image);
//        test2(word2, image);
        String word3 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/59421700706064470.docx?Expires=2016066064&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=74wfIp34rQ3hIVUHSdCtCkkAE3A%3D";
        String word4 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/89231700706100861.docx?Expires=2016066100&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=lYbW4xXonv8WbaEdEgiZdcVkqSc%3D";
//        test3(word3);
        test4(word4);
    }

    /**
     * 识别文字添加信息
     * @param word
     * @param image
     * @throws Exception
     */
    public static void test1(String word, String image) throws Exception{
        XWPFDocument doc = SignDocUtils.signDocPicText(word, image, "当事人或其代理人签名：", 684800, 684800);
        SignDocUtils.signDocTextText(doc,"这是测试文字", "当事人或其代理人意见：");
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs1.docx"));
        doc.write(outputStream);
        outputStream.close();
    }

    public static void test2(String word, String image) throws Exception{
        XWPFDocument doc = SignDocUtils.signDocPicTable(word, image, 0, 0);
        SignDocUtils.signDocTextTable(doc, "2023年11月11日 11时11分", 0, 1);
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs2.docx"));
        doc.write(outputStream);
        outputStream.close();
    }

    public static void test3(String word) throws Exception{
        XWPFDocument doc = SignDocUtils.seeDocText(word);
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs1.docx"));
        doc.write(outputStream);
        outputStream.close();
    }

    public static void test4(String word) throws Exception{
        XWPFDocument doc = SignDocUtils.seeDocTable(word);
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs1.docx"));
        doc.write(outputStream);
        outputStream.close();
    }

}
