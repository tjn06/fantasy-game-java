import java.util.Random;

public class Player extends Creature {
    private static Random staticRandom = new Random();
    private int level;
    private int exp;
    private int maxExp;

    public Player(String name, int currentHp, int dmg, int level, int exp, int extraDmg, int gold, int strength, int toughness ) {
        super(name, currentHp ,dmg, extraDmg, gold, strength, toughness);
        this.level = level;
        this.exp = exp;
        this.maxExp = 150;
    }

    public void increaseLevel(){
        if (this.exp >= this.maxExp) {
            this.exp = 0;
            this.level++;
            int gainedHpRandom = (staticRandom.nextInt(80) + 200);
            int currHP = super.getCurrentHp();
            super.setCurrentHp(currHP + gainedHpRandom);
            System.out.println("You leveled up to " + this.level + " and gained " + gainedHpRandom + " HP!");
        }
    }

    @Override
    void deathScream() {
        System.out.println("Arrgh! Huh! Tell Starly and the kids i love them in the name of Gandron!");
    }

    //Getters & setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = this.exp + exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    @Override
    public String toString() {
        return "*********************" +
                "\n* Name: " + super.getName() +
                "\n* Level: " + this.level +
                "\n* Hp: " + super.getCurrentHp() + "/" + super.getMaxHp() +
                "\n* Exp: " + this.exp + "/" + this.maxExp +
                "\n* Gold: " + super.getGold() +
                "\n* Strength: " + super.getStrength() +
                "\n* Toughness: " + super.getToughness() +
                "\n*********************";
    }
    
}
