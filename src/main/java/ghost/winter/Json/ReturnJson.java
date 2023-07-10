package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-06 17:04
 **/
@Data
@AllArgsConstructor
@ApiModel("返回数据")
public class ReturnJson {

    @ApiModelProperty("连接判断")
    @NotNull
    private boolean connection;

    @NotNull
    @ApiModelProperty("行为代码")
    private int code;

    @ApiModelProperty("行为消息")
    @NotNull
    private String msg;

    @ApiModelProperty("返回数据")
    private Object data;
}
