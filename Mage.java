//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW32 -- Ye Olde Role Playing Game, Expanded
//11-19-16

public class Mage extends Character{
    
    public Mage(String name){
	super(900, name, 150, 20, 100, 90);
    }
    public void specialize(){
	strength = SPECIAL_STRENGTH;
	defense = SPECIAL_DEFENSE;
    }
    public void normalize(){
	strength = DEFAULT_STRENGTH;
	defense = DEFAULT_DEFENSE;
    }
    public String about(){
	return "\tMage: A being of the race Human. Through their magic, mages have a long lifespan lasting anywhere from 400 to 500 years. Much of their time is spent in the Great Mage Libraries hidden throughout the world, but when their help is needed, they appear on their own.\n";
    }
}
