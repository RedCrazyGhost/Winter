package ghost.winter.Controller;

import ghost.winter.Json.RechargeUserJson;
import ghost.winter.Json.ReturnJson;
import ghost.winter.Json.UserLoginJson;
import ghost.winter.Serivce.RechargeService;
import ghost.winter.Unit.CodeMsg;
import ghost.winter.UserCookie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-11 14:06
 **/
@RestController
@Api(tags = "充值接口")
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    RechargeService rechargeService;
    @Autowired
    CodeMsg codeMsg;
    @Autowired
    UserCookie userCookie;

    @ApiOperation(value="充值",response = ReturnJson.class)
    @PostMapping("/user")
    @ResponseBody
    public Object userLogin (@Valid @RequestBody RechargeUserJson rechargeUserJson,
                             @CookieValue("usercookie")String cookie) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
        Integer code;
        if (userCookie.verificationcookie(cookie).split("-")[0].equals(rechargeUserJson.getUsername())){
            code=rechargeService.recharge(rechargeUserJson.getUsername(),rechargeUserJson.getRechargeusername(),rechargeUserJson.getRechargemoney());
        }else {
            code=1011;
        }

        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }

}
