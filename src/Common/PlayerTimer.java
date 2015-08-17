package Common;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerTimer extends TimerTask{
	private Timer timer;
	private int seconds;
	//private BattleShipClient battle_ship_client;		//use your own client window
	
    public PlayerTimer(int seconds){ //, BattleShipClient battle_ship_client) {
        this.seconds = seconds;
        //this.battle_ship_client = battle_ship_client;
    	timer = new Timer();
    	timer.scheduleAtFixedRate(this, 0, 1000);
	}

	@Override
	public void run() {
		int min = seconds/60;
		int sec = seconds%60;
		//battle_ship_client.timerLabel.setText(String.format("  Time: %02d:%02d", min, sec));		//set your message in your window
		seconds--;
		if(seconds < 0){	
            timer.cancel();
        }
	}
}
