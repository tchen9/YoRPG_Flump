//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW32 -- Ye Olde Role Playing Game, Expanded
//11-19-16

public class Archer extends Character{

    public Archer(String name){
	super(850, name, 60, 15, 65, 12);
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
	return "\tArcher: A being of the race Elfin. They have a weak body and rely on their accuracy to shoot and disable their enemies. Due to their long history of war and bloodshed, Elfins generally distrust humans.\n";
    }
}
