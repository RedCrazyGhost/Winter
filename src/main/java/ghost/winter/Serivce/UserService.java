package ghost.winter.Serivce;

import ghost.winter.Dao.UserDAO;
import ghost.winter.Model.User;
import ghost.winter.Unit.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-05 21:32
 **/
@Service
public class UserService {
    @Autowired
    Status status;
    @Autowired
    UserDAO userDAO;

    /*
    code
    1001 密码错误
    1000 用户名不存在
     */
    public Integer login(String username,String password){
        Optional<User> userlogin= userDAO.findById(username);
        if (userlogin.isPresent()){
            if (password.equals(userlogin.get().getPassword())){
                return 200;
            }else {
                return 1001;
            }
        }else{
            return 1000;
        }
    }
    /*
    code
    1003 旧密码和新密码相同
    1002 新密码和验证密码不一致
    1001 密码错误
    1000 用户名不存在
     */
    public  Integer changepassowrd(String username ,String oldpassword,String newpassword,String verifypassowrd){
        Optional<User> userchangepassowrd= userDAO.findById(username);
        if (userchangepassowrd.isPresent()){
            if (oldpassword.equals(userchangepassowrd.get().getPassword())){
                if (newpassword.equals(verifypassowrd)){
                    if (!oldpassword.equals(newpassword)){
                        userDAO.changepassword(username,newpassword);
                        return 200;
                    }else {
                        return 1003;
                    }
                }else {
                    return 1002;
                }
            }else {
                return 1001;
            }
        }else{
            return 1000;
        }
    }


    /*
    code
    1005 当前账号权限不足
    1004 创建的账号已经存在
    1006 创建的账号权限有误
    1000 用户名不存在
     */
    public Integer create(String username,String userstatus,String createusername,String createstatus){
        Optional<User> user= userDAO.findById(username);
        if (user.isPresent()){
            if (!"user".equals(userstatus)&&user.get().getStatus().equals(userstatus)){
                Optional<User> usercreate= userDAO.findById(createusername);
                if (!usercreate.isPresent()){
                    if (status.createStatusFlagProxy(createstatus)){
                        User user1=new User();
                        user1.setUsername(createusername);
                        user1.setPassword(createusername);
                        user1.setMoney(0);
                        user1.setStatus(createstatus);
                        user1.setCode(1);
                        user1.setTime(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                        userDAO.save(user1);
                        return 200;
                    }else{
                        return 1006;
                    }
                }else {
                    return 1004;
                }
            }else {
                return 1005;
            }
        }else {
            return 1000;
        }

    }

    public Integer delete(String username,String deleteusername){
        Optional<User> user= userDAO.findById(username);
        Optional<User> deleteuser= userDAO.findById(deleteusername);
        if ("admin".equals(user.get().getStatus())){
            if (deleteuser.isPresent()){
                userDAO.deleteById(deleteusername);
                return 200;
            }else {
                return 1000;
            }
        }else{
//          删除操作权限不足
            return 1007;
        }
    }

}
