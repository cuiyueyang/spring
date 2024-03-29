package linkUtilTest;

import com.example.demo.util.SignDocUtil;
import com.example.demo.util.SignDocUtils;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.TextSelection;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/27 11:22</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignDoc {

    public static void main(String[] args) throws Exception{
//        test1();
//        test2();
//        test3();
//        test4();
        test();
    }
    public static void test() {
        try {
            String imageUrl1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
            String docUrl = "http://10.22.226.249:8080/aila-illegal/aila/86391701064766304.jpeg";
            URL file = new URL(docUrl);
            InputStream inputStream = file.openStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void test1() throws Exception{
        //添加图片
        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
        String word2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698650295402.docx?Expires=2014010290&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=0wyjoLNU3GRYjrWmMx6jyjH2ZWE%3D";
        String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        Document doc = SignDocUtil.signPic(word1, image, 130, 370, 30, 70);
        //添加文字
        doc = SignDocUtil.signText(doc, "这是一段测试描述", 90, 680, 200, 30);

        TextSelection[] textSelections = new TextSelection[2];
        textSelections[0].getAsOneRange().getCharacterFormat();


        // 保存文档
        doc.saveToFile("/Users/cuiyueyang/Desktop/送达回证-sign.docx", FileFormat.Docx);
    }

    /**
     * 截取开始，结束中间的文字
     * @throws Exception
     */
    public static void test2() throws Exception{
        List<String> list =  SignDocUtil.getWordKey(null);
        System.out.println("cs");
    }

    public static void test3() throws Exception{
        //添加图片
        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
        String word2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698650295402.docx?Expires=2014010290&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=0wyjoLNU3GRYjrWmMx6jyjH2ZWE%3D";
        String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        Document doc = SignDocUtil.signPic(word1, image, 130, 370, 30, 70);
        //添加文字
        SignDocUtil.signTextTemp2(word1, image);
    }

    public static void test4() throws Exception{
        //添加图片
        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/12761699516046140.docx?Expires=2014876046&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=rQZBe67pkSCvnTsUnVAw0sYVVak%3D";
        String word2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/12561699516092921.docx?Expires=2014876092&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=jr9bLY2Bl5sqeKlzFfMPl7e6E0k%3D";
        String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
//        XWPFDocument doc = SignDocUtils.signDocPic2(word1, image, 1, 9);
//        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs.docx"));
//        doc.write(outputStream);
//        outputStream.close();







        //添加图片
        Document doc = SignDocUtil.signPic(word1, image, 130, 370, 30, 70);
        //添加文字
        doc = SignDocUtil.signText(doc, "这是一段测试描述", 90, 680, 200, 30);


        // 保存文档
        doc.saveToFile("/Users/cuiyueyang/Desktop/送达回证-sign.docx", FileFormat.Docx);
    }

}
