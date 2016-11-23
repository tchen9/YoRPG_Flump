//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW31 -- Ye Olde Role Playing Game, Improved
//11-17-16

public abstract class Character{
    
    protected int health;
    protected int strength, defense;
    protected double attackRate;
    protected String name;
    protected int DEFAULT_STRENGTH,
                      DEFAULT_DEFENSE,
                      SPECIAL_STRENGTH,
                      SPECIAL_DEFENSE;

    public Character() {
        health = 0;
        strength = 0;
        defense = 0;
        attackRate = 0;

        DEFAULT_STRENGTH = 0;
        DEFAULT_DEFENSE = 0;
        SPECIAL_STRENGTH = 0;
        SPECIAL_DEFENSE = 0;
    }

    public Character(int health, int default_strength, int default_defense, int special_strength, int special_defense, double attackRate) {
        this.health = health;
        DEFAULT_STRENGTH = default_strength;
        DEFAULT_DEFENSE = default_defense;
        SPECIAL_STRENGTH = special_strength;
        SPECIAL_DEFENSE = special_defense;
        strength = DEFAULT_STRENGTH;
        defense = DEFAULT_DEFENSE;
        this.attackRate = attackRate;
    }

    public Character(int health, String name, int default_strength, int default_defense, int special_strength, int special_defense, double attackRate) {
        this(health, default_strength, default_defense, special_strength, special_defense, attackRate);
        this.name = name;
    }

    public boolean isAlive() {
        return (health > 0);
    }

    public int getDefense() {
        return defense;
    }

    public void lowerHP(int damage) {
        health -= damage;
    }

    public int attack(Character opponent) {
        int damage = (int)(strength * attackRate) - opponent.getDefense();
        System.out.println(strength + " * " + attackRate + " - " + (opponent.getDefense()));
        opponent.lowerHP(damage);
        return damage;
    }
    
    public String getName() {
        return name;
    }

    public abstract void specialize();

    public abstract void normalize();
    public abstract String about();
}
