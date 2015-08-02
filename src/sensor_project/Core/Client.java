package sensor_project.Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by nachiket on 2/8/15.
 */
public class Client {
    public static void main(String[] args) {

        try {
            Properties config = new Properties();
            FileInputStream fis = new FileInputStream("src/sensor_project/Core/migrate_Config.properties"); //put config properties file to buffer
            config.load(fis); //load config.properties file

          //  ClassLoader classLoader = Client.class.getClassLoader();

            //transformation class invoke
            //encryption class invoke
            //broker class invoke

            Broker broker=null;

            try {
                broker = BrokerFactory.newBroker(config.getProperty("broker"), config);
                broker.init(config);
                broker.publishTopic(config.getProperty("topic"),"this is raw text");

            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
