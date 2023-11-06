package linkUtilTest;

import com.example.demo.util.SignDocUtil;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.TextSelection;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/27 11:22</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignDoc {

    public static void main(String[] args) throws Exception{
        test1();
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

}
