package demos.designpatterns.SRP;


public class UserInfo implements IUserInfo {
    String userID;
    String password;
    String userName;


    @Override
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String getUserID() {
        return this.userID;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setUserName(String name) {
        this.userName = name;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public boolean changePassword(String oldPassword) {
        return this.password == oldPassword;
    }

    @Override
    public boolean deleteUser(IUserBO userBO) {
        return this == userBO;
    }

    @Override
    public void mapUser(IUserBO userBO) {

    }

    @Override
    public boolean addOrg(IUserBO userBO, int orgID) {
        return false;
    }

    @Override
    public boolean addRole(IUserBO userBO, int roleID) {
        return false;
    }
}
