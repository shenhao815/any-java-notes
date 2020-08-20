package com.it;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 解析并重写xml文件
 */
public class WriteXML {
    public static void main(String[] args){

        String path1 = "D:\\workspace_backend\\znyd-quality-service\\znyd-quality-service\\src\\main\\resources\\mapper\\construct";		//要遍历的路径
        String path2 = "D:\\workspace_backend\\znyd-quality-service\\znyd-quality-service\\src\\main\\resources\\mapper\\quality";		//要遍历的路径
        File file1 = new File(path1);		//获取其file对象
        File[] fs1 = file1.listFiles();	//遍历path下的文件和目录，放在File数组中
        File file2 = new File(path2);		//获取其file对象
        File[] fs2 = file2.listFiles();	//遍历path下的文件和目录，放在File数组中

        for(File f:fs2){					//遍历File[]数组
            if(!f.isDirectory())		//若非目录(即文件)，则打印
            {
                try {
                    resolveXML(f.getPath());
                } catch (Exception e) {
                    System.out.println(">>>>>>>>>>>  "+f.getPath());
                    e.printStackTrace();

                }
            }
        }

    }

    public static void resolveXML(String url) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url);

        //创建节点
        Element pricElement = document.createElement("result");
        pricElement.setAttribute("column","org_name");
        pricElement.setAttribute("jdbcType","VARCHAR");
        pricElement.setAttribute("property","orgName");

        //得到参考节点
        NodeList nodeList = document.getElementsByTagName("result");
        Element refNodeElement = (Element) nodeList.item(nodeList.getLength());

        //得到要挂的子节点
        Element resultMap = (Element) document.getElementsByTagName("resultMap").item(0);

        resultMap.insertBefore(pricElement, refNodeElement);

        Element sql = (Element) document.getElementsByTagName("sql").item(0);
        sql.setTextContent(sql.getTextContent() + ", org_name");

        Element insert = (Element) document.getElementsByTagName("insert").item(0);
        String insertText = insert.getTextContent();
        insertText = insertText.replaceAll("org_id","org_id , org_name");
        insertText = insertText.replaceAll("#*orgId,jdbcType=VARCHAR*","orgId,jdbcType=VARCHAR} , #{orgName,jdbcType=VARCHAR}");
        insert.setTextContent(insertText);

        Element insertSelective = (Element) document.getElementsByTagName("insert").item(1);
        Element trim0 = (Element) insertSelective.getElementsByTagName("trim").item(0);
        NodeList ifs = trim0.getElementsByTagName("if");
        Element ifele = document.createElement("if");
        ifele.setAttribute("com/it/test","orgName != null and orgName != ''");
        ifele.setTextContent("org_name,");
        Element refIfs = (Element) ifs.item(ifs.getLength());
        trim0.insertBefore(ifele, refIfs);

        Element trim1 = (Element) insertSelective.getElementsByTagName("trim").item(1);
        NodeList ifs1 = trim0.getElementsByTagName("if");
        Element ifele1 = document.createElement("if");
        ifele1.setAttribute("com/it/test","orgName != null and orgName != ''");
        ifele1.setTextContent("#{orgName,jdbcType=VARCHAR},");
        Element refIfs1 = (Element) ifs1.item(ifs1.getLength());
        trim1.insertBefore(ifele1, refIfs1);


        Element updateSelective = (Element) document.getElementsByTagName("update").item(0);
        Element nSet = (Element) updateSelective.getElementsByTagName("set").item(0);
        NodeList upifs = nSet.getElementsByTagName("if");
        Element upele = document.createElement("if");
        upele.setAttribute("com/it/test","orgName != null and orgName != ''");
        upele.setTextContent(" org_name = #{orgName,jdbcType=VARCHAR},");
        Element upref = (Element) upifs.item(upifs.getLength());
        nSet.insertBefore(upele, upref);

        Element update = (Element) document.getElementsByTagName("update").item(1);
        String updateText = update.getTextContent().trim();
        updateText = updateText.replaceAll("where id",",org_name = #{orgName,jdbcType=VARCHAR} \n where id");
        update.setTextContent(updateText);

        //把更新后的内存写回到xml
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer tf = tFactory.newTransformer();
        tf.transform(new DOMSource(document), new StreamResult(new FileOutputStream(url)));
    }
}
