package ghost.winter.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: RedCrazyGhost
 * @create: 2020-03-19 17:04
 **/
@Data
@Entity
@Table(name="recharge_text1")
public class Recharge {

    private static  final long serialVersionUID=8127035123921338189L;

//    交易主体
    @Column(name = "u_name",nullable = false)
    private String username;

    @Id
    @Column(name = "id",nullable = false)
    private String id;

//    交易受体
    @Column(name = "r_u_name",nullable = false)
    private String rechargeusername;


    //    交易金额
    @Column(name = "change_money",nullable = false)
    private String changemoney;

//    执行者的权限
    @Column(name = "u_status",nullable = false)
    private String userstatus;

    @Column(name = "from_recharge")
    private int fromrecharge;

    @Column(name = "recharge_time", nullable = false)
    private String rechargetime;


}
