public abstract class Monster extends Being{
    protected double attackRate;

    public Monster(){
	attackRate = 0;
    }
    public Monster(int health, int strength, int defense, double attackRate){
	super(health, strength, defense);
	this.attackRate = attackRate;
	this.strength = (int)(strength * attackRate);
    }
    public abstract String about();
}
