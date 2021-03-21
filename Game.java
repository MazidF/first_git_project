import java.io.*;
import java.util.*;

public class Game {
    public static boolean gameIsOver = false;
    public static int dayNumber;
    public static int nightNumber;
    public static boolean isCreated;
    public static boolean isStarted;
    public static Scanner input = new Scanner(System.in);
    private static String [] players;
    private static int numberOfPlayers;
    private static int numberOfMafias;
    private static int numberOfCitizens;
    private static int numberOfJoker;
    private static Mafia [] mafias;
    private static Citizen [] citizens;
    private static Joker jocker;

    private static final Game gameMenu = new Game();

    private Game() {}
    static {
        Game.dayNumber = 1;
        Game.nightNumber = 1;
        Game.isCreated = false;
        Game.isStarted = false;
        Game.players = new String[20];
        Game.mafias = new Mafia[20];
        Game.citizens = new Citizen[20];
        Game.numberOfCitizens = 0;
        Game.numberOfMafias = 0;
        Game.numberOfJoker = 0;
    }



    //.....................setter........................//
    //.....................setter........................//

    public static void setPlayers(String order) {
        order += " ";
        Game.isCreated = true;
        while (!order.equals("")) {
            if (Game.getNumberOfPlayers() == Game.players.length) {
                String [] copy = Game.players;
                Game.players = new String[Game.players.length*2];
                System.arraycopy(copy, 0, Game.players, 0, copy.length);
            }
            Game.players[Game.numberOfPlayers++] = order.substring(0,order.indexOf(" "));
            order = order.substring(Game.players[Game.numberOfPlayers-1].length()+1);
        }
    }

    public static void assignPlayers(String order) {
            String player = order.substring(0, order.indexOf(" "));
            String role = order.substring(order.indexOf(" ")+1);
            if (!Game.hasPlayer(player)) {
                System.out.println("user not found");
                System.out.println("try again");
                return;
            }
            switch (role) {
                case "mafia"       -> {
                    Game.addMafia(player, 1);
                }
                case "silencer"    -> {
                    Game.addMafia(player, 2);
                }
                case "godfather"   -> {
                    Game.addMafia(player, 3);
                }

                case "villager"    -> {
                    Game.addCitizen(player, 1);
                }
                case "bulletproof" -> {
                    Game.addCitizen(player, 2);
                }
                case "doctor"      -> {
                    Game.addCitizen(player, 3);
                }
                case "detective"   -> {
                    Game.addCitizen(player, 4);
                }
                case "informer"    -> {
                    Game.addCitizen(player, 5);
                }

                case "Joker"       -> {
                    Game.jocker = new Joker(player, Job.Joker);
                }

                default            -> System.out.println("role not found");
            }
    }
    //.....................setter........................//
    //.....................setter........................//

    //.....................getter........................//
    //.....................getter........................//
    public static Game getInstance() {
        return Game.gameMenu;
    }

    public static Citizen[] getCitizens() {
        Citizen [] copy = new Citizen[Citizen.getNumberOfCitizen()];
        System.arraycopy(copy, 0, Game.citizens, 0, copy.length);
        return copy;
    }

    public static Mafia[] getMafias() {
        Mafia [] copy = new Mafia[Mafia.getNumberOfMafia()];
        System.arraycopy(copy, 0, Game.mafias, 0, copy.length);
        return copy;
    }

    public static Joker getJoker() {
        return Game.jocker;
    }

    public static int getNumberOfCitizens() {
        return Game.numberOfCitizens;
    }

    public static int getNumberOfMafias() {
        return Game.numberOfMafias;
    }

    public static int getNumberOfJoker() {
        return Game.numberOfJoker;
    }

    public static int getNumberOfPlayers() {
        return Game.numberOfPlayers;
    }

    //.....................getter........................//
    //.....................getter........................//
    public static void cleaner() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void delay(long milisecond) {
        long start = System.currentTimeMillis();
        long end;
        while (true) {
            end = System.currentTimeMillis();
            if (end - start >= milisecond) {
                return;
            }
        }
    }

    public static void resize(Object object) {
        if (object instanceof Mafia) {
            Game.mafias = Mafia.resize(Game.mafias);
        } else if (object instanceof Citizen) {
            Game.citizens = Citizen.resize(Game.citizens);
        } else {
            System.out.println("your programming is stupid :)");
            Game.gameIsOver = true;
        }
    }

    private static boolean hasPlayer(String player) {
        for (int i = 0; i < Game.numberOfPlayers; i++) {
            if (player.equals(Game.players[i])) {
                return true;
            }
        }
        return false;
    }

    public static Player finder(String name) {
        if (jocker.getName().equals(name))
            return jocker;
        for (int i = 0; i < numberOfCitizens; i++) {
            if (Game.citizens[i].getName().equals(name)) {
                return Game.citizens[i];
            }
        }
        for (int i = 0; i < numberOfMafias; i++) {
            if (Game.mafias[i].getName().equals(name)) {
                return Game.mafias[i];
            }
        }
        System.out.println("player not found");
        return null;
    }

    private static void addMafia(String player, int kind) {
        if (Game.numberOfMafias == Game.mafias.length) {
            Game.resize(new Silencer("resize", Job.Silencer));
        }

        if (kind == 1) {// simple mafia
            Game.mafias[Game.numberOfMafias++] = new SimpleMafia(player, Job.SimpleMafia);
        }
        if (kind == 2) {// silencer
            Game.mafias[Game.numberOfMafias++] = new Silencer(player, Job.Silencer);
        }
        if (kind == 3) {// godfather
            Game.mafias[Game.numberOfMafias++] = new GodFather(player, Job.GodFather);
        }
    }

    private static void addCitizen(String player, int kind) {
        if (Game.numberOfCitizens == Game.citizens.length) {
            Game.resize(new Villager("resize", Job.Villager));
        }

        if (kind == 1) {// simple citizen
            Game.citizens[Game.numberOfCitizens++] = new Villager(player, Job.Villager);
        }
        if (kind == 2) {// bullet proof
            Game.citizens[Game.numberOfCitizens++] = new Bulletproof(player, Job.BulletProof);
        }
        if (kind == 3) {// doctor
            Game.citizens[Game.numberOfCitizens++] = new Doctor(player, Job.Doctor);
        }
        if (kind == 4) {// detective
            Game.citizens[Game.numberOfCitizens++] = new Detective(player, Job.Detective);
        }
        if (kind == 5) {// informer
            Game.citizens[Game.numberOfCitizens++] = new Informer(player, Job.Informer);
        }
    }

    public static void showPlayers() throws IOException, InterruptedException {
        for (int i = 0; i < numberOfCitizens; i++) {
            System.out.println(Game.citizens[i].getName() + ": " + Game.citizens[i].getJob());
        }
        for (int i = 0; i < numberOfMafias; i++) {
            System.out.println(Game.mafias[i].getName() + ": " + Game.mafias[i].getJob());
        }
        if (Game.jocker != null)
            System.out.println(Game.jocker.getName() + ": " + Game.jocker.getJob());
        System.out.println();
        System.out.println();
        Game.delay(1000);
        System.out.print("Ready?");
        Game.delay(1000);
        System.out.print("Set!");
        Game.delay(1000);
        System.out.println("Go.");
        Game.delay(5000);
        Game.cleaner();
//        day();
    }
    ///......................game........................///
    ///......................game........................///
    public static void day() throws IOException, InterruptedException {
        //if (!Player.winner.equals("null")) return;
        //if (Citizen.getNumberOfCitizen() <= Mafia.getNumberOfMafia()) return;
        //if (0 == Mafia.getNumberOfMafia()) return;
        //if (Game.gameIsOver) return;
        Game.cleaner();
//        //System.out.println("city + " + Citizen.getNumberOfCitizen() + "\tmafia = " + Mafia.getNumberOfMafia());
        System.out.println("Day " + Game.dayNumber);
        Game.vote();
        //if (!Player.winner.equals("null")) return;
        //if (Citizen.getNumberOfCitizen() <= Mafia.getNumberOfMafia()) return;
        //if (0 == Mafia.getNumberOfMafia()) return;
        //if (Game.gameIsOver) return;
        Game.resetAll();
        Game.resetSilence();
        Game.dayNumber++;
        Game.delay(3000);
        //night();
    }

    public static void information() {
        for (int i = 0; i < numberOfCitizens; i++) {
            System.out.println(Game.citizens[i].information());
        }
        for (int i = 0; i < numberOfMafias; i++) {
            System.out.println(Game.mafias[i].information());
        }
        if (Game.numberOfJoker == 1)
            System.out.println(Game.jocker.information());
    }

    public static void night() throws IOException, InterruptedException {
        //if (!Player.winner.equals("null")) return;
        //if ((Citizen.getNumberOfCitizen() <= Mafia.getNumberOfMafia())) return;
        //if ((0 == Mafia.getNumberOfMafia())) return;
        //if (Game.gameIsOver) return;
        Game.cleaner();
//        System.out.println("city + " + Citizen.getNumberOfCitizen() + "\tmafia = " + Mafia.getNumberOfMafia());
        System.out.println("Night " + Game.nightNumber);
        Game.nightWork();
        Game.nightVote();
        //if (!Player.winner.equals("null")) return;
        //if ((Citizen.getNumberOfCitizen() <= Mafia.getNumberOfMafia())) return;
        //if ((0 == Mafia.getNumberOfMafia())) return;
        //if (Game.gameIsOver) return;
        Game.delay(3000);
        Game.resetAll();
        Game.nightNumber++;
        //day();
    }

    private static void nightWork() {
        System.out.println("\n//////////////night worker/////////////\n");
        for (int i = 0; i < numberOfCitizens; i++) {
            if ((Game.citizens[i].hasNightWork) && (!Game.citizens[i].isDead))
                System.out.println(Game.citizens[i].getName() + ": " + Game.citizens[i].getJob());
        }
        for (int i = 0; i < numberOfMafias; i++) {
            if ((Game.mafias[i].hasNightWork) && (!Game.mafias[i].isDead))
                System.out.println(Game.mafias[i].getName() + ": " + Game.mafias[i].getJob());
        }
        if (Game.numberOfJoker == 1)
            if ((Game.jocker.hasNightWork) && (!Game.jocker.isDead))
                System.out.println(Game.jocker.getName() + ": " + Game.jocker.getJob());
        System.out.println("\n///////////////////////////////////////\n");
        ///////////////////////////////////////
        ///////////////////////////////////////
        while (true) {
            String functor = input.next();
            if (functor.equals("get_game_state")) {
                System.out.println("Mafia = " + Game.getNumberOfMafias() + "\nVillager = " + Game.getNumberOfCitizens());
                continue;
            }
            if (functor.equals("end_night")) return;
            if (Objects.requireNonNull(Game.finder(functor)).isDead) {
                System.out.println(Game.finder(functor).getName() + " is dead");
                continue;
            }
            String target = input.next();
            if (Game.finder(functor) == null) {
                System.out.println("user not found");
                continue;
            }
            if (!Objects.requireNonNull(Game.finder(functor)).hasNightWork) {
                System.out.println("user can not wake up during night");
                continue;
            }
            if (Game.finder(target) == null) {
                System.out.println("user not found");
                continue;
            }
            if (Objects.requireNonNull(Game.finder(target)).isDead) {
                System.out.println("user is dead");
                continue;
            }
            Player player = Game.finder(functor);
            assert player != null;
            player.action(target);
        }
    }

    public static void nightVote() {
        int [] votes = new int[Game.numberOfPlayers];
        int num = 0;
        for (int i = 0; i < numberOfCitizens; i++) {
            votes[num++] = Game.citizens[i].numberOfVote;
        }
        for (int i = 0; i < numberOfMafias; i++) {
            votes[num++] = Game.mafias[i].numberOfVote;
        }
        if (Game.numberOfJoker == 1)
            votes[num++] = Game.jocker.numberOfVote;
        int max = -1;
        int maxes = 0;
        for (int x: votes) {
            if (x == max) {
                maxes++;
            }
            if (x > max) {
                max = x;
                maxes = 0;
            }
        }
        /*
        if (maxes > 1) {
            System.out.println("nobody died");
        }
        */
        if (maxes > 0) {
            System.out.println("nobody died");
        }
        /*
        if (maxes > 0) {
            Player [] targets = new Player[2];
            int index = 0;
            for (int i = 0; i < numberOfCitizens; i++) {
                if (Game.citizens[i].numberOfVote == max) {
                    targets[index++] = Game.citizens[i];
                }
            }
            for (int i = 0; i < numberOfMafias; i++) {
                if (Game.mafias[i].numberOfVote == max) {
                    targets[index++] = Game.mafias[i];
                }
            }
            targets[index++] = Game.jocker;
            if (index != 2) {
                System.out.println("something is Wrong");
                Game.gameIsOver = true;
                return;
            }
            for (Player x :
                    targets) {
                if (!x.treatByDoctor) {
                    x.setDead();
                    return;
                }
            }
            System.out.println("nobody died");
        }
        */
        else if (maxes == 0) {
            for (int i = 0; i < numberOfCitizens; i++) {
                if (Game.citizens[i].numberOfVote == max) {
                    Game.citizens[i].setDead();
                    return;
                }
            }
            for (int i = 0; i < numberOfMafias; i++) {
                if (Game.mafias[i].numberOfVote == max) {
                    Game.mafias[i].setDead();
                    return;
                }
            }
            Game.jocker.setDead();
            Player.setWinner(Game.jocker.getName());
        }
    }

    public static void vote() {
        int [] votes = new int[Game.numberOfPlayers];
        int num = 0;
        while (true) {
            String voter_name = input.next();
            if (voter_name.equals("get_game_state")) {
                System.out.println("Mafia = " + Game.getNumberOfMafias() + "\nVillager = " + Game.getNumberOfCitizens());
                continue;
            }
            if (voter_name.equals("end_vote")) {
                break;
            }
            String voted_name = input.next();
            if (Objects.requireNonNull(Game.finder(voter_name)).isDead) {
                System.out.println("voter is dead");
                continue;
            }
            if (Game.finder(voter_name) == null) {
                System.out.println("user not found");
                continue;
            }
            if (Objects.requireNonNull(Game.finder(voter_name)).isSilent) {
                System.out.println("voter is silenced");
                continue;
            }
            if (Objects.requireNonNull(Game.finder(voted_name)).isDead) {
                System.out.println("voted already dead");
                continue;
            }
            Objects.requireNonNull(Game.finder(voted_name)).numberOfVote++;
        }
        for (int i = 0; i < numberOfCitizens; i++) {
            votes[num++] = Game.citizens[i].numberOfVote;
        }
        for (int i = 0; i < numberOfMafias; i++) {
            votes[num++] = Game.mafias[i].numberOfVote;
        }
        votes[num++] = Game.jocker.numberOfVote;
        int max = -1;
        int maxes = 0;
        for (int x: votes) {
            if (x == max) {
                maxes++;
            }
            if (x > max) {
                max = x;
                maxes = 0;
            }
        }
        if (maxes > 0) {
            System.out.println("nobody died");
        }
        else if (maxes == 0) {
            for (int i = 0; i < numberOfCitizens; i++) {
                if (Game.citizens[i].numberOfVote == max) {
                    Game.citizens[i].setDead();
                    return;
                }
            }
            for (int i = 0; i < numberOfMafias; i++) {
                if (Game.mafias[i].numberOfVote == max) {
                    Game.mafias[i].setDead();
                    return;
                }
            }
            Game.jocker.setDead();
            Player.setWinner(Game.jocker.getName());
        }
    }

    public static void resetAll() {
        for (int i = 0; i < numberOfCitizens; i++) {
            Game.citizens[i].numberOfVote = 0;
            Game.citizens[i].doneAction = false;
        }
        for (int i = 0; i < numberOfMafias; i++) {
            Game.mafias[i].numberOfVote = 0;
            Game.mafias[i].doneAction = false;
        }
        Game.jocker.numberOfVote = 0;
        Game.jocker.doneAction = false;
    }

    public static void resetSilence() {
        for (int i = 0; i < numberOfCitizens; i++) {
            Game.citizens[i].isSilent = false;
        }
        for (int i = 0; i < numberOfMafias; i++) {
            Game.mafias[i].isSilent = false;
        }
        Game.jocker.isSilent = false;
    }

    public static void setNumberOfJoker() {
        Game.numberOfJoker = 1;
    }
    ///......................game........................///
    ///......................game........................///

}
