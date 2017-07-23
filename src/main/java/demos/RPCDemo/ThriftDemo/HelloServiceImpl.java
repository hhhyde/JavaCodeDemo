package demos.RPCDemo.ThriftDemo;

import org.apache.thrift.TException;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
public class HelloServiceImpl implements Hello.Iface {
    @Override
    public String helloString(String para) throws TException {
        return "helloString";
    }

    @Override
    public int helloInt(int para) throws TException {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para;
    }

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        return para;
    }

    @Override
    public void helloVoid() throws TException {

    }

    @Override
    public String helloNull() throws TException {
        return null;
    }
}
