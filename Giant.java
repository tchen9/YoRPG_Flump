//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW32 -- Ye Olde Role Playing Game, Expanded
//11-19-16

public class Giant extends Character{
    
    public Giant(String name){
	super(300, name, 120, 15, 200, 2, 0.4);
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
	return "\tGiant: A being of the race Giant. Ranging from heights of 3 to 5 meters, these beings completely tower over all other races. Giants are usually peaceful and misunderstood creatures, but when the well-being of a member of their tribe, especially a family memeber, is threatened, they will stop at nothing to keep them in safety.\n";
    }
}
