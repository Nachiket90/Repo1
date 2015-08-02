package sensor_project.Core;

/**
 * Created by nachiket on 2/8/15.
 *
 * Could not find expected broker
 */
public class UnknownBrokerException extends Exception{

    private static final long serialVersionUID = 459099842269616836L;

    public UnknownBrokerException(String message)
    {
        super(message);
    }

    public UnknownBrokerException()
    {
        super();
    }

    public UnknownBrokerException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public UnknownBrokerException(Throwable cause)
    {
        super(cause);
    }

}
