public class MultithreadPSO extends Thread {
    Particle[] particles;
    Swarm swarm;

    public MultithreadPSO(Particle[] particles, Swarm swarm) {
        this.particles = particles;
        this.swarm = swarm;
    }

    public void run() {
        for (Particle p : particles) {
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
