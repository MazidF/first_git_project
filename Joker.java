public class Joker extends Citizen{

    protected Joker(String name, Job jod) {
        super(name, jod);
        Game.setNumberOfJoker();
    }

    @Override
    void action(String player) {

    }

    @Override
    public void setDead() {
        if (this.isDead) return;
        super.setDead();
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }
}
