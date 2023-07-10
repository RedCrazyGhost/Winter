package ghost.winter.Controller;

import ghost.winter.Json.FromInquiryJson;
import ghost.winter.Json.FromSubmitJson;
import ghost.winter.Json.ReturnJson;
import ghost.winter.Serivce.FromService;
import ghost.winter.Unit.CodeMsg;
import ghost.winter.UserCookie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-10 12:32
 **/
@RestController
@Api(tags = "订单接口")
@RequestMapping("/from")
public class FromController {
    @Autowired
    FromService fromService;
    @Autowired
    CodeMsg codeMsg;
    @Autowired
    UserCookie userCookie;

    @ApiOperation(value="查询账号课程",response = ReturnJson.class)
    @PostMapping("/inquiryfrom")
    @ResponseBody
    public Object inquiryfrom(@Valid @RequestBody FromInquiryJson fromInquiryJson) throws IOException {
        Map<String,Object> data=fromService.inquiryfromdata(fromInquiryJson.getSchoolname(),fromInquiryJson.getAccount(),fromInquiryJson.getAccountpassword(),fromInquiryJson.getPlatformType());
        return new ReturnJson(true,(Integer) data.get("code"),codeMsg.CodeMsg((Integer) data.get("code")),data);
    }

    @ApiOperation(value="提交订单课程",response = ReturnJson.class,notes = "{\n" +
            "  \"data\": {\"playformType\":1,\n" +
            "\"accountpassword\": \"9408429031\",\n" +
            "    \"code\": 200,\n" +
            "    \"subjcets\": [\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"软件工程\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"软件测试\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"毛泽东思想和中国特色社会主义理论体系概论（下）\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"计算机专业英语\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"jsp程序设计\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"Java高级程序设计实战教程\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"shuaflag\": false,\n" +
            "        \"subjcetname\": \"famousstars\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"schoolname\": \"华中科技大学\",\n" +
            "    \"name\": \"张三\",\n" +
            "    \"account\": \"2019312431\",\n" +
            "    \"username\":\"9408429031\"\n" +
            "  }\n" +
            "}")
    @PostMapping("/submitfrom")
    @ResponseBody
    public Object submitfrom(@Valid @RequestBody FromSubmitJson fromSubmitJson,
                             @CookieValue("usercookie")String cookie) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
        Integer code;
        if (userCookie.verificationcookie(cookie).split("-")[0].equals(fromSubmitJson.getData().get("username"))){
            code=fromService.submitfrom(fromSubmitJson.getData());
        }else {
            code=1011;
        }

        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }


}
