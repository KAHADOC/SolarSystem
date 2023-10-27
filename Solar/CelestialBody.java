package my.java.projects.Solar;

public interface CelestialBody {
    public static final double bigG = 6.637e-11;

    Properties getProperties();

    interface Properties {
        double getMass();
        double getRadius();
        Satellite[] getSatellites();
        String name();
        void printSummary();
    }



    /**
     * An immutable representation of the properties of the {@link
     * CelestialBody}. Immutability is necessary to honor the contract for
     * enums.
     */
    static final class ImmutableProperties implements Properties {
        private final double mass;
        private final double radius;
        private final String name;
        private final Satellite[] satellites;

        public ImmutableProperties(String name, double mass, double radius,
                                   Satellite... satellites) {
            this.name = name;
            this.mass = mass;
            this.radius = radius;
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

        public void printSummary() {
            System.out.println("Summary for " + name() + ":");
            System.out.printf("Surface Gravity: %1$e m/s²\n\n",
                    Util.calcSurfaceGravity(this));

            if (satellites != null) {
                for (Satellite satellite : getSatellites()) {
                    satellite.getProperties().printSummary();
                }
            }
        }
    }



    /**
     * A static class that is used to simulate the logic that would be
     * implemented in an abstract superclass for Satellite enums, if Java
     * allowed such a thing.
     */
    static class Util {

        // private constructor to ensure static-only access
        private Util() {
        }

        /**
         * Calculates and returns the surface gravity of the given {@link
         * CelestialBody}. The result is given in m/s².
         *
         * @param celestialBody the body whose surface gravity is being
         *                      calculated
         * @return the surface gravity, in m/s²
         */
        public static double calcSurfaceGravity(Properties celestialBody) {
            return bigG * celestialBody.getMass() / Math.pow(
                    celestialBody.getRadius(), 2.0);
        }
    }
}
