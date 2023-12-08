package nye.hu.progTech;

import nye.hu.progTech.Map.World;
import nye.hu.progTech.Start.Menu;



public class Main {

    public static void main(String[] args) {
        World world = new World(100);
        Menu menu = new Menu(world);
        menu.showMainMenu();
    }
}