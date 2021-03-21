import java.io.IOException;
import java.util.Scanner;

public class GameMenu {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input =new Scanner(System.in);
        Game game = Game.getInstance();
        String welcome = "welcome to the best game ever : ";
        Game.cleaner();
        for (int i = 0; i < welcome.length(); i++) {
            System.out.print(welcome.charAt(i));
            Game.delay(100);
        }
        Game.delay(1500);
        System.out.println("!! MAFIA !!");
        Game.delay(3000);

        while (true) {
            Game.cleaner();
            System.out.print("your order: ");
//            if (Game.isStarted && (Citizen.getNumberOfCitizen() <= Mafia.getNumberOfMafia())) {
//                System.out.println("Mafia won!");
//                break;
//            }
//            if (Game.isStarted && (0 == Mafia.getNumberOfMafia())) {
//                System.out.println("Villagers won!");
//                break;
//            }
//            if (Game.gameIsOver) {
//
//                break;
//            }
            String order = input.nextLine();
            if (order.contains("creat_game")) {
                Game.setPlayers(order.substring("creat_game".length()+1));
            }
            if (!Game.isCreated) {
                System.out.println("no game created");
            }
            else if (order.contains("assign_role")) {
                Game.assignPlayers(order.substring("assign_role".length()+1));
            }
            else if (order.contains("start_game")) {
                if (Citizen.getNumberOfCitizen() + Mafia.getNumberOfMafia() != Game.getNumberOfPlayers()) {
                    System.out.println("exist at least a player that dont be assigned");
                    System.out.println("please assign them");
                    Game.delay(2000);
                }
                else if (Game.isStarted)
                    System.out.println("game has already started");
                else {
                    Game.showPlayers();
                    break;
                }
            }
        }
        int a, b;
        while (true) {
            Game.cleaner();
            a = Citizen.getNumberOfCitizen();
            b = Mafia.getNumberOfMafia();
            if (a <= b) {
                System.out.println("Mafia won!");
                break;
            }
            if (0 == b) {
                System.out.println("Villagers won!");
                break;
            }
            if (Game.gameIsOver) {

                break;
            }
//            else if (order.contains("get_game_state")) {
//                System.out.println("Mafia = " + Game.getNumberOfMafias() + "\nVillager = " + Game.getNumberOfCitizens());
//            }
            if (Game.dayNumber == Game.nightNumber) {
//                System.out.println("day part");
//                Game.delay(1500);
                Game.day();
            }
            else {
//                System.out.println("night part");
//                Game.delay(1500);
                Game.night();
            }
        }
        System.out.println("\nGame is Over\n\n///////////////////////////////////////////////////////\n");
        Game.information();
    }
}
/*
creat_game a b c d e
assign a mafia
assign b godfather
assign c villager
assign d doctor
assign e Joker
start_game

*/