//package Common;
//
///**
// * Created by Clinton on 7/16/2015.
// */
//public class GameFramework
//{
//
//    // basic structure for game
//    private ServerConnection server;
//    private String userid = "";
//    //add your game client class here
//
//    public GameFramework()
//    {
//        server = new ServerConnection(this, userid);
//        //call your game client constructor
//        //startServer();
//    }
//
//    //Handle incoming messages
//    protected void messageHandler(Message message)
//    {
//        if(message.getMessageType().equals(Message.Type.CHAT))
//        {
//            message = (ChatMessage) message;
//            //TODO: CHAT message
//            return;
//        }
//        if(message.getMessageType().equals(Message.Type.ACKNOWLEDGE) || message.getMessageType().equals(Message.Type.DENY))
//        {
//            message = (ResponseMessage) message;
//            //TODO: Response Message
//            return;
//        }
//        if(message.getMessageType().equals(Message.Type.TIMER))
//        {
//            message = (TimerMessage) message;
//            //TODO: Timer Message
//            return;
//        }
//        if(message.getMessageType().equals(Message.Type.START_GAME) || message.getMessageType().equals(Message.Type.END_GAME)
//                || message.getMessageType().equals(Message.Type.OBSERVE_GAME) ||message.getMessageType().equals(Message.Type.JOIN_GAME))
//        {
//            message = (StatusMessage) message;
//            //TODO: Status Message
//            return;
//        }
//        if(message.getMessageType().equals(Message.Type.GAME))
//        {
//            message = (GameMessage) message;
//            //TODO: Game Message
//            return;
//        }
//    }
//
//    //Send messages through ServerConnection.
//    protected void sendServerMessage(Message message)
//    {
//        server.sendMessage(message);
//    }
//
//    //start a thread for server connection
//    protected void startServer()
//    {
//        Thread thread = new Thread(server);
//        thread.start();
//    }
//
//    /**
//     * Code will need to change for your methods in your client window. This is the middle man
//     * it will handle messages to the server and updating the client window
//     *
//     * i will program the message handling portion once trevor builds the class but you will need
//     * to adapt it to your client as needed.
//     */
//
//}
