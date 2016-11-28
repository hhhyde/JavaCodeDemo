package demos.designpatterns.SRP;

/**
 * Created by kke on 2016/11/28.
 */

/**
 * 负责用户的行为
 */
public interface IUserBiz {
    boolean changePassword(String oldPassword);

    boolean deleteUser(IUserBO userBO);

    void mapUser(IUserBO userBO);

    boolean addOrg(IUserBO userBO, int orgID);

    boolean addRole(IUserBO userBO, int roleID);

}
