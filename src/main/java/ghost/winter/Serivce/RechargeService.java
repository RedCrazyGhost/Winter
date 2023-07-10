package ghost.winter.Serivce;

import ghost.winter.Dao.FromDAO;
import ghost.winter.Dao.RechargeDAO;
import ghost.winter.Dao.UserDAO;
import ghost.winter.Model.Recharge;
import ghost.winter.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-11 14:20
 **/
@Service
public class RechargeService {
    @Autowired
    FromDAO fromDAO;
    @Autowired
    RechargeDAO rechargeDAO;
    @Autowired
    UserDAO userDAO;

    public Integer recharge(String username,String rechargename,Integer rechargemoney){
        User user=userDAO.findById(username).get();
        if (user.getStatus().equals("admin")||user.getStatus().equals("proxy")){
            if (user.getMoney()-rechargemoney>=0){
                userDAO.minusUserMoney(username,rechargemoney);
                userDAO.addUserMoney(rechargename,rechargemoney);
                saverecharge(username, user.getStatus(), rechargename, rechargemoney);
                return 200;
            }else {
                return  1009;
            }
        }else {
            return 1005;
        }
    }

    private void saverecharge(String username,String status,String rechargename,Integer rechargemoney){
        Integer id=count();

        Recharge recharge=new Recharge();
        recharge.setUserstatus(status);
        recharge.setUsername(username);
        recharge.setFromrecharge(0);
        recharge.setId(id+"-1");
        recharge.setChangemoney("+"+rechargemoney);
        recharge.setRechargeusername(rechargename);
        recharge.setRechargetime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        rechargeDAO.save(recharge);

        recharge.setUserstatus(userDAO.findById(rechargename).get().getStatus());
        recharge.setUsername(rechargename);
        recharge.setFromrecharge(0);
        recharge.setId(id+"-2");
        recharge.setChangemoney("-"+rechargemoney);
        recharge.setRechargeusername(username);
        recharge.setRechargetime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        rechargeDAO.save(recharge);

    }







    private Integer count(){
        Integer formnum=(int) fromDAO.count();
        Integer rechargenum=(int)rechargeDAO.count();
        Integer fromrecharge=(rechargenum-formnum)/2;
        fromrecharge+=formnum;
        fromrecharge++;
        return fromrecharge;
    }
}
