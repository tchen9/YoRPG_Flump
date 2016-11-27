//Team Flump: Adris Jaoutakas, Tina Chen, Jesse Sit
//APCS pd 1
//HW31 -- Ye Olde Role Playing Game, Improved
//11-17-16

public abstract class Character extends Being{
    
    protected String name;
    protected int DEFAULT_STRENGTH,
                      DEFAULT_DEFENSE,
                      SPECIAL_STRENGTH,
                      SPECIAL_DEFENSE;

    public Character() {
        DEFAULT_STRENGTH = 0;
        DEFAULT_DEFENSE = 0;
        SPECIAL_STRENGTH = 0;
        SPECIAL_DEFENSE = 0;
    }

    public Character(int health, int default_strength, int default_defense, int special_strength, int special_defense) {
	super(health, default_strength, default_defense);
        DEFAULT_STRENGTH = default_strength;
        DEFAULT_DEFENSE = default_defense;
        SPECIAL_STRENGTH = special_strength;
        SPECIAL_DEFENSE = special_defense;
    }

    public Character(int health, String name, int default_strength, int default_defense, int special_strength, int special_defense) {
        this(health, default_strength, default_defense, special_strength, special_defense);
        this.name = name;
    }

   
    
    public String getName() {
        return name;
    }

    public abstract void specialize();

    public abstract void normalize();
    public abstract String about();
}
