public abstract class Citizen extends Player{
    protected static int numberOfCitizen;
    private static Citizen [] citizens;

    static {
        Citizen.citizens = new Citizen[20];
        Citizen.numberOfCitizen = 0;
    }

    protected Citizen(String name, Job jod) {
        super(name, jod);
        Citizen.citizens[Citizen.numberOfCitizen++] = this;
        if (Citizen.numberOfCitizen == Citizen.citizens.length) {
            Citizen.citizens = Citizen.resize(Citizen.citizens);
        }
    }

    public static Citizen[] resize(Citizen [] citizens_) {
        Citizen [] resize = citizens_;
        citizens_ = new Citizen[citizens_.length *2];
        System.arraycopy(resize, 0, citizens_, 0, resize.length);
        return resize;
    }

    public static int getNumberOfCitizen() {
        return numberOfCitizen;
    }

    @Override
    abstract void action(String player);

    @Override
    public void setDead() {
        if (this.isDead) return;
        super.setDead();
        Citizen.numberOfCitizen--;
    }
}
