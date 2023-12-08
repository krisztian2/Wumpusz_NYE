package nye.hu.progTech.Start;

import nye.hu.progTech.Map.Load;
import nye.hu.progTech.Map.World;

import java.util.Scanner;

public class Menu {
    private World world;
    private Load load;




    public Menu(World world) {
        this.world = world;
        this.load =new Load(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kérlek add meg a játékos nevedet:");
        this.world.setName(scanner.nextLine());
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Pályaszerkesztő");
            System.out.println("2. Betöltés adatbázisból");
            System.out.println("3. Mentett játékmenetek");
            System.out.println("4. Kilépés");

            System.out.print("Választási lehetőség: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    editWorldMenu();
                    break;
                case 2:
                    load.setValuesFromDatabase(this.world.getName());
                    int a =load.getSize();
                    load = new Load(a);
                    load.play();
                    break;
                case 3:
                    System.out.println("\n");
                    load.reviwe();
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println("Kilépés a játékból.");
                    System.exit(0);
                default:
                    System.out.println("Ilyen lehetőség nem létezik!");
                    break;
            }
        }
    }

    private void editWorldMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Pályaszerkesztő menü:");
            System.out.println("1. Könnyű");
            System.out.println("2. Normál");
            System.out.println("3. Nehéz");
            System.out.println("4. Vissza a főmenübe");

            System.out.print("Választási lehetőségek: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    int easySize = world.getRandomNumberEasy();
                    world = new World(easySize);
                    System.out.println("A pálya nehézsége könnyűre lett állítva!");
                    world.play();
                    break;

                case 2:
                    int normalSize = world.getRandomNumberNormal();
                    world = new World(normalSize);
                    System.out.println("A pálya nehézsége normálra lett állítva!");
                    world.play();
                    break;
                case 3:
                    int hardSize = world.getRandomNumberHard();
                    world = new World(hardSize);
                    System.out.println("A pálya nehézsége nehézre lett állítva!");
                    world.play();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Nincs ilyen opció!");
                    break;
            }
        }
    }
}


