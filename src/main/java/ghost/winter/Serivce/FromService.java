package ghost.winter.Serivce;

import com.alibaba.fastjson.JSONPath;
import ghost.winter.Dao.FromDAO;
import ghost.winter.Dao.RechargeDAO;
import ghost.winter.Dao.UserDAO;
import ghost.winter.Json.FromSubmitJson;
import ghost.winter.Model.From;
import ghost.winter.Model.Recharge;
import ghost.winter.Model.User;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-10 12:36
 **/
@Service
public class FromService {
    @Autowired
    FromDAO fromDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RechargeDAO rechargeDAO;

    private  String playformstr(Integer platform){
        switch (platform){
            case 1:return"超星";
            case 2:return"智慧树";
            case 3:return"高校邦";
            case 4:return"好大学";
            case 5:return"名华";
            case 6:return"中国大学";
            case 7:return"优学院";
            case 8:return"云课堂智慧职教";
            case 9:return "智慧职教";
            case 10:return "安全教育";
            case 11:return "学堂云pro/学堂云3.0";
            case 12:return"UI校园";
            case 13:return"湖南青马";
            case 14:return"创就业云课堂";
            case 15:return"广东开放大学";
            case 16:return"青书学堂";
            case 17:return"河北北方学院继续教育学院网上教学平台";
            case 18:return"点墨云";
            default:return "未知问题，请核对!";
        }
    }

    private Integer count(){
        Integer formnum=(int) fromDAO.count();
        Integer rechargenum=(int)rechargeDAO.count();
        Integer fromrecharge=(rechargenum-formnum)/2;
        fromrecharge+=formnum;
        fromrecharge++;
        return fromrecharge;
    }

    public Integer submitfrom(Map<String,Object>data){
        Optional<User> user= userDAO.findById(data.get("username"));
        if (user.isPresent()){
            Integer fromrecharge=count();
            Integer id=(int)fromDAO.count();
            id++;

            From from=new From();
            from.setId(id);
            from.setAccount(data.get("account").toString());
            from.setAccountpassword(data.get("accountpassword").toString());
            from.setUsername(data.get("username").toString());
            from.setName(data.get("name").toString());
            from.setSchoolname(data.get("schoolname").toString());
            from.setPlatform(playformstr((Integer)data.get("playformType")));
            Integer subjectnum=0;
            String subject="";
            List<Map<String,Object>> listdata=(List)data.get("subjcets");
            for (Map<String,Object> mapdata:listdata){
                if ((Boolean)mapdata.get("shuaflag")){
                    subject+=mapdata.get("subjcetname").toString()+"  ";
                    subjectnum++;
                }
            }
            if (subjectnum==0){
                //课程数量不能为0
                return 1010;
            }
            from.setSubject(subject);
            from.setSubjectnum(subjectnum);
            Integer price=subjectnum*2;
            from.setPrice(price);
            from.setSubjectcode("未上号");
            from.setSubjecttime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
            from.setFromrecharge(fromrecharge);

            Recharge recharge=new Recharge();
            recharge.setId(fromrecharge.toString());
            recharge.setChangemoney("+"+price);
            recharge.setUsername(data.get("username").toString());
            recharge.setRechargeusername("RedCrazyGhost");
            recharge.setUserstatus(user.get().getStatus());
            recharge.setFromrecharge(fromrecharge);
            recharge.setRechargetime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));

            if(user.get().getMoney()-price>=0){
                userDAO.minusUserMoney(data.get("username").toString(),price);
                userDAO.addUserMoney("RedCrazyGhost",price);
                fromDAO.save(from);
                rechargeDAO.save(recharge);
                return 200;
            }else {
                //当前帐号余额不足
                return  1009;
            }
        }else {
            return 1000;
        }
    }



//    http://nanashuake.com 网址已失效！
    public Map<String, Object> inquiryfromdata(String schoolname, String account, String  accountpassword, String platformType) throws IOException {
        String studendatajson = Jsoup.connect("http://nanashuake.com/api/query?token=null&school="
                +schoolname+"&username="
                +account+"&password="
                +accountpassword+"&platform="
                +platformType)
                .ignoreContentType(true)
                .timeout(300000)
                .post().text();

        Map<String,Object>mapdata=new HashMap<>();
        String jsonmgs= JSONPath.read(studendatajson, "$.obj.status").toString();
        if (jsonmgs.equals("true")){
            mapdata.put("name",JSONPath.read(studendatajson,"$.obj.userName").toString());
            mapdata.put("school",JSONPath.read(studendatajson,"$.obj.schoolName").toString());
            mapdata.put("account",JSONPath.read(studendatajson,"$.obj.account").toString());
            mapdata.put("accountpassword",JSONPath.read(studendatajson,"$.obj.password"));
            mapdata.put("platformType",platformType);

            List<Map<String,String>> mapcourseList=(List<Map<String, String>>) JSONPath.read(studendatajson,"$.obj.courseList");
            List<Object> subjectslistdata = new ArrayList<>();
            for (int i = 0; i <mapcourseList.size() ; i++) {
                Map<String,Object>subjcetdata=new HashMap<>();
                subjcetdata.put("subjcetname",JSONPath.read(studendatajson,"$.obj.courseList["+i+"].courseName"));
                subjcetdata.put("shuaflag",false);
                subjectslistdata.add(subjcetdata);
            }
            mapdata.put("subjcets",subjectslistdata);
            mapdata.put("code",200);
        }else {
            mapdata.put("msg",JSONPath.read(studendatajson, "$.obj.msg"));
            mapdata.put("code",1008);
        }
        return mapdata;
    }
}
