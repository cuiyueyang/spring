package linkUtilTest;

import com.example.demo.util.SignPdfUtil;
import com.example.demo.util.SignPdfUtil2;
import com.example.demo.util.SignPdfUtil3;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/27 11:22</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignPdf2 {

    public static void main(String[] args) throws Exception{
        //根据经纬度
        test1();
        //根据文字确定点位
//        test2();
    }

    public static void test1() throws Exception{
        try {
            String pdf1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/39871702447999389.pdf?Expires=2017807999&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=sFhs4ficcTi2OR5cXNytWa2jFl4%3D";
            String sign1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/53651702447626210.jpg?Expires=2017807626&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=OuYJD7%2BTg4edIYdUAdn7oldcVOI%3D";

            byte[] bytes =SignPdfUtil3.getByte(pdf1);
            bytes = SignPdfUtil3.signCoordinate(bytes, sign1, 170, 110, 25, 55, 90, 1);
            String text1 = "根据我是两个人委托授权，我代表我是两个人放弃进行陈述，申辩的权利，要求尽快处理。";
            String text2 = text1.substring(0, 34);
            String text3 = text1.substring(34, text1.length());
            bytes = SignPdfUtil3.signAddText(bytes,  text2, 2, 150, 250);
            bytes = SignPdfUtil3.signAddText(bytes,  text3, 2, 150, 230);

            bytes = SignPdfUtil3.signCoordinate(bytes, sign1, 170, 110, 25, 55, 90, 2);
            bytes = SignPdfUtil3.signAddText(bytes,  "csCuiyy", 1, 150, 250);

            FileOutputStream pdfOutputStream = new FileOutputStream("/Users/cuiyueyang/Desktop/送达回证-sign1.pdf");
            pdfOutputStream.write(bytes);
            pdfOutputStream.close();
        } catch (Exception e) {
            System.out.println("error:"+e.getMessage());
        }
    }

//    public static void test1() throws Exception{
//        try {
//            String pdf1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/39871702447999389.pdf?Expires=2017807999&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=sFhs4ficcTi2OR5cXNytWa2jFl4%3D";
//            String sign1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/53651702447626210.jpg?Expires=2017807626&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=OuYJD7%2BTg4edIYdUAdn7oldcVOI%3D";
//
//
//            byte[] bytes = SignPdfUtil3.signCoordinate(pdf1, sign1, 170, 110, 25, 55, 90, 1);
//            bytes = SignPdfUtil3.signAddText(bytes,  "csCuiyy", 2, 150, 250);
//            FileOutputStream pdfOutputStream = new FileOutputStream("/Users/cuiyueyang/Desktop/送达回证-sign1.pdf");
//            pdfOutputStream.write(bytes);
//            pdfOutputStream.close();
//        } catch (Exception e) {
//            System.out.println("error:"+e.getMessage());
//        }
//    }

//    public static void test2() throws Exception{
//        try {
//            Calendar cal = Calendar.getInstance();
//            // 当前年
//            int year = cal.get(Calendar.YEAR);
//            String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
//            String pdf1 = "https://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/79601698375654960.pdf?Expires=2013735655&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=kXJ9YnASuFeqlvRgBjpa6WIImEs%3D";
//            String img1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/4951698375938540.jpeg?Expires=2013735938&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=%2FBw41Wde4A98Q%2FGEaKzdA1v%2FFOQ%3D";
//            String img2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
//            byte[] bytes = SignPdfUtil.signImgAndText(pdf1, img2);
//            bytes = SignPdfUtil.signAddText(bytes, year + "", 1, 432, 376, "授权委托代理人不放弃陈述、申辩、听证的权力");
//            FileOutputStream pdfOutputStream = new FileOutputStream("/Users/cuiyueyang/Desktop/送达回证-sign2.pdf");
//            pdfOutputStream.write(bytes);
//            pdfOutputStream.close();
//        } catch (Exception e) {
//            System.out.println("error:"+e.getMessage());
//        }
//    }

}
