package Common;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameFramework
{

    // basic structure for game
    private ServerConnection server;
    private String userid = "";
    //add your game client window class here

    public GameFramework()
    {
        server = new ServerConnection(this, userid);
        //call your game client constructor
    }

    //Handle incoming messages
    protected void messageHandler()
    {
        //TODO: Message Logic once Message class is built by Trevor
    }

    //start a thread for server connection
    protected void startServer()
    {
        Thread thread = new Thread(server);
        thread.start();
    }

    /**
     * Code will need to change for your methods in your client window. This is the middle man
     * it will handle messages to the server and updating the client window
     *
     * i will program the message handling portion once trevor builds the class but you will need
     * to adapt it to your client as needed.
     */

}
