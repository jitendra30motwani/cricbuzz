/**
 * 
 */
package design.lld.cricbuzz;

import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author 91978
 * 
 * 1. Requirements Gathering
 * 
 * List all the live matches
 * Display scorecard of the match
 * Display scorecrad in innings form
 * 
 * 2. Flow
 * 
 * Match -> Team -> Player -> ScoreCard
 * 
 * 3. Object Indentification
 * 
 * Match/Team/Player/Innings/Over/Scorecard
 * 
 * 
 *
 */
public class CricBuzzLldExample {

	/**
	 * 
	 */
	public CricBuzzLldExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Match{
	
	Team A;
	Team B;
	Date date;
	String venue;
	Innings innnings[];
	MatchType matchType;
	Team tossWinner;
	
	public void startMatch() {
		
	}
	
}

class Team{
	
	String name;
	Queue<Player> p11;
	List<Player> extras;
	
	PlayerBattingController batControl;
	PlayerBowlingController bowlControl;
	
	
}

class Innings{
	
	Team batting;
	Team bowling;
	
	List<Over> overs;
	
	public void startInning() {
		
	}
}

interface MatchType{
	
	int overs();
	int maxOveryByBowler();

}

class OneDay implements MatchType{

	@Override
	public int overs() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public int maxOveryByBowler() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	
	
}

class T20 implements MatchType{

	@Override
	public int overs() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public int maxOveryByBowler() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	
	
}

class Player{
	
	Person person;
	PlayerType type;
	BattingScoreCard batCard;
	BowlingScoreCard bowlCard;
	
}

class PlayerBattingController{
	
	Queue<Player> yetToBat;
	
	Player striker;
	Player nonStriker;
}

class PlayerBowlingController{
	
	Deque<Player> bowlers;
	
	Map<Player,Integer> bowlerOversCount;
	
	Player currentBowler;
	
}

class Over{
	
	int overNo;
	List<Ball> balls;
	
	public void startOver() {
		
	}
	
}

class Person{
	String name;
	int age;
	Address address;
}

enum PlayerType{
	
	BATSMAN,BOWLER,ALLROUNDER,WICKETKEEPER;
	
}


class BattingScoreCard{
	
	int runs;
	int balls;
	int fours;
	int sixs;
	float strikeRate;
	
}

class BowlingScoreCard{
	
	int overs;
	int wickets;
	int runs;
	int mdns;
	int nbls;
	int wbls;
	float economyRate;
}

class Ball{
	
	int ballNo;
	Player bowledBy;
	Player playedBy;
	BallType btype;
	RunType rType;
	Wicket wicket;
	
	List<ScoreCardUpdaterObserver> scoreCardUpdaters;
	
	public Ball() {
		
		scoreCardUpdaters= new ArrayList<>();
		scoreCardUpdaters.add(new BattingScoreCardUpdater());
		scoreCardUpdaters.add(new BowlingScoreCardUpdater());
	}
	
	public void startBowlDelivery() {
		
		this.notifys();
		
	}
	
	public void notifys(){
		for(ScoreCardUpdaterObserver scoreCardUpdaterObserver: scoreCardUpdaters) {
			scoreCardUpdaterObserver.update(this);
		}
	}
	
}

class Address{
	
	String city;
	String state;
	String street;
	int pincode;
	
}

enum BallType{
	NORMAL,WIDE,NO;
}

enum RunType{
	ONE,TWO,THREE,FOUR,SIX,WIDE;
}

class Wicket{
	
	Player wicketBy;
	WicketType wType;
	Ball ball;
	
}

enum WicketType{
	BOWLED,RUNOUT,CATCH;
}

interface ScoreCardUpdaterObserver{
	
	void update(Ball ball);
	
}

class BattingScoreCardUpdater implements ScoreCardUpdaterObserver{

	@Override
	public void update(Ball ball) {
		// TODO Auto-generated method stub
		Player player= ball.playedBy;
		player.batCard.runs+= (ball.rType==RunType.ONE ? 1 : 0);
	}
	
	
	
}


class BowlingScoreCardUpdater implements ScoreCardUpdaterObserver{

	@Override
	public void update(Ball ball) {
		// TODO Auto-generated method stub
		Player player= ball.bowledBy;
		player.bowlCard.runs+= (ball.rType==RunType.ONE ? 1 : 0);
	}
	
	
	
}