import java.util.Scanner;

class Coffee {
    private String type;
    private double price;
    private int water;
    private int milk;
    private int beans;

    public Coffee(String type, double price, int water, int milk, int beans) {
        this.type = type;
        this.price = price;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }
}

class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private double moneyEarned;
    private int espressoCount;
    private int latteCount;
    private int cappuccinoCount;

    public CoffeeMachine() {
        water = 0;
        milk = 0;
        beans = 0;
        moneyEarned = 0;
        espressoCount = 0;
        latteCount = 0;
        cappuccinoCount = 0;
    }

    public void fillIngredients(int water, int milk, int beans) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
    }

    public boolean canMakeCoffee(Coffee coffee) {
        return water >= coffee.getWater() && milk >= coffee.getMilk() && beans >= coffee.getBeans();
    }

    public void makeCoffee(Coffee coffee) {
        water -= coffee.getWater();
        milk -= coffee.getMilk();
        beans -= coffee.getBeans();
        moneyEarned += coffee.getPrice();

        if (coffee.getType().equalsIgnoreCase("Espresso")) {
            espressoCount++;
        } else if (coffee.getType().equalsIgnoreCase("Latte")) {
            latteCount++;
        } else if (coffee.getType().equalsIgnoreCase("Cappuccino")) {
            cappuccinoCount++;
        }

        System.out.println("Here is your " + coffee.getType() + ". Enjoy!");
    }

    public void takeMoney() {
        System.out.println("You collected $" + moneyEarned);
        moneyEarned = 0;
    }

    public void showIngredients() {
        System.out.println("Ingredients available:");
        System.out.println("Water: " + water + " ml");
        System.out.println("Milk: " + milk + " ml");
        System.out.println("Beans: " + beans + " g");
    }

    public void showAnalytics() {
        System.out.println("Coffee Machine Analytics:");
        System.out.println("Espresso count: " + espressoCount);
        System.out.println("Latte count: " + latteCount);
        System.out.println("Cappuccino count: " + cappuccinoCount);
        System.out.println("Total money earned: $" + moneyEarned);
    }
}

public class CoffeeMachineSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Coffee espresso = new Coffee("Espresso", 4, 250, 0, 16);
        Coffee latte = new Coffee("Latte", 7, 350, 75, 20);
        Coffee cappuccino = new Coffee("Cappuccino", 6, 200, 100, 12);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Buy");
            System.out.println("2. Fill Ingredients");
            System.out.println("3. Take Money");
            System.out.println("4. Show Ingredients");
            System.out.println("5. Analytics");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Coffees:");
                    System.out.println("1. " + espresso.getType() + " - $" + espresso.getPrice());
                    System.out.println("2. " + latte.getType() + " - $" + latte.getPrice());
                    System.out.println("3. " + cappuccino.getType() + " - $" + cappuccino.getPrice());
                    System.out.print("Select a coffee (1-3): ");
                    int coffeeChoice = scanner.nextInt();

                    Coffee selectedCoffee;
                    switch (coffeeChoice) {
                        case 1:
                            selectedCoffee = espresso;
                            break;
                        case 2:
                            selectedCoffee = latte;
                            break;
                        case 3:
                            selectedCoffee = cappuccino;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                            continue;
                    }

                    if (coffeeMachine.canMakeCoffee(selectedCoffee)) {
                        coffeeMachine.makeCoffee(selectedCoffee);
                    } else {
                        System.out.println("Insufficient ingredients to make the selected coffee.");
                    }
                    break;

                case 2:
                    System.out.print("Enter the amount of water to add (in ml): ");
                    int water = scanner.nextInt();
                    System.out.print("Enter the amount of milk to add (in ml): ");
                    int milk = scanner.nextInt();
                    System.out.print("Enter the amount of beans to add (in g): ");
                    int beans = scanner.nextInt();
                    coffeeMachine.fillIngredients(water, milk, beans);
                    System.out.println("Ingredients filled successfully.");
                    break;

                case 3:
                    coffeeMachine.takeMoney();
                    break;

                case 4:
                    coffeeMachine.showIngredients();
                    break;

                case 5:
                    coffeeMachine.showAnalytics();
                    break;

                case 6:
                    System.out.println("Exiting the simulator.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
