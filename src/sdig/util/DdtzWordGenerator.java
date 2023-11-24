package sdig.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DdtzWordGenerator {
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(DdtzWordGenerator.class,"/sdig/template");
        allTemplates = new HashMap<>(); // Java 7 钻石语法
        try {
            allTemplates.put("调动通知表",configuration.getTemplate("ddtz.xml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private DdtzWordGenerator() {
        throw new AssertionError();
    }

    public static File createDoc(Map<?, ?> dataMap, String filename)
            throws TemplateException, IOException {
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";
        File f = new File(name);
        Template t = allTemplates.get(filename);

        // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
        Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
        t.process(dataMap, w);
        w.close();

        return f;
    }

    // 将图片转换成BASE64字符串
    public static String getImageString(String imgname) throws IOException {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgname);
            data = new byte[in.available()];
            in.read(data);


            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (in != null)
                in.close();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }

}

