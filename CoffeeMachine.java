package machine;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class CoffeeMachine {

    static class CoffeeMachineInstance {
        private int water;
        private int milk;
        private int coffeeBean;
        private int disposableCups;
        private int money;

        public CoffeeMachineInstance(int water, int milk, int coffeeBean, int disposableCups, int money) {
            this.water = water;
            this.milk = milk;
            this.coffeeBean = coffeeBean;
            this.disposableCups = disposableCups;
            this.money = money;
        }
        public void handleUserInput(String input) {

        }

        public boolean checkSufficientWater(int requirement) {
            if (water < requirement) {
                System.out.println("Sorry, not enough water!");
                return false;
            }
            return true;
        }
        public boolean checkSufficientMilk(int requirement) {
            if (milk < requirement) {
                System.out.println("Sorry, not enough milk!");
                return false;
            }
            return true;
        }
        public boolean checkSufficientCoffeeBeans(int requirement) {
            if (coffeeBean < requirement) {
                System.out.println("Sorry, not enough coffee beans!");
                return false;
            }
            return true;
        }
        public boolean checkSufficientCups(int requirement) {
            if (disposableCups < requirement) {
                System.out.println("Sorry, not enough cups!");
                return false;
            }
            return true;
        }

        @Override
        public String toString() {

            return "The coffee machine has:\n"
                    + water + " of water\n"
                    + milk + " of milk\n"
                    + coffeeBean + " of coffee beans\n"
                    + disposableCups + " of disposable cups\n"
                    + "$" + money + " of money";

        }

        public int takeMoney() {
            int temp = this.money;
            this.money = 0;
            return temp;
        }

        // todo add validations
        public void addWater(int howMuch) {
            water += howMuch;
        }

        public void addMilk(int howMuch) {
            milk += howMuch;
        }

        public void addCoffeeBeans(int howMuch) {
            coffeeBean += howMuch;
        }

        public void addCups(int howMuch) {
            disposableCups += howMuch;
        }

        /*  espresso: 250 ml of water, 16 g of coffee beans. It costs $4.
            latte: 350 ml of water, 75 ml of milk, 20 g of coffee beans. It costs $7.
            cappuccino: 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
        */
        public void handleEspressoPurchase() {

            if (!checkSufficientCoffeeBeans(1) ||!checkSufficientWater(250) || !checkSufficientCoffeeBeans(16)) {
                return;
            }

            System.out.println("I have enough resources, making you a coffee!");
            water -= 250;
            coffeeBean -= 16;
            disposableCups--;
            money += 4;
        }

        public void handleLattePurchase() {

            if (!checkSufficientCoffeeBeans(1) ||!checkSufficientWater(350) || !checkSufficientMilk(75) || !checkSufficientCoffeeBeans(20)){
                return;
            }

            System.out.println("I have enough resources, making you a coffee!");

            water -= 350;
            milk -= 75;
            coffeeBean -= 20;
            disposableCups--;
            money += 7;

        }


        public void handleCappuccinoPurchase() {

            if (!checkSufficientCoffeeBeans(1) || !checkSufficientWater(200) || !checkSufficientMilk(100) || !checkSufficientCoffeeBeans(12)) {
                return;
            }

            System.out.println("I have enough resources, making you a coffee!");

            water -= 200;
            milk -= 100;
            coffeeBean -= 12;
            disposableCups--;
            money += 6;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }

        public int getMilk() {
            return milk;
        }

        public void setMilk(int milk) {
            this.milk = milk;
        }

        public int getCoffeeBean() {
            return coffeeBean;
        }

        public void setCoffeeBean(int coffeeBean) {
            this.coffeeBean = coffeeBean;
        }

        public int getDisposableCups() {
            return disposableCups;
        }

        public void setDisposableCups(int disposableCups) {
            this.disposableCups = disposableCups;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CoffeeMachineInstance machine = new CoffeeMachineInstance(400, 540, 120, 9, 550);

//        System.out.println(machine);

//        System.out.println();

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");

            String userInput = scanner.next().toLowerCase();

            switch (userInput) {
                case "buy":
                    System.out.println();
                    handleBuyRequest(machine);
                    break;

                case "fill":
                    System.out.println();
                    handleFillRequest(machine);
                    break;
                case "take":
                    System.out.println();
                    handleTakeRequest(machine);
                    break;
                case "remaining":
                    System.out.println();
                    System.out.println(machine);
                    break;
                case "exit":
                    return;

            }
            System.out.println();

        }


    }

    private static void handleTakeRequest(CoffeeMachineInstance machine) {
        int amountReceived = machine.takeMoney();
        System.out.println("I gave you $" + amountReceived);

    }

    private static void handleFillRequest(CoffeeMachineInstance machine) {
        System.out.println("Write how many ml of water do you want to add:");
        int addWater = scanner.nextInt();

        System.out.println("Write how many ml of milk do you want to add:");
        int addMilk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addCoffee = scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int addCups = scanner.nextInt();

        machine.addWater(addWater);
        machine.addMilk(addMilk);
        machine.addCoffeeBeans(addCoffee);
        machine.addCups(addCups);
    }

    private static void handleBuyRequest(CoffeeMachineInstance machine) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");

        String input = scanner.next().toLowerCase();

        if (input.equals("back")) {
            return;
        }

        int selection = Integer.parseInt(input);

        switch (selection) {
            case 1:
                machine.handleEspressoPurchase();
                break;
            case 2:
                machine.handleLattePurchase();
                break;
            case 3:
                machine.handleCappuccinoPurchase();
                break;
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
    }
}
