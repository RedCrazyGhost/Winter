package ghost.winter.Json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-11 10:14
 **/
@Data
@ApiModel("提交订单数据")
public class FromSubmitJson {


    @ApiModelProperty("课程数据")
    @NotNull(message = "数据不能为空")
    private Map<String,Object>data;

}
