package ioc.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dela on 3/1/18.
 */

// 启动XmlBeanFactory后进行Resource资源定位, 加载xml文件,
// 将其中<bean>标签中所定义的信息转化未BeanDefinition
public class XmlBeanFactory extends DefaultListableBeanFactory {
    private String fileName;

    public XmlBeanFactory(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws ParserConfigurationException, SAXException, IOException {
        loadFile(fileName);
    }

    // 将XML文件加载进来, 然后读取各个标签里的配置
    private void loadFile(String location) throws IOException, ParserConfigurationException, SAXException {
        // 加载 xml 配置文件
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        // 遍历 <bean> 标签
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String className = ele.getAttribute("class");

                // 加载 beanClass
                Class beanClass = null;
                try {
                    beanClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                // 创建 bean
                // 遍历 <property> 标签

            }
        }
    }
}

