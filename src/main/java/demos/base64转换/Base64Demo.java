package demos.base64转换;

import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharSet;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by kj855 on 2017/1/6.
 */
public class Base64Demo {
    public static void main(String[] args) throws IOException {
        String ss = Base64Utils.encodeToString(FileUtils.readFileToByteArray(new File("D://aa.jpg")));
        FileUtils.writeStringToFile(new File("D://aa1.txt"),ss, Charset.defaultCharset());
        byte[] bb = Base64Utils.decodeFromString(FileUtils.readFileToString(new File("D://aa1.txt"),"UTF-8"));
        FileUtils.writeByteArrayToFile(new File("D://aa1.jpg"),bb);
    }
}
