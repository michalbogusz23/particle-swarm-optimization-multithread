public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {

    }

    public void mul(double mult){
        x *= mult;
        y *= mult;
        z *= mult;
    }

    public Vector diff( Vector b) {
        Vector result = new Vector();
        result.x = this.x - b.x;
        result.y = this.y - b.y;
        result.z = this.z - b.z;

        return result;
    }

    public void sum( Vector b) {
        this.x += b.x;
        this.y += b.y;
        this.z += b.z;
    }
    public Vector clone(){
        return new Vector(x, y, z);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
