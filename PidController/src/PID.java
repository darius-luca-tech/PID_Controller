public class PID {
    private double point;
    private double kP, kI, kD;
    private double limit_min, limit_max;
    private double time;
    private double previousError;
    private double integralError;
    private double derivativeError;

    public PID(double point, double kP, double kI, double kD) {
        this.point = point;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        init();
    }

    public void init() {
        time = 0;
        previousError = 0;
        integralError = 0;
    }

    public double getOutput(double currentTime, double currentValue) {
        double actualError = point - currentValue;
        double dt; //delta t(time)
        if(time != 0) {
            dt = currentTime - time;
        } else {
            dt = 0;
        }

        if(dt != 0) {
            derivativeError = (actualError - previousError) / dt;
        } else {
            derivativeError = 0;
        }

        integralError += actualError * dt;
        previousError = actualError;

        return LimitCheck((kP * actualError) + (kI * integralError) + (kD * derivativeError));

    }

    public double LimitCheck(double output) {
        if(!Double.isNaN(limit_min) && output < limit_min) {
            return limit_min;
        } else if(!Double.isNaN(limit_max) && output > limit_max) {
            return limit_max;
        } else {
            return output;
        }
    }

    public double getPoint() {
        return point;
    }
}
