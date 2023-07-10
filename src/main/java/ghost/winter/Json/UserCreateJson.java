package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-09 14:17
 **/
@ApiModel("创建用户数据")
@Data
public class UserCreateJson {

    @ApiModelProperty("当前账号用户名")
    @NotEmpty(message = "当前账号用户名不能为空")
    private String username;

    @ApiModelProperty("当前使用账号的权限")
    @NotEmpty(message = "当前账号其权限不能为空")
    private String userstatus;

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String createusername;

    @NotEmpty(message = "权限不能为空")
    @ApiModelProperty("权限")
    private String createstatus;
}
