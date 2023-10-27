# SolarSystem
My project for learning Enums and Interfaces in Java (adapted from an article by Lance Finney)

There are some areas in which Java's enums are restricted - enum instances implicitly extend java.lang.Enum, are implicitly final, and cannot be defined using generics. This means that a developer cannot directly create an enum that contains all the instances of another, cannot inherit logic from an abstract parent, and cannot be typed for generics.

This is an attempt to workaround these restrictions by using interfaces.
