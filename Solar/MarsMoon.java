package my.java.projects.Solar;


/**
 * An enumeration of the moons that orbit Mars.
 */
public enum MarsMoon implements Satellite {
    PHOBOS(1.072e16, 1.11e4, 9.38e6),
    DEIMOS(1.5e15, 6.2e4, 2.346e7);

    final Properties properties;

    private MarsMoon(double mass, double radius, double semiMajorAxis) {
        this.properties = new ImmutableProperties(this, name(), mass, radius,
                semiMajorAxis);
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public CelestialBody getParentBody() {
        return Planet.MARS;
    }

}
