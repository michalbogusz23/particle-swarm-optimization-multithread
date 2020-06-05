import java.time.Duration;
import java.time.Instant;

public class PSO {
    public static void main(String[] argv) {
        final double minRange = -5.12;
        final double maxRange = 5.12;
        final int numOfParticles = 1000000;
        final int numOfEpochs = 20;
        final double inertia = 0.9;
        final double localBestAim = 2.5;
        final double globalBestAim = 2.5;
        Swarm PSO = new Swarm(minRange, maxRange, numOfParticles, numOfEpochs, inertia, localBestAim, globalBestAim);
        
        Instant start = Instant.now();
        PSO.startAlgorithm();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.printf("Time of execution  %dms\n", timeElapsed);
    }
}
