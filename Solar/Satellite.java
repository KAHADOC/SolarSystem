

package my.java.projects.Solar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static my.java.projects.Solar.CelestialBody.bigG;

/**
 * An interface representing a generic satellite (planet, moon, etc.). This
 * interface is often implemented by {@link Enum} types. In order to simulate
 * the behavior of abstract-class inheritance for those enums, this interface
 * has inner classes and inner interfaces.
 */
public interface Satellite extends CelestialBody {
    /**
     * @return the representation of all the values of the Satellite
     */
    Properties getProperties();

    /**
     * Returns the parent body for this Satellite (as in the star around which
     * this planet orbits).
     *
     * @return the parent body for this Satellite
     */
    CelestialBody getParentBody();



    /**
     * An immutable representation of the properties of the {@link Satellite}.
     * Immutability is necessary to honor the contract for enums.
     */
    static final class ImmutableProperties implements CelestialBody
            .Properties {
        private final Satellite satellite;
        private final double mass;
        private final double radius;
        private final double semiMajorAxis;
        private final String name;
        private final Satellite[] satellites;

        public ImmutableProperties(Satellite satellite, String name,
                                   double mass, double radius, double semiMajorAxis,
                                   Satellite... satellites) {
            this.satellite = satellite;
            this.name = name;
            this.mass = mass;
            this.radius = radius;
            this.semiMajorAxis = semiMajorAxis;
            this.satellites = satellites == null ? null : satellites.clone();
        }

        @Override
        public double getMass() {
            return mass;
        }

        @Override
        public double getRadius() {
            return radius;
        }

        @Override
        public Satellite[] getSatellites() {
            return satellites == null ? null : satellites.clone();
        }

        @Override
        public String name() {
            return name;
        }

        private CelestialBody getParentBody() {
            return satellite.getParentBody();
        }

        /**
         * Calculates and returns the orbital period of this {@link Satellite}
         * which orbits the given parent body. The result is given in seconds.
         *
         * @param parentBody the body around which the satellite orbits
         * @return the orbital period, in seconds
         */
        public double getOrbitalPeriod(CelestialBody parentBody) {
            return 2 * Math.PI * Math.sqrt(Math.pow(semiMajorAxis, 3.0) /
                    (bigG *
                            parentBody.getProperties().getMass()));
        }

        /**
         * Prints a summary of the calculated orbital and physical
         * characteristics of this {@link Satellite} and any of its own
         * satellites.
         */
        @Override
        public void printSummary() {
            System.out.println("Summary for " + getParentage());

            CelestialBody parentBody = getParentBody();
            long orbitalPeriod = (long) getOrbitalPeriod(parentBody);
            System.out.printf("Orbital Period: %1$d hours/%2$d days\n",
                    TimeUnit.SECONDS.toHours(orbitalPeriod),
                    TimeUnit.SECONDS.toDays(orbitalPeriod));

            System.out.printf("Surface Gravity: %1$e m/s²\n\n",
                    CelestialBody.Util.calcSurfaceGravity(this));

            if (satellites != null) {
                for (Satellite child : satellites) {
                    child.getProperties().printSummary();
                }
            }
        }

        /**
         * Generates and returns a String listing all the orbital parents of
         * this satellite.
         * <p/>
         * For example, for the Earth's moon, it would return
         * <pre>", satellite of EARTH, satellite of SOL"</pre>.
         *
         * @return a String showing orbital parentage
         */
        private String getParentage() {
            List<CelestialBody> parents = new ArrayList<CelestialBody>();
            CelestialBody parentBody = getParentBody();
            parents.add(parentBody);
            while (parentBody instanceof Satellite) {
                Satellite satelliteParent = (Satellite) parentBody;
                parentBody = satelliteParent.getParentBody();
                parents.add(parentBody);
            }

            StringBuilder sb = new StringBuilder(name());
            for (CelestialBody parent : parents) {
                sb.append(", satellite of ");
                sb.append(parent.getProperties().name());
            }
            sb.append(":");

            return sb.toString();
        }
    }
}
