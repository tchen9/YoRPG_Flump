public abstract class Being{
    protected int health;
    protected int strength, defense;

    public Being(){
	health = 0;
	strength = 0;
	defense = 0;
    }
    public Being(int health, int strength, int defense){
	this.health = health;
	this.strength = strength;
	this.defense = defense;
    }
    public boolean isAlive() {
        return (health > 0);
    }

    public int getDefense() {
        return defense;
    }
    public int getStrength(){
	return strength;
    } public int getHealth(){
	return health;
    }
    public void lowerHP(int damage) {
        health -= damage;
    }
     public int attack(Being opponent) {
        int damage = strength - opponent.getDefense();
        opponent.lowerHP(damage);
        return damage;
    }
    public abstract String about();
}
    
