//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW31 -- Ye Olde Role Playing Game, Improved
//11-17-16

public class Monster extends Character{

    public Monster() {
        health = 100; 
        DEFAULT_DEFENSE = defense;
        attackRate = 0.85;

        DEFAULT_STRENGTH = (int)(Math.random() * 50.0) + 40;
        strength = DEFAULT_STRENGTH;
        defense = 20;
        DEFAULT_DEFENSE = defense;

        int inc = (int)(Math.random() * 10) + 5;
	SPECIAL_STRENGTH = DEFAULT_STRENGTH + inc;
        SPECIAL_DEFENSE = defense - inc;
    }
    public String about(){
	return "monster";
    }
    public void specialize(){
	strength = SPECIAL_STRENGTH;
	defense = SPECIAL_DEFENSE;
    }
    public void normalize(){
	strength = DEFAULT_STRENGTH;
	defense = DEFAULT_DEFENSE;
    }
}
