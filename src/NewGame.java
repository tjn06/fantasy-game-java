import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class NewGame {
    private static Random staticRandom = new Random();
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int GO_ADVENTURE = 1;
    public static final int SHOW_DETAILS = 2;
    public static final int GO_TO_SHOP = 3;
    public static final int EXIT = 4;

    public static void main(String[] args) {
        boolean goAdventure = true;
        int round = 0;

        /**
         * To survive the game, you need to actively visit the shop
         * and buy attack and defense items, since the monsters get tougher and tougher.
         * If you want to die quickly, increase the damage values of (SpecificMonster) m1, m2, and m3.
         * If you want to kill the monsters quickly, increase (Person) p1's damage.
         */
        
        MonsterNames monsterNames = new MonsterNames();
        Shop shop = new Shop();
        ArrayList<SpecificMonster> monsterArray = new ArrayList<SpecificMonster>();
        SpecificMonster m1 = new SpecificMonster("Groothy", 150, 25, 35, 20,100,0,0);
        SpecificMonster m2 = new SpecificMonster("Gloryip", 200, 35, 50, 25, 200, 0,0);
        SpecificMonster m3 = new SpecificMonster("Dooda-da-da", 250, 45, 65, 35, 300, 0,0);
        monsterArray.add(m1);
        monsterArray.add(m2);
        monsterArray.add(m3);
        Player p1 = new Player("", 500, 50, 1, 0, 60, 0,0,0);
        welcomeToTheGame();
        p1.setName(SCANNER.nextLine());

        do {
            SpecificMonster currentMonster = getEnemy(monsterArray, round);
            showMenu();

            boolean nothingHappens = staticRandom.nextInt(10) <= 0;
            boolean showMenuAgain = false;

            int choice = (SCANNER.nextInt());
            switch (choice) {
                case GO_ADVENTURE:
                    goAdventure = true;
                    break;
                case SHOW_DETAILS:
                    System.out.println("Show stats");
                    System.out.println(p1.toString());
                    showMenuAgain = true;
                    break;
                case GO_TO_SHOP:
                    shop.goToShop(p1);
                    showMenuAgain = true;
                    break;
                case EXIT:
                    System.out.println("You ended the game, bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, choose a number between 1-4");
                    showMenuAgain = true;
            }

            if (!showMenuAgain) {
                if (nothingHappens) {
                    smallChanceNoFight();
                } else {
                    System.out.println("You meet a " + currentMonster.getName() + "!" + "\nTotal number of encounters with monsters: " + (round+1));
                    while (currentMonster.getCurrentHp() > 0 || p1.getCurrentHp() > 0) {
                        promptEnterKey();
                        p1.attack(currentMonster);
                        System.out.println("------------------------------------------------\n" + p1.getName() + " attacking " + currentMonster.getName());
                        delayFightEvent(".",200,5);
                        System.out.println(p1.getName() + " dealing " + p1.getTotalDmg() +" damage on " + currentMonster.getName() +
                                "\n------------------------------------------------");
                        if (currentMonster.getCurrentHp() < 1) { //If monster dies
                            p1.setExp(currentMonster.getGiveExpWhenKilled());
                            p1.pickGold(currentMonster.getGold());
                            p1.increaseLevel();
                            System.out.println();
                            currentMonster.deathScream();
                            System.out.println("*****************************************************\n"+
                                    "You killed " + currentMonster.getName() +
                                    ", gaining " + currentMonster.getGiveExpWhenKilled() + " experience and " + currentMonster.getGold() + " gold!\n" +
                                    "You are level " + p1.getLevel() + ", have " + p1.getExp() + " exp, " + p1.getCurrentHp() + " hp " +
                                    "and " + p1.getGold() + " gold." +
                                    "\n*****************************************************");
                            levelUpAndRestoreMonster(currentMonster, p1.getLevel(), monsterNames);

                            if (p1.getLevel() >= 10) {
                                System.out.println(
                                        "\n*****************************************************\n" +
                                        "Congratulations! You reached level 10, you won the game!\n" +
                                        "*****************************************************");
                                goAdventure = false;
                            }
                            break;
                        }
                        System.out.println(currentMonster.getName() + " attacks you ");
                        currentMonster.attack(p1);
                        delayFightEvent(".",300,5);
                        System.out.println(currentMonster.getName() + " dealing " + currentMonster.getTotalDmg() +
                                " damage on " + p1.getName() + "\n------------------------------------------------");
                        if (p1.getCurrentHp() < 1) { //If player dies
                            p1.deathScream();
                            System.out.println("You where killed by the monster, game over!");
                            goAdventure = false;
                            break;
                        }
                        System.out.println(p1.getName() + ": " + p1.getCurrentHp() + " hp");
                        System.out.println(currentMonster.getName() + ": " + currentMonster.getCurrentHp() + " hp" +
                                "\n------------------------------------------------");
                    }
                }
            }
        } while(goAdventure);

    }//End Main

    public static void showMenu() {
        System.out.println("\n1. Go adventure");
        System.out.println("2. Show details");
        System.out.println("3. Go to shop");
        System.out.println("4. Exit Game");
        System.out.print("\nChoice? ");
    }

    public static void welcomeToTheGame(){
        System.out.println("**************************");
        System.out.println("* Welcome To Groothland! *");
        System.out.println("**************************");
        System.out.println("What's your name?");
    }

    public static void smallChanceNoFight (){
        System.out.println("You keep on walking, waiting for a monsterfight!");
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to attack monster...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void delayFightEvent(String output, int time, int count) {
        try {
            for (int i = 0; i < count; i++) {
                Thread.sleep(time);
                System.out.print(output);
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        System.out.println();
    }

    public static SpecificMonster getEnemy(ArrayList<SpecificMonster> arr, int round) {
        if (round >= arr.size())  {
            int randomIndex = staticRandom.nextInt(arr.size() - 1);
            System.out.println(randomIndex);
            return arr.get(randomIndex);
        } else {
            return arr.get(round);
        }
    }

    public static void levelUpAndRestoreMonster(SpecificMonster monster, int level, MonsterNames monsterNames) {
        monster.setCurrentHp(monster.getMaxHp());
        monster.setName(monsterNames.getRandomMonsterName());
        if (level >= 0 && level <=3 ) {
            monster.setStrength(4);
            monster.setToughness(5);
        } else if (level >= 4 && level <= 5 ) {
            monster.setStrength(6);
            monster.setToughness(25);
        } else if (level >= 6 && level <=8 ) {
            monster.setStrength(monster.getStrength() + 2);
            monster.setToughness(35);
        } else if (level >= 9 ) {
            monster.setStrength(monster.getStrength() + 1);
            monster.setToughness(100);
        }
    }

}
