package ghost.winter.Unit;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-09 15:30
 **/
@Component
public class Status {
    public boolean createStatusFlagProxy(String status) {
        List<String> list = new ArrayList<>();
        list.add("proxy");
        list.add("user");

        for (String s : list) {
            if (status.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
