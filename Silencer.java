import java.util.Objects;

public class Silencer extends Mafia{
    public Silencer(String name, Job jod) {
        super(name, jod);
        this.hasNightWork = true;
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

    void action2(String player) {
        Objects.requireNonNull(Game.finder(player)).numberOfVote++;
    }
    @Override
     void action(String player) {
        if (this.doneAction) {
            action2(player);
            return;
        }
        Objects.requireNonNull(Game.finder(player)).isSilent = true;
        this.doneAction = true;
    }
}
