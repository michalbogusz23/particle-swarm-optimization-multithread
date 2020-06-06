public class MultithreadPSO extends Thread {
    Particle[] particles;
    Swarm swarm;
    Vector range;

    public MultithreadPSO(Particle[] particles, Vector range, Swarm swarm) {
        this.particles = particles;
        this.swarm = swarm;
        this.range = range;
    }

    public void run() {
        for (int i = (int) range.getX(); i < range.getY(); i++) {
            Particle p = particles[i];
            swarm.updateParticleVelocity(p);
            p.updatePosition();

            if (p.getPositionEval() < p.getBestPositionEval()) {
                p.updateBestPosition();
                if (p.getBestPositionEval() < swarm.getBestSwarmPositionEval())
                    swarm.updateBestSwarmPosition(p);
            }
        }
    }
}
