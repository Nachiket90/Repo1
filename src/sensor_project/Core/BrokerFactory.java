package sensor_project.Core;

import java.util.Properties;

/**
 * Created by nachiket on 2/8/15.
 */
public class BrokerFactory {
    public static Broker newBroker(String brokername, Properties properties) throws UnknownBrokerException {

        ClassLoader classLoader = BrokerFactory.class.getClassLoader();

        Broker ret=null;

        try {
            Class brokerclass = classLoader.loadClass(brokername);
            System.out.println("brokerclass.getName() = " + brokerclass.getName());

            ret = (Broker)brokerclass.newInstance();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }

        ret.setP(properties);
        return ret;
    }

}
