import java.util.Objects;

public class GodFather extends Mafia{
    public GodFather(String name, Job jod) {
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
