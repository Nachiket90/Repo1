package sensor_project.Core;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Properties;


/**
 * Created by nachiket on 2/8/15.
 */
public class Paho extends Broker implements MqttCallback{

    MqttClient Client;
    private static String broker_address, clientId;
    private static int qos=0;

    @Override
    public void init(Properties p) {
        //Properties migrate_Config = new Properties();
        //FileInputStream fis = new FileInputStream("src/sensor_project/migration/migrate_Config.properties"); //put config properties file to buffer
        //migrate_Config.load(fis); //load config.properties file

        String topic = "MQTT Examples";
        String content = "Message from MqttPublishSample";
        //int qos             = 2;
        //String broker       = "tcp://iot.eclipse.org:1883";
        //String broker       = "tcp://localhost:1883";
        //String clientId     = "JavaSample";
        //MemoryPersistence persistence = new MemoryPersistence();

        try {
            //This is where you add your config variables:
            //DEBUG = Boolean.parseBoolean((String)migrate_Config.get("broker"));
            broker_address = p.getProperty("broker_address", "tcp://localhost:1883");
            System.out.println("broker is :" + broker_address);
            clientId = p.getProperty("clientID", "sample");
            System.out.println("clientID is :" + clientId);
            qos = Integer.parseInt(p.getProperty("qos", "0"));
            System.out.println("qos is" + qos);

            //fis.close();
            System.out.println("Settings file successfuly loaded");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int publishTopic(String topic, String message) {
        try {
            Client = new MqttClient(broker_address, clientId);
            System.out.println("Connecting to broker: "+broker_address);
            Client.connect();
            MqttMessage QMessage = new MqttMessage(message.getBytes());
            QMessage.setQos(qos);
            Client.publish(topic, QMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int subscribeTopic(String topic) {
        try {
            Client = new MqttClient(broker_address, clientId);
            //MqttConnectOptions connOpts = new MqttConnectOptions();
            //connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker_address);
            //sampleClient.connect(connOpts);
            Client.connect();
            Client.setCallback(this);
            Client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
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
