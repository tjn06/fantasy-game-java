import java.util.Random;

public abstract class Creature {
    private static Random staticRandom = new Random();
    private String name;
    private final int maxHp;
    private int currentHp;
    private int dmg;
    private int extraDmg;
    private int totalDmg;
    private int gold;
    private int strength;
    private int toughness;

    public Creature(String name, int currentHp, int dmg, int extraDmg, int gold, int strength, int toughness) {
        this.name = name;
        this.dmg = dmg;
        this.currentHp = currentHp;
        this.maxHp = currentHp; //Not in use
        this.extraDmg = extraDmg;
        this.gold = gold;
        this.strength = strength;
        this.toughness = toughness;
    }

    public void attack(Creature attackedCreature) {
        setDmgAndDefense(attackedCreature.getToughness());
        attackedCreature.currentHp -= this.totalDmg;
    }

    public void setDmgAndDefense(int opToughness) {
        int purchasedWeaponDmg = generatePurchasedWeaponDmg();
        int randomExtraDmg = generateRandomExtraDmg();
        int sumOfDmgs = this.dmg + randomExtraDmg + purchasedWeaponDmg;
        if (opToughness > sumOfDmgs ) {
            System.out.println("Defense boost");
            this.totalDmg = 1;
        } else {
            this.totalDmg = sumOfDmgs - opToughness;
        }
    }

    public int generatePurchasedWeaponDmg () {
        int randomWeaponRangeDmg = (this.strength * this.strength) - this.strength + 1;
        int purchasedWeaponDmg = (this.strength > 0 ) ? staticRandom.nextInt(randomWeaponRangeDmg) + this.strength : 0;
        return purchasedWeaponDmg;
    }

    abstract void deathScream();

    public int generateRandomExtraDmg() {
        return (this.extraDmg > 0 ) ? staticRandom.nextInt(this.extraDmg) : 0;
    }

    //Getter & setters
    public int getStrength() {
        return strength;
    }

    public void setAddStrength(int strength) {
        this.strength += strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setAddToughness(int toughness) {
        this.toughness += toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getGold() {
        return gold;
    }

    public void giveGold(int payment) {
        this.gold =  this.gold - payment;
    }

    public void pickGold(int pickedGold) {
        this.gold += pickedGold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getExtraDmg() {
        return extraDmg;
    }

    public void setExtraDmg(int extraDmg) {
        this.extraDmg = extraDmg;
    }

    public int getTotalDmg() {
        return totalDmg;
    }

    public void setTotalDmg(int totalDmg) {
        this.totalDmg = totalDmg;
    }




}
