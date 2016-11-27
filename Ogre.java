public class Ogre extends Monster{

    public Ogre(int floor){
	this(0.6 + floor * 0.02);
    }
    public Ogre(double attackRate){
	super(75, 60, 20, attackRate);
    }
    public String about(){
	return "ogre";
    }
}
