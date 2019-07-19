package com.file.management.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DocumentReader;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 利用dom4j读取xml转换为dom4j的document
 * 利用XStream将xml的String转换为pojo
 */
public class XStreamUtil {
    /**
     * XML转对象
     * @param clazz 对象类
     * @param xml xml字符串
     * @return Java对象
     */
    public static Object toBean(Class<?> clazz, String xml){
        Object xmlObject = null;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject= xstream.fromXML(xml);

        return xmlObject;
    }

    /**
     * 对象转xml
     * @param object Java对象
     * @return xml字符串
     */
    public static String toXml(Object object){
        String xml = "";
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(object.getClass());
        return xStream.toXML(object);
    }

    /**
     * 将xml的document类型转换为String
     * @param document xml文件
     * @return
     */
    public static String xmlDocument2String(Document document){
        String xmlStr = document.asXML();
        return xmlStr;
    }

    /**
     * 将xml的String转换为document
     * @param xmlStr xml字符串
     * @return
     */
    public static Document string2XMLDocument(String xmlStr){
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("xmlStr转Document失败！");
        }
        return document;
    }
}
