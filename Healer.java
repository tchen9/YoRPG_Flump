//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW32 -- Ye Olde Role Playing Game, Expanded
//11-19-16

public class Healer extends Character{

    public Healer(String name){
	super(100, name, 50, 20, 125, 15, 0.7);
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
	return "\tHealer: A being of the race Human. They dedicate their life's work to understanding all the properties of different herbs. During peace, they are the the town's healer, treating all kinds of diseases. During war, they brew up poisonous concontions which can kill an ogre with a single gulp or even a splash.\n";
    }
}
