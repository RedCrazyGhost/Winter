package ghost.winter.Controller;

import ghost.winter.Dao.UserDAO;
import ghost.winter.Json.*;
import ghost.winter.Serivce.UserService;
import ghost.winter.Unit.CodeMsg;
import ghost.winter.UserCookie;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-05 15:04
 **/

@RestController
@Api(tags="用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CodeMsg codeMsg;
    @Autowired
    UserCookie userCookie;

    //数据查询没有写
    //考虑session和cookie的跨域操作
    @ApiOperation(value="登录",response = ReturnJson.class)
    @PostMapping("/login")
    @ResponseBody
    public Object userLogin (@Valid @RequestBody UserLoginJson userLoginJson,
                             HttpServletResponse response) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
        Integer code=userService.login(userLoginJson.getUsername(), userLoginJson.getPassword());
        if (code==200){
            Cookie cookie=new Cookie("usercookie",userCookie.generateCookie(userLoginJson.getUsername()));
            //秒为单位 1H有效
            cookie.setMaxAge(60*60);
            cookie.setHttpOnly(false);
            //跨域设置
            cookie.setPath("*");
            response.addCookie(cookie);
        }
        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }

    @ApiOperation(value = "修改密码",response = ReturnJson.class)
    @PutMapping("/changepassword")
    @ResponseBody
    public Object userChangePassword(@Valid @RequestBody UserChangePasswordJson userChangePasswordJson,
                                     @CookieValue("usercookie")String cookie) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
       Integer code;
        if(userCookie.verificationcookie(cookie).split("-")[0].equals(userChangePasswordJson.getUsername())) {
        code= userService.changepassowrd(userChangePasswordJson.getUsername(),
                userChangePasswordJson.getOldpassword(),
                userChangePasswordJson.getNewpassword(),
                userChangePasswordJson.getVerifypassowrd());
       }else {
            code=1011;
       }

        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }


    @ApiOperation(value = "创建用户",response = ReturnJson.class)
    @PostMapping("/create")
    @ResponseBody
    public Object userCreate(@Valid @RequestBody UserCreateJson userCreateJson,
                             @CookieValue("usercookie")String cookie) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
        Integer code;
        if (userCookie.verificationcookie(cookie).split("-")[0].equals(userCreateJson.getUsername())){
            code=userService.create(userCreateJson.getUsername(),userCreateJson.getUserstatus(),userCreateJson.getCreateusername(),userCreateJson.getCreatestatus());
        }else{
            code=1011;
        }
        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }

    @ApiOperation(value = "删除用户",response = ReturnJson.class)
    @DeleteMapping("/delete")
    @ResponseBody
    public Object userDelete(@Valid @RequestBody UserDeleteJson userDeleteJson,
                             @CookieValue("usercookie")String cookie) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {
        Integer code;
        if (userCookie.verificationcookie(cookie).split("-")[0].equals(userDeleteJson.getUsername())){
            code=userService.delete(userDeleteJson.getUsername(),userDeleteJson.getDeleteusername());
        }else{
            code=1011;
        }

        return new ReturnJson(true,code,codeMsg.CodeMsg(code),null);
    }
}
