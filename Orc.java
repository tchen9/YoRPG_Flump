public class Orc extends Monster{

    public Orc(int floor){
	this(0.8 + floor * 0.02);
    }
    public Orc(double attackRate){
	super(100, 75, 30, attackRate);
    }
    public String about(){
	return "orc";
    }
}
