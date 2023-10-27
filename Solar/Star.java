package my.java.projects.Solar;
/**
 * An enumeration of the stars at the center of solar systems.
 */
public enum Star implements CelestialBody {
    SOL(1.98892e30, 6.955e8, Planet.values()); // singleton

    private final Properties properties;

    private Star(double mass, double radius, Satellite[] satellites) {
        this.properties = new ImmutableProperties(name(), mass, radius,
                satellites);
    }

    public Properties getProperties() {
        return properties;
    }

}
