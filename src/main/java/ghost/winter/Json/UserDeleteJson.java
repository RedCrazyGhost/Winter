package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-09 15:43
 **/
@ApiModel("用户删除数据")
@Data
public class UserDeleteJson {

    @ApiModelProperty("执行操作用户")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("删除用户")
    @NotEmpty(message = "删除用户不能为空")
    private String deleteusername;


    @ApiModelProperty("删除确认")
    @NotNull(message = "选项不能为空")
    @AssertTrue(message = "请确认后执行操作")
    private Boolean deleteflag;
}
