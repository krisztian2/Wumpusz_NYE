package nye.hu.progTech.Map;


import nye.hu.progTech.controlars.Hero;
import nye.hu.progTech.database.DatabaseConnection;


import javax.xml.bind.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class World {
    private char[][] world;
    private int size;
    private int numWumpus;
    private Hero hero;
    private int initialHeroX;
    private int initialHeroY;
    private String HeroName;

    public void setName(String playerName) {
        this.HeroName = playerName;
    }
    public String getName() {
        return this.HeroName;
    }




    public World(int size) {
        this.size = size;
        this.world = new char[size][size];
        initializeWorld();
    }


    public void setHero(int x, int y, int xold, int yold) {
        world[x][y] = 'H';
        world[xold][yold] = ' ';
    }

    public char[][] getWorld() {
        return world;
    }

    public int getSize() {
        return size;
    }


    public void setxStone1(int xStone1) {
        this.xStone1 = xStone1;
    }

    public void setyStone1(int yStone1) {
        this.yStone1 = yStone1;
    }

    public int getxStone1() {
        return xStone1;
    }

    public int getyStone1() {
        return yStone1;
    }

    public int getNumWumpus() {
        return numWumpus;
    }

    public void initializeWorld() {
        // A wumpus és fal meghatározása a size változó szerint.
        if (size <= 8) {
            numWumpus = 1;

        } else if (size >= 9 && size <= 14) {
            numWumpus = 2;

        } else if (size >= 15) {
            numWumpus = 3;

        }

        // Pálya inicializálása és feltöltése üres hellyel.
        world = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = ' ';
            }
        }

        // Prefixek a pályán.
        placeCharacterU('U', numWumpus);
        placeCharacterS1('S');
        placeCharacterS2('S');
        placeCharacterS3('S');
        placeCharacterG('G', 1);
        placeCharacterH('H');
    }

    public int getRandomNumberEasy() {

        Random random = new Random();
        int min = 5;
        int max = 8;

        int randomNum = random.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public int getRandomNumberNormal() {
        Random random = new Random();
        int min = 9;
        int max = 14;
        int randomnum = random.nextInt((max - min) + 1) + min;
        return randomnum;

    }

    public int getRandomNumberHard() {
        Random random = new Random();
        return random.nextInt(15 - 4) + 4;
    }

    public int xGold;
    public int yGold;
    public void placeCharacterG(char character, int count) {
        Random random = new Random();
        int remainingCount = count;

        while (remainingCount > 0) {
            xGold = random.nextInt(size);
            yGold = random.nextInt(size);

            if (world[xGold][yGold] == ' ') {
                world[xGold][yGold] = character;
                remainingCount--;
            }
        }
    }


    public int xStone1;
    public int yStone1;
    public void placeCharacterS1(char character) {
        Random random = new Random();

        xStone1 = random.nextInt(size);
        yStone1 = random.nextInt(size);

        if (world[xStone1][yStone1] == ' ') {
            world[xStone1][yStone1] = character;
        }

    }

    public int xStone2;
    public int yStone2;
    public void placeCharacterS2(char character) {
        Random random = new Random();

        xStone2 = random.nextInt(size);
        yStone2 = random.nextInt(size);

        if (world[xStone2][yStone2] == ' ') {
            world[xStone2][yStone2] = character;
        }

    }

    public int xStone3;
    public int yStone3;
    public void placeCharacterS3(char character) {
        Random random = new Random();

        xStone3 = random.nextInt(size);
        yStone3 = random.nextInt(size);

        if (world[xStone3][yStone3] == ' ') {
            world[xStone3][yStone3] = character;
        }

    }

    public int xU;
    public int yU;
    public void placeCharacterU(char character, int count) {
        Random random = new Random();
        int remainingCount = count;

        while (remainingCount > 0) {
            xU = random.nextInt(size);
            yU = random.nextInt(size);

            if (world[xU][yU] == ' ') {
                world[xU][yU] = character;
                remainingCount--;
            }
        }
    }

    public void placeCharacterH(char character) {
        Random random = new Random();
        initialHeroX = random.nextInt(size);
        initialHeroY = random.nextInt(size);
        world[initialHeroX][initialHeroY] = character;
        hero = new Hero(initialHeroX, initialHeroY, numWumpus);
    }

    public void printWorld() {
        for (int i = -1; i <= size; i++) {
            for (int j = -1; j <= size; j++) {
                char borderChar = 'W';

                if (i >= 0 && i < size && j >= 0 && j < size) {
                    borderChar = world[i][j];
                }

                System.out.print(borderChar + " ");
            }
            System.out.println();

        }
        System.out.println("A hős iránya:" + hero.getDirection());
    }

    public void hint(){
        int asciivalue= initialHeroY+97;
        char ypos = (char)asciivalue;
        System.out.println("Vidd vissza az aranyat oda ahonnan jöttél: "+"X: "+ initialHeroX +" and "+ "Y: "+ypos);
    }

    public String fileName;
    public void saveWorld() {
        /*System.out.println("Játékos fájlneve: ");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();*/

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insert = "insert status (heroX,heroY,steps,size,xGold,yGold,xStone1,yStone1,xU,yU,xStone2,yStone2,xStone3,yStone3,name,direction,initialX,initialY,arrows) values("
                +hero.getHeroX()+","
                +hero.getHeroY()+","
                +hero.steps+","
                +size+","
                +xGold+","
                +yGold+","
                +xStone1+","
                +yStone1+","
                +xU+","
                +yU+","+
                +xStone2+","
                +yStone2+","
                +xStone3+","
                +yStone3+",'"
                +this.HeroName+"','"
                +hero.getDirection()+"',"
                +initialHeroX+","
                +initialHeroY+","
                +hero.getArrows()+")";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void play() {


        Scanner scanner = new Scanner(System.in);
        String input;
        boolean gameOver = false;
        printWorld();
        while (!gameOver) {

            System.out.println("Parancsok: (fordul balra, fordul jobbra, lép, lő ,'aranyat felszed', 'halasztás', felad)");
            input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "fordul balra":
                    hero.turnLeft();
                    break;
                case "fordul jobbra":
                    hero.turnRight();
                    break;
                case "lép":
                    hero.walk(this);
                    break;
                case "lő":
                    hero.shoot(this);
                    break;
                case "aranyat felszed":
                    hero.haveGold();
                    break;
                case "halasztás":
                    saveWorld();
                    gameOver = true;
                    break;
                case "felad":
                    System.out.println("Biztos vagy benne, hogy feladod? (igen vagy nem)");
                    String giveUpChoice = scanner.nextLine().toLowerCase();
                    if (giveUpChoice.equals("igen")) {
                        gameOver = true;
                    }
                    break;
                default:
                    System.out.println("Ismeretlen parancs. Parancsok: 'fordul balra', 'fordul jobbra', 'lép', 'aranyat felszed', 'lő', 'felad'.");
            }
            if (gameOver == false){
                printWorld();
            }

            if (!hero.isAlive()) {
                System.out.println("A játéknak vége! A hős meghalt.");
                System.out.println("Szeretnél egy új játékmenetet kezdeni? (igen vagy nem)");
                input = scanner.nextLine().toLowerCase();
                if (input.equals("igen")) {
                    hero = new Hero(initialHeroX, initialHeroY, numWumpus);
                    initializeWorld();
                    printWorld();
                } else {
                    gameOver = true;
                }
            }
            if (initialHeroX == hero.getHeroX() && initialHeroY == hero.getHeroY() && hero.isHasGold() == true){
                System.out.println("Gratulálok, megnyerted a játékmenetet. Pontszámod: "+ hero.steps);

                /*Scanner player = new Scanner(System.in);
                System.out.println("Írd be a nevedet:");
                String name = scanner.nextLine();*/
                scanner.close();

                addRank(this.HeroName,hero.steps);


                System.exit(0);
            }
        }
    }

    public void addRank(String name, int num) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String insert = "insert into rank_tb(playerName, points) values('" + name + "','" + num + "');";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insert);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void reviwe(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        try {

            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rank_tb");

            while (resultSet.next()) {
                String column1Value = resultSet.getString("playerName");
                int column2Value = resultSet.getInt("points");

                System.out.println(" Játékosnév : " + column1Value + "  Pontok: " + column2Value);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }



}