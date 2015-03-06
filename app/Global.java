import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.*;
import com.avaje.ebean.Ebean;
import java.util.*;

/**
 * Created by annesofiestranderichsen on 03.03.15.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app){

        if(User.find.findRowCount() == 0){
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }

    }
}
