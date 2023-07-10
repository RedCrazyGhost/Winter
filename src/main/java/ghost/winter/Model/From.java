package ghost.winter.Model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: RedCrazyGhost
 * @create: 2020-03-19 16:23
 **/
@Data
@Entity
@Table(name = "form_text1")
public class From {

    private static final long serialVersionUID = 8127135730921338189L;

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    //    订单归属用户

    @Column(name = "u_name", nullable = false)
    private String username;

    //    学校名称
    @Column(name = "school_name", nullable = false)
    private String schoolname;

    //    姓名
    @Column(name = "name", nullable = false)
    private String name;

    //    学号
    @Column(name = "account", nullable = false)
    private String account;

    //    学号密码
    @Column(name = "account_password", nullable = false)
    private String accountpassword;

    //    花费金额
    @Column(name = "price", nullable = false)
    private Integer price;

    //    科目
    @Column(name = "subject", nullable = false)
    private String subject;

    //    科目数量
    @Column(name = "subject_num", nullable = false)
    private Integer subjectnum;

    //    订单状态
    @Column(name = "subject_code", nullable = false)
    private String subjectcode;

    //    平台
    @Column(name = "platform", nullable = false)
    private String platform;

    //对应交易订单
    @Column(name = "from_recharge",nullable = false)
    private int fromrecharge;

    //       创建时间
    @Column(name = "subject_time", nullable = false)
    private String subjecttime;

}
