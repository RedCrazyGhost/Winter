package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-11 18:01
 **/
@Data
@ApiModel("充值数据")
public class RechargeUserJson {

    @ApiModelProperty("操作账号")
    @NotEmpty(message = "操作账号不能为空")
    private String username;

    @NotNull(message = "充值金额不能为空")
    @Min(value = 1,message = "充值最小值为1元")
    @ApiModelProperty("充值金额")
    private Integer rechargemoney;

    @NotEmpty(message = "充值账号不能为空")
    @ApiModelProperty("充值账号")
    private String rechargeusername;
}
