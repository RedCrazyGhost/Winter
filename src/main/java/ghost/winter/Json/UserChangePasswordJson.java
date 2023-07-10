package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-06 22:19
 **/
@Data
@ApiModel("修改密码数据")
public class UserChangePasswordJson {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    @Length(max = 20,message = "用户名最大长度为20")
    private String username;

    @ApiModelProperty("旧密码")
    @Length(min = 6,max = 20,message = "密码最小长度为6，最大长度为20")
    @NotEmpty(message = "旧密码不能为空")
    private String oldpassword;

    @ApiModelProperty("新密码")
    @Length(min = 6,max = 20,message = "密码最小长度为6，最大长度为20")
    @NotEmpty(message = "新密码不能为空")
    private String newpassword;

    @ApiModelProperty("验证密码")
    @Length(min = 6,max = 20,message = "密码最小长度为6，最大长度为20")
    @NotEmpty(message = "验证密码不能为空")
    private String verifypassowrd;
}
