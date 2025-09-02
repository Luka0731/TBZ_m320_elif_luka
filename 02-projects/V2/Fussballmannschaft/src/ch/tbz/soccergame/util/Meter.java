package ch.tbz.soccergame.util;

import ch.tbz.soccergame.util.frontendSamples.ConsoleStyle;
import ch.tbz.soccergame.util.frontendSamples.Output;

public class Meter {
    private final int meter;
    private double value;

    public Meter() {
        this.value = 0.0;
        this.meter = 100;
    }

    public Meter(int meter) {
        this.value = 0.0;
        this.meter = meter;
    }

    public Meter(double value) {
        this.value = value;
        this.meter = 100;
    }

    public Meter(int meter, double value) {
        this.meter = meter;
        this.value = value;
    }

    public void set(double value) {
        changeValue(value);
    }

    public void add(double value) {
        changeValue(this.value + value);
    }

    public void remove(double value) {
        changeValue(this.value - value);
    }

    public boolean isInMeter(double value) {
        return value >= 0 && value <= meter;
    }

    public String toSting() {
        return value + "/" + meter;
    }


    // |--- helper methods ---|

    private void changeValue(double value) {
        if (value >= 0 && value <= meter) {
            this.value = value;
        } else if (value > meter) {
            this.value = meter;
        } else {
            this.value = 0;
        }
    }


    // |--- getters ---|

    public int getMeterMax() {
        return meter;
    }

    public int getMeterMin() {
        return 0;
    }

    public double getValue() {
        return value;
    }
}
