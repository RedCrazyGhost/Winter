package ghost.winter.Unit;

import org.springframework.stereotype.Component;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-06 22:00
 **/

@Component
public class CodeMsg {
    public String CodeMsg(Integer code){
        switch (code){
            case  1011:return "未知身份";
            case  1010:return "课程数量不能为0";
            case  1009:return "当前账号余额不足";
            case 1008:return "获取数据失败";
            case 1007:return "删除操作权限不足";
            case 999:return "测试使用code！";
            case 1006:return "创建的账号权限有误";
            case 1005: return "当前账号权限不足";
            case 1004:return "创建的账号已经存在";
            case 1003:return "旧密码和新密码相同";
            case 1002:return "新密码和验证密码不一致";
            case 200:return "成功";
            case 1000:return "账号不存在";
            case 1001:return "密码不正确";
            default:return "未知错误！请联系服务提供商";
        }
    }
}
