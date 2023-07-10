package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-06 14:24
 **/
@ApiModel("登录数据")
@Data
public class UserLoginJson {

    @ApiModelProperty("登录用户名")
    @NotEmpty(message = "用户名不能为空")
    @Length(max = 20,message = "用户名最大长度为20")
    private  String username;

    @ApiModelProperty("登录密码")
    @NotEmpty(message = "登陆密码不能为空")
    private String password;
}
