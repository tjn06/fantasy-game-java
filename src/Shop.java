import java.util.Scanner;

public class Shop {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ATTACK_AMULET = "1";
    private static final String ATTACK_SWORD = "2";
    private static final String DEFENSE_AMULET = "3";
    private static final String MAGIC_DEFENSE_SHIELD = "4";
    private static final String EXIT = "e";

    public void goToShop(Player player) {
        System.out.println("Welcome to the shop. What do you want to buy?");
        System.out.println("You have " + player.getGold() + " gold.\n");
        showShopItems();
        String choice;
        do {
            choice = SCANNER.nextLine();
            switch(choice) {
                case ATTACK_AMULET:
                    buy(180, player, "Attack amulet", 1, true);
                    break;
                case ATTACK_SWORD:
                    buy(290, player, "Attack sword", 2, true);
                    break;
                case DEFENSE_AMULET:
                    buy(180, player, "Defense amulet", 5, false);
                    break;
                case MAGIC_DEFENSE_SHIELD:
                    buy(290, player, "Magic defense shield", 10, false);
                    break;
                case EXIT:
                    System.out.println("\nYou left the shop\n");
                    break;
            }
            System.out.println("Buy something more or exit the shop.\n");
            showShopItems();
        } while(!choice.equals("e"));
        System.out.println("\nYou left the shop.");
    }

    public void buy(int price, Player player, String weapon, int powerNumber, boolean attackOrDefence) {
        if (player.getGold() < price) {
            System.out.println("You don't have enough money to buy the " + weapon);
        } else {
            player.giveGold(price);
            if(attackOrDefence) {
                player.setAddStrength(powerNumber);
            } else {
                player.setAddToughness(powerNumber);
            }
            System.out.println("You bought the " + weapon + "!");
            System.out.println("\nYou have " + player.getGold() + " gold left.");
        }
    }

    public void showShopItems() {
        System.out.println(
                "1. Attack amulet (+ 1 strength) - 180 gold\n" +
                "2. Attack sword (+ 2 strength) - 290 gold\n" +
                "3. Defense amulet ( + 5 toughness ) - 180 gold\n" +
                "4. Magic defense shield ( + 10 toughness ) - 290 gold\n" +
                "e. Exit shop");
    }

}
