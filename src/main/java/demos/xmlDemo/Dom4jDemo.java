package demos.xmlDemo;

import org.apache.axis2.util.XMLUtils;
import org.apache.tools.ant.util.ReaderInputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dom4jDemo {
    private String createXML() {
        Document document = DocumentHelper.createDocument();
        Element catalogElement = document.addElement("root");
        Element headElement = catalogElement.addElement("head");
        Element codeElement = headElement.addElement("code");
        codeElement.setText("1");
        Element messageElement = headElement.addElement("message");
        messageElement.setText("数据下载成功");
        Element rownumElement = headElement.addElement("rownum");
        rownumElement.setText("5");
        Element bodyElement = catalogElement.addElement("body");

        for (int i = 0; i < 5; i++) {
            Element drvexamElement = bodyElement.addElement("drvexam");

            SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmss");
            String today = format.format(new Date());

            Element lshElement = drvexamElement.addElement("lsh");
            lshElement.setText("0" + today + i);

            Element kskmElement = drvexamElement.addElement("kskm");
            kskmElement.setText("2");

            Element zkzmbh = drvexamElement.addElement("zkzmbh");
            zkzmbh.setText("0" + today + i);

            Element sfzmmc = drvexamElement.addElement("sfzmmc");
            sfzmmc.setText("0");

            Element sfzmhm = drvexamElement.addElement("sfzmhm");
            sfzmhm.setText("0" + today + i);

            Element xm = drvexamElement.addElement("xm");
            xm.setText("0" + today + i);

            Element ykrq = drvexamElement.addElement("ykrq");
            ykrq.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));

            Element kscs = drvexamElement.addElement("kscs");
            kscs.setText("1");

            Element ksy1 = drvexamElement.addElement("ksy1");
            ksy1.setText("ksy1");

            Element ksy2 = drvexamElement.addElement("ksy2");
            ksy2.setText("ksy2");

        }

        // 生成 xml文件
//            try {
//                XMLWriter output = new XMLWriter(new FileWriter(new File("d:/catalog1.xml")));
//                output.write(document);
//                output.close();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }

        return document.asXML();
    }

    private void parserXML() throws DocumentException, UnsupportedEncodingException {
        String xml = createXML();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
        List<DefaultElement> drvexams = document.selectNodes("//lsh");
        for (Element drvexam : drvexams) {
            Element lshElement = drvexam.element("lsh5");
            if (null!=lshElement){
                System.out.println("lsh=" + drvexam.element("lsh5").getTextTrim());
            }

            Element ykrqElement = drvexam.element("ykrq");
            if (null!=ykrqElement){
                System.out.println("ykrq=" + drvexam.element("ykrq").getTextTrim());
            }
        }

    }


    public static void main(String[] ss) throws DocumentException, UnsupportedEncodingException {
        Dom4jDemo demo = new Dom4jDemo();
        demo.parserXML();
//        System.out.println(demo.createXML());
    }
}
