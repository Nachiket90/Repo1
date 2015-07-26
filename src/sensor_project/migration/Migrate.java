package sensor_project.migration;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by nachiket on 25/7/15.
 */
public class Migrate implements IMigrate, MqttCallback {

    MqttClient sampleClient;
    public static String broker, clientId;
    public static int qos=0;


    public Migrate() {

    }

    public static void main(String[] args) {
        new Migrate().demo();
    }

    public void demo() {
        String topic        = "MQTT Examples";
        String content      = "Message from MqttPublishSample";
        //int qos             = 2;
        //String broker       = "tcp://iot.eclipse.org:1883";
        //String broker       = "tcp://localhost:1883";
        //String clientId     = "JavaSample";
        MemoryPersistence persistence = new MemoryPersistence();

        try {

            Properties migrate_Config = new Properties();
            FileInputStream fis = new FileInputStream("src/sensor_project/migration/migrate_Config.properties"); //put config properties file to buffer
            migrate_Config.load(fis); //load config.properties file

            //This is where you add your config variables:
            //DEBUG = Boolean.parseBoolean((String)migrate_Config.get("broker"));
            broker = migrate_Config.getProperty("broker", "tcp://localhost:1883");
            System.out.println("broker is :" + broker);
            clientId = migrate_Config.getProperty("clientID", "sample");
            System.out.println("clientID is :" + clientId);
            qos = Integer.parseInt(migrate_Config.getProperty("qos","0"));
            System.out.println("qos is" + qos);

            fis.close();
            System.out.println("Settings file successfuly loaded");



            sampleClient = new MqttClient(broker, clientId, persistence);
            //MqttConnectOptions connOpts = new MqttConnectOptions();
            //connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            //sampleClient.connect(connOpts);
            sampleClient.connect();
            sampleClient.setCallback(this);
            sampleClient.subscribe("MQTT Examples");

            System.out.println("Connected");
            System.out.println("Publishing message: "+content);

            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);

            sampleClient.publish(topic, message);
            System.out.println("Message published");

            //sampleClient.disconnect();
            //System.out.println("Disconnected");
            //System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init() {

    }

    @Override
    public int readData() {
        return 0;
    }

    @Override
    public int connectToBroker() {
        return 0;
    }

    @Override
    public int publishTopic() {
        return 0;
    }

    @Override
    public int subscribeTopic() {
        return 0;
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("msg received :" + mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

}
