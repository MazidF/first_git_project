import java.util.Objects;

public class SimpleMafia extends Mafia{
    public SimpleMafia(String name, Job jod) {
        super(name, jod);
        this.hasNightWork = true;
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

    @Override
    void action(String player) {
        Objects.requireNonNull(Game.finder(player)).numberOfVote++;
    }
}
