//Console Crawl
//property of [Redacted.ink]

import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleCrawl{

    //monster statistics
    private static String command, commandAttack, commandInventory;

    private static int monsterRatHp = 2;
    private static int monsterWolfHp = 5;
    private static int monsterSkeletonHp = 7;
    private static int monsterDireWolfHp = 11;
    private static int monsterMimicHp = 8;

    private static int monsterRatAtk = 1;
    private static int monsterWolfAtk = 3;
    private static int monsterSkeletonAtk = 4;
    private static int monsterDireWolfAtk = 6;
    private static int monsterMimicAtk = 5;

    //player statistics
    private static int hp = 20;
    private static int level = 0;
    private static int attack = 1;
    private static int exp = 0;
    private static int mana = 10;
    private static int gold = 0;

    //inventory items
    private static String[] inventoryX = {"Rusty Knife"};

    //spells list
    private static String[] spellsX = {"Healing Word"};
    //I'm using X after rach of the arrays so the program does not get confused withing their respective functions


    //number of rooms
    private static int rooms = 1;


    //title and instructions
    public static void title(){
        System.out.println("Welcome the the glorious interactive experience of: ConsoleCrawl");
        System.out.println("Please only type exaclty what is prompted: commands should be in lower=case font, and yes and no should be y and n, respectively");
    }


    //generates gold and distributes it to the player
    public static void findGold(int goldX){
        int generateGold = (int)(6 + Math.random()*goldX);
        System.out.println("You found" + generateGold + "Gold!");
        gold = gold + generateGold;
    }

    //player finds a chest and gets a random item based on level
    public static void findChest(){
        System.out.println("You found a chest!");
        int lootRating = (int)(1 + Math.random()*100);
        if (lootRating <= 15){
            //bad luck
            System.out.println("The chest was empty.");
        }else if (lootRating <= 70 && lootRating > 50){
            //find an item
            findItem();
        }else if (lootRating <= 50 && lootRating > 15){
            //find gold up to 60
            findGold(55);
        }else if (lootRating <= 90 && lootRating > 70){
            //find gold up to 125
            findGold(120);
        }else if (lootRating <=100 && lootRating > 90){
            //player must fight a mimic
            System.out.println("The chest was a Mimic!");
            fightEnemy("Mimic", monsterMimicHp, monsterMimicAtk);
        }
        nextRoom();
    }

    //the item based on level thing from before
    public static void findItem(){
        //
        //
        System.out.println("Placeholder Text. Items currently under development.");
        //
        //
    }

    //a program that heals just for simplicity's sake
    public static void heal(int hP){
        int healHp = (int)(2 + Math.random()*hP);
        hp = hp + healHp;
    }

    
    //for siplicitie's sake, I'm putting the question for going to next toom here
    public static void roomQuestion(){
        Scanner console = new Scanner(System.in);
        System.out.println("Move on to the next room?");
        command = console.next();
            if (command.equalsIgnoreCase("y")){
                nextRoom();
            }else if (command.equalsIgnoreCase("n")){
                System.out.println("Ok, take a moment to breath.\n"
                                    + "...\n"
                                    + "ok we're going now");
                nextRoom();
            }else {
                System.out.println("Invalid command/");
                roomQuestion();
            }
    }


    // in order to check if hp ever hits 0 when the player takes damage, this function is here. Call it when we need
    public static void checkHealth(int n){
        hp = hp - n;
        if (hp <= 0){
            System.out.println("YOU DIED\n" + 
                                "Max room: " + rooms);
            System.exit(0);
        }else {
            System.out.println("You take " + n + " damage!");
        }
    }




    //this is just a little function that will check to see if an item or spell is in the inventory
    public static void checkItemSpell(String[] a){
        Scanner console = new Scanner(System.in);
        for (int i = 0; i < inventoryX.length; i++) {
            System.out.println(a[i] + ", ");
         }
        commandInventory = console.next();
        if (commandInventory.equalsIgnoreCase("Healing Potion")){
            if (a.contains("Healing Potion")){
                System.out.println("You used the Healing Potion!");
                heal(12);
                a.remove("Healing Potion");
            }else {
                System.out.println("You do not have that item in your inventory");
            }
        }else if (commandInventory.equalsIgnoreCase("Healing Potion")){
            if (a.contains("Healing Word")){
                System.out.println("You cast Healing Word! Mana was reduced by 5.");
                heal(12);
                mana -= 5;
            }else {
                System.out.println("You do not have that item in your inventory");
            }
        }else {

        }
    }


    //prompts the combat options for the player to choose
    public static String combatOptions(String combatChoice){
        Scanner console = new Scanner(System.in);
        System.out.println("What do you do?\n"
                            + "|attack|spell|item|run|\n");
        combatChoice = console.next();
        return combatChoice;
    }

    //initiated when the player choses to use a spell
    public static void useSpell(){
        System.out.println("Which spell would you like to cast?");
        checkItemSpell(spellsX);
    }

    //code for when you choose an item, displays inventory and prompts item usage
    public static void openInventory(){
        System.out.println("Inventory open. Please type the name of the item you wish to use.");
        checkItemSpell(inventoryX);
    }


    //enemy combat generator
    public static void fightEnemy(String n, int hP, int atk){
        System.out.println("A " + n + " stands before you!\n");
        if (hP > 0){
            do {
                String choice = combatOptions(commandAttack);
                if (choice.equalsIgnoreCase("attack")){
                    System.out.println("You dealt" + attack + "damage to the " + n + "!");
                    hP = hP - attack;
                }else if (choice.equalsIgnoreCase("spell")){
                    useSpell();
                }else if (choice.equalsIgnoreCase("item")){
                    openInventory();
                }else if (choice.equalsIgnoreCase("run")){
                    System.out.println("You ran away from the " + n + " like a little bitch baby\n");

                }else{
                    //if player inputs an invalid command
                    System.out.println("Invalid command");
                    fightEnemy(n, hP, atk);
                }
                System.out.println("The " + n + " attacks!");
                checkHealth(atk);
            } while (hP > 0);
        }else {
            System.out.println("You defeated the " + n + "!");
            findGold(20);
            roomQuestion();
        }
    }



    //code that runs when you enter the next room
    public static void nextRoom(){
        //increases the room number by 1
        rooms += 1;
        monsterRatHp = 2;
        monsterWolfHp = 5;
        monsterSkeletonHp = 7;
        monsterDireWolfHp = 11;
        //random number generator identifies what enemy you fight in the new room
        int encounterRating = (int)(1 + Math.random()*150);
        if (encounterRating <= 40){
            //fights a rat
            fightEnemy("Rat", monsterRatHp, monsterRatAtk);
        }else if (encounterRating <= 70 && encounterRating > 40){
            //fights a wolf
            fightEnemy("Wolf", monsterWolfHp, monsterWolfAtk);
        }else if (encounterRating <= 90 && encounterRating > 70){
            //fights a skeleton
            fightEnemy("Skeleton", monsterSkeletonHp, monsterSkeletonAtk);
        }else if (encounterRating <= 100 && encounterRating > 90){
            //fights a dire wolf
            fightEnemy("Dire Wolf", monsterDireWolfHp, monsterDireWolfAtk);
        }else if (encounterRating <= 130 && encounterRating >100){
            findChest();
        }
    }

    //starts the game
    public static void game(){
        Scanner console = new Scanner(System.in);
        System.out.print("--------------------------------------------\n"
                        + "Welcome to the dungeon! Enter room number 1?\n");
        command = console.next();
        if(command.equalsIgnoreCase("Y")){
            //user enters first room
            fightEnemy("Rat", monsterRatHp, monsterRatAtk);
            nextRoom();

        }else if(command.equalsIgnoreCase("N")){
            //user refuses to enter room
            System.out.println("Then why the hell are you here?");

        }else if(command.equalsIgnoreCase("fuckyou")){
            //user is mean
            System.out.println("Two can play that game. Fuck you too mate.");
            checkHealth(hp);;

        }else{
            //user entered wrong prompt
            System.out.print("Invalid prompt. Please try again.");
            game();
        }
    }

    public static void main(String[] args){
        title();
        game();
    }

}
