public class Goblin extends Monster{

    public Goblin(int floor){
	this(0.5 + floor * 0.02);
    }
    public Goblin(double attackRate){
	super(25, 50, 20, attackRate);
    }
    public String about(){
	return "goblin";
    }
}
