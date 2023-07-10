package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-10 17:32
 **/
@Data
@ApiModel("查询订单数据")
public class FromInquiryJson {
    @ApiModelProperty("学校名称")
    @NotEmpty(message = "学校名称不能为空")
    private String schoolname;

    @ApiModelProperty("登录账号")
    @NotEmpty(message = "登录账号不能为空")
    private String account;

    @ApiModelProperty("登录密码")
    @NotEmpty(message = "登录密码不能为空")
    private String accountpassword;

    @ApiModelProperty("执行平台")
    @NotEmpty(message = "执行平台不能为空")
    private  String platformType;
}
