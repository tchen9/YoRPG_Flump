public class Dragon extends Monster{

    public Dragon(int floor){
	this(0.5 + floor * 0.02);
    }
    public Dragon(double attackRate){
	super(150, 85, 40, attackRate);
    }
    public String about(){
	return "dragon";
    }
}
