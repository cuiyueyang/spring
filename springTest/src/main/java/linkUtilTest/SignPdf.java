package linkUtilTest;

import com.example.demo.util.SignPdfUtil;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/27 11:22</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignPdf {

    public static void main(String[] args) throws Exception{
        //根据经纬度
        test1();
        //根据文字确定点位
        test2();
    }

    public static void test1() throws Exception{
        try {
            Calendar cal = Calendar.getInstance();
            // 当前年
            int year = cal.get(Calendar.YEAR);
            String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
            String pdf1 = "https://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/79601698375654960.pdf?Expires=2013735655&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=kXJ9YnASuFeqlvRgBjpa6WIImEs%3D";
            String img1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/4951698375938540.jpeg?Expires=2013735938&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=%2FBw41Wde4A98Q%2FGEaKzdA1v%2FFOQ%3D";
            String img2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
            byte[] bytes = SignPdfUtil.signCoordinate(pdf1, img2, 170, 350, 50, 110, 90, 1);
            bytes = SignPdfUtil.signAddText(bytes, year + "", 1, 432, 376, "授权委托代理人不放弃陈述、申辩、听证的权力");
            FileOutputStream pdfOutputStream = new FileOutputStream("/Users/cuiyueyang/Desktop/送达回证-sign.pdf");
            pdfOutputStream.write(bytes);
            pdfOutputStream.close();
        } catch (Exception e) {
            System.out.println("error:"+e.getMessage());
        }
    }

    public static void test2() throws Exception{
        try {
            Calendar cal = Calendar.getInstance();
            // 当前年
            int year = cal.get(Calendar.YEAR);
            String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
            String pdf1 = "https://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/79601698375654960.pdf?Expires=2013735655&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=kXJ9YnASuFeqlvRgBjpa6WIImEs%3D";
            String img1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/4951698375938540.jpeg?Expires=2013735938&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=%2FBw41Wde4A98Q%2FGEaKzdA1v%2FFOQ%3D";
            String img2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
            byte[] bytes = SignPdfUtil.signCoordinate(pdf1, img2, 170, 350, 50, 110, 90, 1);
            bytes = SignPdfUtil.signAddText(bytes, year + "", 1, 432, 376, "授权委托代理人不放弃陈述、申辩、听证的权力");
            FileOutputStream pdfOutputStream = new FileOutputStream("/Users/cuiyueyang/Desktop/送达回证-sign.pdf");
            pdfOutputStream.write(bytes);
            pdfOutputStream.close();
        } catch (Exception e) {
            System.out.println("error:"+e.getMessage());
        }
    }

}
