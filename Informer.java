public class Informer extends Citizen{
    protected Informer(String name, Job jod) {
        super(name, jod);
    }

    @Override
    void action(String player) {

    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

    }
}
