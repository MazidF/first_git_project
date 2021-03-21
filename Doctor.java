import java.util.Objects;

public class Doctor extends Citizen{
    protected Doctor(String name, Job jod) {
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
            return;
        }
        Objects.requireNonNull(Game.finder(player)).numberOfVote--;
        this.doneAction = true;
    }
}
