public class Villager extends Citizen{
    protected Villager(String name, Job jod) {
        super(name, jod);
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

    @Override
    void action(String player) {

    }
}
