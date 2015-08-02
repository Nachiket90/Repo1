package sensor_project.Core;

import java.util.Properties;

/**
 * Created by nachiket on 27/7/15.
 */
public abstract class Broker {

    Properties p = new Properties();

    public Properties getP() {
        return p;
    }

    public void setP(Properties p) {
        this.p = p;
    }

    public abstract void init(Properties p);

    public abstract int publishTopic(String topic, String message);

    public abstract int subscribeTopic(String topic);
}
