package my.java.projects.Solar;

/**
 * An enumeration of the planets in our solar system. We only show the inner
 * planets, for simplicity.
 */
public enum Planet implements Satellite {
    MERCURY(0.3302e24, 2.4397e6, 57.91e9),
    VENUS(4.869e24, 6.0518e6, 108.21e9),
    EARTH(5.9742e24, 6.3781e6, 149.6e9, EarthMoon.values()),
    MARS(0.64185e24, 3.3962e6, 227.92e9, MarsMoon.values());

    final ImmutableProperties properties;

    private Planet(double mass, double radius, double semiMajorAxis) {
        this(mass, radius, semiMajorAxis, null);
    }

    private Planet(double mass, double radius, double semiMajorAxis,
                   Satellite[] satellites) {
        this.properties = new ImmutableProperties(this, name(), mass, radius,
                semiMajorAxis, satellites);
    }

    public ImmutableProperties getProperties() {
        return properties;
    }

    @Override
    public CelestialBody getParentBody() {
        return Star.SOL;
    }

}