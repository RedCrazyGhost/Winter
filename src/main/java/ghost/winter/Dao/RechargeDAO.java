package ghost.winter.Dao;

import ghost.winter.Model.Recharge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface RechargeDAO extends CrudRepository<Recharge,Object> {

}
