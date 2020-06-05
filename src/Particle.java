import java.util.Random;

public class Particle {
    private Vector position;
    private Vector bestPosition;
    private Vector velocity;
    private double bestPositionEval;
    private double positionEval;

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    Particle(double minValue, double maxValue) {
        position = new Vector();
        bestPosition = new Vector();
        velocity = new Vector();

        setRandomStartingPosition(minValue, maxValue);
        bestPosition = position.clone();
        setRandomVelocity(minValue, maxValue);
        bestPositionEval = Function.rosenbrock(bestPosition.getX(), bestPosition.getY());
    }

    private void setRandomVelocity(double minValue, double maxValue) {
        double x = rand(-Math.abs(maxValue - minValue), Math.abs(maxValue - minValue));
        double y = rand(-Math.abs(maxValue - minValue), Math.abs(maxValue - minValue));
        double z = rand(-Math.abs(maxValue - minValue), Math.abs(maxValue - minValue));
        velocity.set(x, y, z);
    }

    private void setRandomStartingPosition(double minValue, double maxValue) {
        double x = rand(minValue, maxValue);
        double y = rand(minValue, maxValue);
        double z = rand(minValue, maxValue);
        position.set(x, y, z);
    }
    private double rand(double minValue, double maxValue) {
        Random r = new Random();
        return r.nextDouble()*(maxValue - minValue) + minValue;
    }

    public double getBestPositionEval() {
        return bestPositionEval;
    }
    public Vector getBestPosition() {
        return bestPosition;
    }

    public Vector getPosition() {
        return position;
    }

    public double getPositionEval() {
        return positionEval;
    }

    public void setPositionEval(double positionEval) {
        this.positionEval = positionEval;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
    public void evalPosition(){
        positionEval = Function.rosenbrock(position.getX(), position.getY());
    }
    public void updatePosition() {
        position.sum(velocity);
        evalPosition();
    }

    public void updateBestPosition() {
        bestPositionEval = positionEval;
        bestPosition = position.clone();
    }
}
