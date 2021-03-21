import java.util.Objects;

public class Detective extends Citizen{
    protected Detective(String name, Job jod) {
        super(name, jod);
        this.hasNightWork = true;
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

    @Override
    void action(String player) {
        if (this.doneAction) {
            System.out.println("detective has already asked :(");
            return;
        }
        this.doneAction = true;
        Player player1 = Objects.requireNonNull(Game.finder(player));
        if (player1 instanceof Mafia) {
            System.out.println(!player1.getJob().equals("godfather"));
            return;
        }
        System.out.println("No");
    }
}
