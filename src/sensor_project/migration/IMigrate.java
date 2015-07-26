package sensor_project.migration;

/**
 * Created by nachiket on 25/7/15.
 */
public interface IMigrate {

    // Implements initiation sequence for each MQTT implementation
    public void init();

    public int readData();

    public int connectToBroker();

    public int publishTopic();

    public int subscribeTopic();
}
