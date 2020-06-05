import java.time.Duration;
import java.time.Instant;

public class PSO {
    public static void main(String[] argv) {
        final double minRange = -5.12;
        final double maxRange = 5.12;
        final int numOfParticles = 1000000;
        final int numOfEpochs = 100;
        final double inertia = 0.9;
        final double localBestAim = 2.5;
        final double globalBestAim = 2.5;
        final int numOfThreads = 8;
        Swarm PSO = new Swarm(minRange, maxRange, numOfParticles, numOfEpochs,
                inertia, localBestAim, globalBestAim);
        System.out.printf("Starting sequential algorithm\n");
        Instant start = Instant.now();
        PSO.startAlgorithm();
        Instant finish = Instant.now();
        long sequentialTimeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Time of execution  %dms\n\n", sequentialTimeElapsed);

        Swarm multithreadedPSO = new Swarm(minRange, maxRange, numOfParticles, numOfEpochs,
                inertia, localBestAim, globalBestAim, numOfThreads);
        System.out.printf("Starting parallel algorithm with %d threads\n", numOfThreads);
        start = Instant.now();
        PSO.startAlgorithm();
        finish = Instant.now();
        long parallelTimeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Time of execution  %dms\n\n", parallelTimeElapsed);

        int speedUp = (int) (((double) (sequentialTimeElapsed - parallelTimeElapsed) / sequentialTimeElapsed) * 100);
        System.out.printf("Speed up equals: %d%%\n", speedUp);
    }
}
