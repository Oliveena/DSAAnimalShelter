package factories;

import data.Adopter;

public class AdopterFactory {
        public static Adopter createAdopter(String name, String phoneNumber) {
            return new Adopter(name, phoneNumber);
        }
}
