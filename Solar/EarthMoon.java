package my.java.projects.Solar;


/**
 * An enumeration of the moons that orbit the Earth.
 */
public enum EarthMoon implements Satellite {
    LUNA(0.07349e24, 1.7381e6, 0.3844e9);

    final Properties properties;

    private EarthMoon(double mass, double radius, double semiMajorAxis) {
        this.properties = new ImmutableProperties(this, name(), mass, radius,
                semiMajorAxis);
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public CelestialBody getParentBody() {
        return Planet.EARTH;
    }

}