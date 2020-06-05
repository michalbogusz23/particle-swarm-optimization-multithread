public class PSO {
    public static void main(String[] argv) {
        final double minRange = -5.12;
        final double maxRange = 5.12;
        final int numOfParticles = 1000;
        final int numOfEpochs = 20;
        final double inertia = 0.9;
        final double localBestAim = 2.5;
        final double globalBestAim = 2.5;
        Swarm PSO = new Swarm(minRange, maxRange, numOfParticles, numOfEpochs, inertia, localBestAim, globalBestAim);
        PSO.startAlgorithm();
    }
}
