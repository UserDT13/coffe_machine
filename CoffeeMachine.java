import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        String actionInput, checkRessources;
        String coffeeToBuy;
        boolean endProgramm = false;
        int[][] coffeeTypes = {{250, 0, 16, 1, 4}, {350, 75, 20, 1, 7}, {200, 100, 12, 1, 6}};
        int[] actualRessources = {400, 540, 120, 9, 550};


        while (!endProgramm) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            actionInput = scanner.nextLine();

            switch (actionInput) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    coffeeToBuy = scanner.nextLine();
                    if (!coffeeToBuy.equals("back")) {
                        if (checkValidCoffeeType(coffeeToBuy)) {
                            checkRessources = checkRessources(actualRessources, coffeeTypes, Integer.parseInt(coffeeToBuy));
                            if (!checkRessources.equals("enough Ressources")) {
                                System.out.printf("Sorry, not enough %s!\n", checkRessources);
                            } else {
                                System.out.println("I have enough resources, making you a coffee!");
                                actionBuy(actualRessources, coffeeTypes, Integer.parseInt(coffeeToBuy));
                            }
                        }
                    }                    
                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add:");
                    actualRessources[0] += scanner.nextInt();
                    System.out.println("Write how many ml of milk you want to add:");
                    actualRessources[1] += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    actualRessources[2] += scanner.nextInt();
                    System.out.println("Write how many disposable cups you want to add:");
                    actualRessources[3] += scanner.nextInt();
                    break;
                case "take":
                    actionTake(actualRessources);
                    break;
                case "remaining":
                    printQuantatySupplies(actualRessources);
                    break;
                case "exit":
                    endProgramm = true;
                    break;
                default:
                    break;
            }
        }
    }

    public static void printQuantatySupplies(int[] actualRessources) {
        System.out.printf("""
            \nThe coffee machine has:
            %d ml of water
            %d ml of milk
            %d g of coffee beans
            %d disposable cups
            $%d of money\n
                """, actualRessources[0], actualRessources[1], actualRessources[2], actualRessources[3], actualRessources[4]);
    }

    public static void actionBuy(int[] actualRessources, int[][] coffeeTypes, int coffeeToBuy) {
        actualRessources[0] -= coffeeTypes[coffeeToBuy - 1][0];
        actualRessources[1] -= coffeeTypes[coffeeToBuy - 1][1];
        actualRessources[2] -= coffeeTypes[coffeeToBuy - 1][2];
        actualRessources[3] -= coffeeTypes[coffeeToBuy - 1][3];
        actualRessources[4] += coffeeTypes[coffeeToBuy - 1][4];
    }

    public static String checkRessources(int[] actualRessources, int[][] coffeeTypes, int coffeeToBuy) {
        if (actualRessources[0] - coffeeTypes[coffeeToBuy - 1][0] < 0) {
            return "water";
        } else if (actualRessources[1] - coffeeTypes[coffeeToBuy - 1][1] < 0) {
            return "milk";
        } else if (actualRessources[2] - coffeeTypes[coffeeToBuy - 1][2] < 0) {
            return "coffee beans";
        } else if (actualRessources[3] - coffeeTypes[coffeeToBuy - 1][3] < 0) {
            return "disposable cups";
        }
        return "enough Ressources";
    }

    public static void actionTake(int[] actualRessources ) {
        System.out.printf("I gave you $%d\n", actualRessources[4]);
        actualRessources[4] -= actualRessources[4];
    }

    public static boolean checkValidCoffeeType(String coffeeToBuy) {
        switch (coffeeToBuy) {
            case "1":
                return true;
            case "2":
                return true;
            case "3":
                return true;
            default:
                return false;
        }
    }
}
