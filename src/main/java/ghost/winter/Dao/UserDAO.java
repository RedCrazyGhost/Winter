package ghost.winter.Dao;


import ghost.winter.Model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author: RedCrazyGhost
 * @create: 2020-03-19 17:10
 **/
public interface UserDAO extends CrudRepository<User,Object> {
    //修改密码
    @Transactional
    @Modifying
    @Query("UPDATE User u set u.password=:newpassword where u.username=:username")
    void changepassword(@Param("username") String username, @Param("newpassword") String newpassword);

    //UPDATE user_text1 u set u.money=u.money-1 where u.user_name="RedCrazyGhost"
    @Transactional
    @Modifying
    @Query("UPDATE User u set u.money=u.money+:money where u.username=:username")
    int addUserMoney(@Param("username") String username, @Param("money") int money);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.money=u.money-:money where u.username=:username")
    int minusUserMoney(@Param("username") String username, @Param("money") int money);

}
