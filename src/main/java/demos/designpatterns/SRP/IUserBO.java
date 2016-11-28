package demos.designpatterns.SRP;

/**
 * 负责用户的属性
 */
public interface IUserBO {
    void setUserID(String userID);

    String getUserID();

    void setPassword(String password);

    String getPassword();

    void setUserName(String name);

    String getUserName();
}
