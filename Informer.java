import java.util.Random;

public class Informer extends Citizen{
    protected Informer(String name, Job jod) {
        super(name, jod);
    }

    @Override
    void action(String player) {

    }

    @Override
    public void setDead() {
        super.setDead();
        Random random = new Random();
        int mafiaNum = random.nextInt(Mafia.getNumberOfMafia()) + 1;
        for (int i = 0; i < Mafia.getMafiasLength(); i++) {
            if (!Mafia.getMafias()[i].isDead) {
                mafiaNum--;
                if (mafiaNum==0) {
                    System.out.println(Mafia.getMafias()[i].getName().charAt(0));
                }
            }
        }
    }

    @Override
    public String toString() {
        return name + " : " + this.job;
    }

}
