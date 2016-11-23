//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW31 -- Ye Olde Role Playing Game, Improved
//11-17-16

public class Warrior extends Character{

    public Warrior(String name) {
        super(200, name, 70, 15, 90, 5, 0.9);
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
	return "\tWarrior: A being of the race Human. Raised from early childhood to fight for the human race, they are cladded in armor and wield a shield in one hand and a sword in the other. They will cut down any enemy that appears before them.\n";
    }
}
