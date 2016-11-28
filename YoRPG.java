/*=============================================
  class YoRPG -- Driver file for Ye Olde Role Playing Game.
  Simulates monster encounters of a wandering adventurer.
  Required classes: Warrior, Monster
  =============================================*/

import java.io.*;
import java.util.*;
import core.Game;

public class YoRPG
{
    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~

    //each round, a Warrior and a Monster will be instantiated...
    private Character pat;   //Is it man or woman?
    private Monster smaug; //Friendly generic monster name?

    private int moveCount;
    private boolean gameOver;
    private int difficulty;
    private int floor;

    private InputStreamReader isr;
    private BufferedReader in;
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~ DEFAULT CONSTRUCTOR ~~~~~~~~~~~
    public YoRPG()
    {
	moveCount = 0;
	gameOver = false;
	isr = new InputStreamReader( System.in );
	in = new BufferedReader( isr );
	newGame();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    // ~~~~~~~~~~~~~~ METHODS ~~~~~~~~~~~~~~~~~~~

    /*=============================================
      void newGame() -- gathers info to begin a new game
      pre:  
      post: according to user input, modifies instance var for difficulty 
      and instantiates a Warrior
      =============================================*/
    public void newGame()
    {
	floor = 1;
	String s;
	String name = "";
	int player = 0;

        String graphics = "n";
        System.out.println("Enable Experimental Graphics? (y/n)");
        try {
            graphics = in.readLine();
        } catch ( IOException e ) {}

        if (graphics.equals("y")) {
            new Game().start();
        } else {
    
             s = "~~~ Welcome to Ye Olde RPG! ~~~\n";
       
             s += "\nChoose your difficulty: \n";
             s += "\t1: Easy\n";
             s += "\t2: Not so easy\n";
             s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
             s += "Selection: ";
             System.out.print( s );
       
             try {
                 difficulty = Integer.parseInt( in.readLine() );
             }
             catch ( IOException e ) { }
       
             s = "Intrepid warrior, what doth thy call thyself? (State your name): ";
             System.out.print( s );
       
             try {
                 name = in.readLine();
             }
             catch ( IOException e ) { }
             
             s = "Class Info:\n";
             s += "\t1: Warrior\n";
             s += "\t2: Mage\n";
             s += "\t3: Archer\n";
             s += "\t4: Healer\n";
             s += "\t5: Giant\n";
             s += "\t6: I'm ready\n";
             s += "Selection: ";
             System.out.print( s );
       
             while (player != 6){
             	    
             	try{
             	    player = Integer.parseInt( in.readLine() );
             	}
             	catch ( IOException e ) { }
             
             	//instantiate the player's character
             	if (player == 1){
             	    pat = new Warrior( name );
             	    System.out.println(pat.about());
                 
             	}
             	if (player == 2){
             	    pat = new Mage( name );
             	    System.out.println(pat.about());
             	}
             	if (player == 3){
             	    pat = new Archer( name );
             	    System.out.println(pat.about());
             	}
             	if (player == 4){
             	    pat = new Healer( name );
             	    System.out.println(pat.about());
             	}
             	if (player == 5){
             	    pat = new Giant( name );
             	    System.out.println(pat.about());
             	}
             	if (player == 6)
             	    break;
             	System.out.println(s);
            }
            startGame();
        }
    }//end newGame()

    public void startGame(){
	int room = 1;
	System.out.println("\nIn front of you stands a great tower. You look up and see the princess at the top floor. You exclaim, ' I'm coming fair maiden!' and rush in.");
	while (floor <= 5){
	    if (pat.getHealth() <= 0)
		break;
	    while (room <= 5){
		System.out.println( "\nYou are in Room " + room + " of floor " + floor + ".");
		if (Math.random() >= 0.25){
		    if (!playTurn())
			break;
		    else {
			room += 1;
		    }
		}
		else {
		    if (Math.random() < 0.5){
			System.out.println("\nYay, you got a powerup");
			pat.strength += 5;
			pat.defense += 5;
			pat.DEFAULT_STRENGTH += 5;
			pat.DEFAULT_DEFENSE += 5;
			pat.SPECIAL_STRENGTH += 5;
			pat.SPECIAL_STRENGTH += 5;
			System.out.println("\nStrength: " + pat.getStrength() + "\nDefense: " + pat.getDefense());
			room += 1;
		    }
		    else {
			System.out.println("\nYay, you got a health potion");
			pat.health += 200;
			System.out.println("\nHealth: " + pat.getHealth());
			room += 1;
		    }
		}
	    }
	    room = 1;
	    floor += 1;
	}
	if (floor == 6){
	    System.out.println("\nYou reach the top floor of the tower and run out onto the roof. As you rush to untie the princess, the Boss monster eats her and you stare dumbfounded.");
	}
    }

    /*=============================================
      boolean playTurn -- simulates a round of combat
      pre:  Warrior pat has been initialized
      post: Returns true if player wins (monster dies).
      Returns false if monster wins (player dies).
      =============================================*/
    public boolean playTurn()
    {
	int i = 1;
	int d1, d2;
	double monsterType = Math.random();

	if ( Math.random() >= ( difficulty / 3.0 ) )
	    System.out.println( "\nNothing to see here. Move along!" );
	else {

	    if (monsterType < 0.25){
		smaug = new Goblin(floor);
	    }
	    else if (monsterType < 0.5){
		smaug = new Ogre(floor);
	    }
	    else if (monsterType < 0.75){
		smaug = new Orc(floor);
	    }
	    else {
		smaug = new Dragon(floor);
	    }

	    System.out.println( "\nLo, yonder " + smaug.about() + "  approacheth!" );
	    while( smaug.isAlive() && pat.isAlive() ) {

		// Give user the option of using a special attack:
		// If you land a hit, you incur greater damage,
		// ...but if you get hit, you take more damage.
		try {
		    System.out.println( "\nDo you feel lucky?" );
		    System.out.println( "\t1: Nay.\n\t2: Aye!" );
		    i = Integer.parseInt( in.readLine() );
		}
		catch ( IOException e ) { }

		if ( i == 2 )
		    pat.specialize();
		else
		    pat.normalize();

		d1 = pat.attack( smaug );
		d2 = smaug.attack( pat );

		System.out.println( "\n" + pat.getName() + " dealt " + d1 +
				    " points of damage.");

		System.out.println( "\nThe " + smaug.about() + "  smacked " + pat.getName() +
				    " for " + d2 + " points of damage.");
		if (smaug.isAlive() && pat.isAlive()){
		    System.out.println( "\nThe wretched beast still stand!");
		}
	    }//end while

	    //option 1: you & the monster perish
	    if ( !smaug.isAlive() && !pat.isAlive() ) {
		System.out.println( "\n'Twas an epic battle, to be sure... " + 
				    "You cut ye olde monster down, but " +
				    "with its dying breath ye olde monster. " +
				    "laid a fatal blow upon thy skull." );
		return false;
	    }
	    //option 2: you slay the beast
	    else if ( !smaug.isAlive() ) {
		System.out.println( "\nHuzzaaH! Ye olde monster hath been slain!" );
		return true;
	    }
	    //option 3: the beast slays you
	    else if ( !pat.isAlive() ) {
		System.out.println( "\nYe olde self hath expired. You got dead." );
		return false;
	    }
	}//end else

	return true;
    }//end playTurn()
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static void main( String[] args )
    {
	//As usual, move the begin-comment bar down as you progressively 
	//test each new bit of functionality...

	
	//loading...
	YoRPG game = new YoRPG();
	
	System.out.println( "\nThy game doth be over." );
	/*================================================
	  ================================================*/
    }//end main

}//end class YoRPG


