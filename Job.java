public enum Job {
    SimpleMafia, Silencer, GodFather, Villager, BulletProof, Detective, Doctor, Informer, Joker;

    @Override
    public String toString() {
        if (this.equals(Job.SimpleMafia)) {
            return "mafia";
        }
        if (this.equals(Job.Silencer)) {
            return "silencer";
        }
        if (this.equals(Job.GodFather)) {
            return "godfather";
        }
        if (this.equals(Job.Villager)) {
            return "villager";
        }
        if (this.equals(Job.Detective)) {
            return "detective";
        }
        if (this.equals(Job.Doctor)) {
            return "doctor";
        }
        if (this.equals(Job.Informer)) {
            return "informer";
        }
        if (this.equals(Job.BulletProof)) {
            return "bulletproof";
        }
        if (this.equals(Job.Joker)) {
            return "Joker";
        }
        Game.gameIsOver = true;
        return "null";
    }
}
