package Common;

import java.io.*;
import java.net.Socket;

/**
 * Created by Clinton on 7/16/2015.
 */
public class ServerConnection implements Runnable
{
    private String userid;
    private GameFramework gameFramework;
    private ObjectOutputStream out;
    private Socket connection;

    public ServerConnection(GameFramework framework, String user)
    {
        gameFramework = framework;
        userid = user;
    }


    @Override
    public void run()
    {
        try
        {
            connection = new Socket("localhost",8989);
            out = new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
            out.writeUTF(userid);
            out.flush();
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));
            while(!connection.isClosed() && connection.isConnected()) {
                //TODO: Typecast input to NavalMessage object once Trevor builds class.
                //incomingMessage;
                try
                {
                    //incomingMessage = () in.readObject();
                    //gameFramework.messageHandler(incomingMessage);
                }catch (NullPointerException e)
                {

                }//catch (ClassNotFoundException e)
                //{
                    //TODO: Handle class not found exception
                //}
            }

        }catch (IOException e)
        {

        }

    }

    protected void sendMessage(/*Messageclass message*/)
    {
        try
        {
            //out.writeObject(message);
            out.flush();
        }catch (IOException e)
        {

        }
    }
}
