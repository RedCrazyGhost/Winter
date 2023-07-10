package ghost.winter.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: RedCrazyGhost
 * @create: 2020-03-19 15:45
 **/
@Data
@Entity
@Table(name = "user_text1")
public class User {
    private static  final long serialVersionUID=8127035730921338189L;

//        用户名账号 具有唯一性
    @Id
    @Column(name = "user_name",nullable = false,updatable = false,unique = true)
    private String username;

//    用户密码
    @Column(name = "user_password",nullable = false,updatable = false)
    private String password;

//   账号余额
    @Column(name = "money",nullable = false)
    private int money;

//        账号创建时间
    @Column(name = "time",nullable = false)
    private String time;

//    账号权限：admin(网站管理员) proxy(代理) user(用户)
    @Column(name = "status",nullable = false)
    private  String status;

//    账号状态 0表示封禁 1表示正常运行
    @Column(name = "code",nullable = false)
    private int code;

}
