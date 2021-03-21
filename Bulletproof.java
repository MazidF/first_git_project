public class Bulletproof extends Citizen{
    protected Bulletproof(String name, Job jod) {
        super(name, jod);
    }

    @Override
    void action(String player) {

    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }
}
