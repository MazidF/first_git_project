public abstract class Player {
    public static String winner = "null";

    public boolean doneAction = false;
    //public boolean treatByDoctor = false;
    public boolean isSilent = false;
    public boolean isDead = false;
    public boolean hasNightWork = false;
    public int numberOfVote = 0;
    protected String name;
    protected Job job;

    protected Player(String name, Job job) {
        this.name = name;
        this.job = job;
    }

    abstract void action(String player);

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setDead() {
        System.out.println(name + " died");
        isDead = true;
    }

    protected static void setWinner(String winner) {
        Player.winner = winner;
        Game.gameIsOver = true;
    }

    protected String information() {
        return "name:" + name +" Dead:" + isDead + " silence:" + isSilent + " votes:" + numberOfVote;
    }
}
