import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Swarm {
    private int numOfParticles, numOfEpochs, numOfThreads;
    private double minRange, maxRange;
    private double bestSwarmPositionEval;
    private Vector bestSwarmPosition;
    private double inertia, localBestAim, globalBestAim;

    public Swarm(double minRange, double maxRange, int numOfParticles,
                 int numOfEpochs, double inertia, double localBestAim,
                 double globalBestAim) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.numOfParticles = numOfParticles;
        this.numOfEpochs = numOfEpochs;
        this.inertia = inertia;
        this.localBestAim = localBestAim;
        this.globalBestAim = globalBestAim;
        this.numOfThreads = numOfThreads;
        bestSwarmPosition = new Vector();
        bestSwarmPositionEval = Double.POSITIVE_INFINITY;
    }

    public Swarm(double minRange, double maxRange, int numOfParticles,
                 int numOfEpochs, double inertia, double localBestAim,
                 double globalBestAim, int numOfThreads) {
        this(minRange, maxRange, numOfParticles, numOfEpochs,
                inertia, localBestAim, globalBestAim);
        this.numOfThreads = numOfThreads;

    }

    public void startAlgorithm(){
        Particle[] particles = initialize();
        for(int i = 0; i < numOfEpochs; i++) {
            for(Particle p: particles) {
                updateParticleVelocity(p);
                p.updatePosition();

                if(p.getPositionEval() < p.getBestPositionEval()) {
                    p.updateBestPosition();
                    if(p.getBestPositionEval() < bestSwarmPositionEval)
                        updateBestSwarmPosition(p);
                }
            }
        }
        System.out.printf("PSO finished working\n");
        System.out.printf("Found minimum: %f\n", bestSwarmPositionEval);
        System.out.printf("For x= %f, y= %f\n", bestSwarmPosition.getX(), bestSwarmPosition.getY());
    }

    public void startMultithreadedAlgorithm() {
        Particle[] particles = initialize();

        for (int i = 0; i < numOfEpochs; i++) {
            List<Vector> multithreadedRanges = divideForRanges(particles);
            MultithreadPSO[] threads = new MultithreadPSO[numOfThreads];
            int j = 0;
            for (Vector particlesRange : multithreadedRanges) {
                threads[j] = new MultithreadPSO(particles, particlesRange, this);
                threads[j++].start();
            }
            for (MultithreadPSO t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("PSO finished working\n");
        System.out.printf("Found minimum: %f\n", bestSwarmPositionEval);
        System.out.printf("For x= %f, y= %f\n", bestSwarmPosition.getX(), bestSwarmPosition.getY());
    }

    private List<Vector> divideForRanges(Particle[] particles) {
        int size = particles.length;
        List<Vector> threadParticlesList = new LinkedList<>();
        int threadSize = (int) size / numOfThreads;
        int i;
        for (i = 0; i < numOfThreads - 1; i++) {
            threadParticlesList.add(new Vector(i * threadSize, (i + 1) * threadSize));
        }
        threadParticlesList.add(new Vector(i * threadSize, size));

        return threadParticlesList;
    }

    public void updateBestSwarmPosition(Particle p) {
        bestSwarmPositionEval = p.getBestPositionEval();
        bestSwarmPosition = p.getBestPosition().clone();
    }

    private Particle[] initialize() {
        Particle[] particles = new Particle[numOfParticles];
        for(int i = 0; i < numOfParticles; i++) {
            Particle particle = new Particle(minRange, maxRange);
            particles[i] = particle;
            if(particle.getBestPositionEval() < bestSwarmPositionEval){
                bestSwarmPositionEval = particle.getBestPositionEval();
                bestSwarmPosition = particle.getBestPosition();
            }
        }
        return particles;
    }

    public void updateParticleVelocity(Particle p) {
        Vector oldVelocity = p.getVelocity();
        Vector position = p.getPosition();
        Vector particleBestPosition = p.getBestPosition();

        Random r = new Random();
        double rl = r.nextDouble();
        double rg = r.nextDouble();

        Vector newVelocity = oldVelocity.clone();
        newVelocity.mul(inertia);

        Vector secondPart = particleBestPosition.diff(position);
        secondPart.mul(localBestAim * rl);

        Vector thirdPart = bestSwarmPosition.diff(position);
        thirdPart.mul(globalBestAim * rg);

        newVelocity.sum(secondPart);
        newVelocity.sum(thirdPart);

        p.setVelocity(newVelocity);
    }

    public double getBestSwarmPositionEval() {
        return bestSwarmPositionEval;
    }
}
