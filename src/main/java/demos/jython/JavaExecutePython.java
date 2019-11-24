package demos.jython;

import org.junit.Before;
import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

// TODO: 2018-08-29 完成java与python的对接    py4j? 
public class JavaExecutePython {
    public PythonInterpreter interpreter;
    public String basePath = JavaExecutePython.class.getResource("").getPath();

    @Before
    public void start() {
        interpreter = new PythonInterpreter();
    }

    /**
     * 在java类中直接执行python语句
     */
    @Test
    public void test01() {
        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        System.out.println(666);
        interpreter.exec("print days[1];");
    }

    //在java中调用本机python脚本中的函数
    @Test
    public void test02() {
        interpreter.execfile(basePath + "my_utils.py");
        PyFunction func = (PyFunction) interpreter.get("adder", PyFunction.class);

        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("anwser = " + pyobj.toString());
    }

    //直接执行Python脚本
    @Test
    public void test03() {
        interpreter.execfile(basePath + "input.py");
    }
}
