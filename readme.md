# Winter

<kbd>功能无法正常使用！依赖存在风险漏洞！</kbd>

> 架构：Spring Boot + Spring Data JPA + fastjson + jsoup(页面解析) + swagger

此项目是大二疫情期间做的Web项目，由于时间久远导致页面内容丢失，只保留下来服务端代码。


## 存在问题
1. “注解”项目 -> 代码开发控制细粒度不够，缺少异常出现的兜底方案
2垃圾代码可读性极差！并且返回code含义不明确！
```java
if (userchangepassowrd.isPresent()){
    if (oldpassword.equals(userchangepassowrd.get().getPassword())){
        if (newpassword.equals(verifypassowrd)){
            if (!oldpassword.equals(newpassword)){
                userDAO.changepassword(username,newpassword);
                return 200;
            }else {
                return 1003;
            }
        }else {
            return 1002;
        }
    }else {
        return 1001;
    }
}else {
    return 1000;
}
```

