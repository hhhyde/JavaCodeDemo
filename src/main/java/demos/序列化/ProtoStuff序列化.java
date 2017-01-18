package demos.序列化;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2017/1/18.
 */
public class ProtoStuff序列化 {
    public void protostuff(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserID(1000);
        userInfo.setUserName("访问法尔范儿");
        Schema schema = RuntimeSchema.getSchema(userInfo.getClass());
        LinkedBuffer buffer =LinkedBuffer.allocate(1024);
        byte[] bytes = ProtostuffIOUtil.toByteArray(userInfo,schema,buffer);
        System.out.println("The protostuff serializable length is : " +bytes.length);
    }

    public void nativeSerial() throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserID(1000);
        userInfo.setUserName("访问法尔范儿");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + b.length);
        bos.close();
    }

    public static void main(String[] ss) throws IOException {
        ProtoStuff序列化 proto=new ProtoStuff序列化();
        proto.protostuff();
        proto.nativeSerial();
    }
}
