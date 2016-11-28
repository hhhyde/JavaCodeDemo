package demos.designpatterns.SRP;

/**
 * Created by kke on 2016/11/28.
 */
public class SRPTest {
    public static void main(String[] args) {
        IUserBiz userInfo = new UserInfo();
        // 认为他是一个纯粹的BO
        IUserBO userBO = (IUserBO) userInfo;
        userBO.setPassword("password");
        // 开始执行动作了,当做他是一个业务逻辑类
        IUserBiz userBiz = (IUserBiz) userInfo;
        userBiz.changePassword("password1");
    }
}
