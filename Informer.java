import java.util.Random;

public class Informer extends Citizen{
    static int [] randomNumber;
    static int randomNum;
    protected Informer(String name, Job jod) {
        super(name, jod);
        Informer.randomNumber = new int[50];
        Informer.randomNum = 0;
    }

    @Override
    void action(String player) {

    }

    public static boolean hasNumber(int number) {
        for (int i = 0; i < Informer.randomNum; i++) {
            if (Informer.randomNumber[i] == number) return true;
        }
        return false;
    }

    @Override
    public void setDead() {
        super.setDead();
        Random random = new Random();
        int mafiaNum;

        do {
            mafiaNum = random.nextInt(Mafia.getNumberOfMafia()) + 1;
        } while (Informer.hasNumber(mafiaNum));

        Informer.randomNumber[randomNum++] = mafiaNum;
        if (randomNum == Mafia.getNumberOfMafia()) { // deleting this array because, we cover all of the mafia.
            Informer.randomNumber = new int[50];
            Informer.randomNum = 0;
        }
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
