import java.util.Random;

public class SpecificMonster extends Creature {
    private int giveExpWhenKilled;
    private String deathText = null;
    private static Random staticRandom = new Random();

    public SpecificMonster(String name, int currentHp, int dmg, int giveExpWhenKilled, int extraDmg , int gold, int strength, int toughness) {
        super(name, currentHp , dmg, extraDmg, gold, strength, toughness);
        this.giveExpWhenKilled = giveExpWhenKilled;
    }

    @Override
    void deathScream() {
        int max = 5;
        int min = 0;
        int deathScreamAlt = staticRandom.nextInt((max - min) + 1) + min;
        switch (deathScreamAlt) {
            case 0:
                System.out.println("Arrrghh! See you in the underworld fool!");
                break;
            case 1:
                System.out.println("Huh! I hate you human! Arrrh!");
                break;
            case 2:
                System.out.println("Well fought human! Ahhhhh! Arhhgg!");
            case 3:
                System.out.println("Noooooo! arghhh! you will never reach level 10");
                break;
            case 4:
                System.out.println("Huh, huh, i dont want to die, maaamaa! arhhhg!");
            case 5:
                System.out.println("Your the most powerful creature i met, arrrghh! Now i can die i peace!");
        }

    }

    public int getGiveExpWhenKilled() {
        return giveExpWhenKilled;
    }

    public void setGiveExpWhenKilled(int giveExpWhenKilled) {
        this.giveExpWhenKilled = giveExpWhenKilled;
    }

    public String getDeathText() {
        return deathText;
    }

    public void setDeathText(String deathText) {
        this.deathText = deathText;
    }

}
