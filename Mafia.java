public abstract class Mafia extends Player{
    private static int numberOfMafia;
    private static Mafia [] mafias;
    static {
        Mafia.mafias = new Mafia[20];
        Mafia.numberOfMafia = 0;
    }

    public Mafia(String name, Job jod) {
        super(name, jod);
        Mafia.mafias[Mafia.numberOfMafia++] = this;
        if (Mafia.numberOfMafia == Mafia.mafias.length) {
            Mafia.mafias = Mafia.resize(Mafia.mafias);
        }
        this.hasNightWork = true;
    }

    public static Mafia[] resize(Mafia [] mafias_) {
        Mafia [] resize = mafias_;
        mafias_ = new Mafia[mafias_.length *2];
        System.arraycopy(resize, 0, mafias_, 0, resize.length);
        return mafias_;
    }

    public static int getNumberOfMafia() {
        return numberOfMafia;
    }

    /////////////////////////////////////////////////
/////////////////////////////////////////////////
    @Override
    abstract void action(String player) ;

    @Override
    public void setDead() {
        if (this.isDead) return;
        super.setDead();
        Mafia.numberOfMafia--;
    }

    public static int getMafiasLength() {
        return Mafia.mafias.length;
    }

    public static Mafia[] getMafias() {
        return mafias;
    }
}
