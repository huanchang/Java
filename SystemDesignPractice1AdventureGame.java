// This is a practice of Strategy Pattern

public class SystemDesignPractice1AdventureGame{
    public static void main(String[] args) {
        // demo for class definition for a Adventure Game
        // There are different characters and each character can have one weapon at a time.
        // Character can change weapon at any time.
        
        Character queen = new Queen();
        Character knight = new Knight();
        Character troll = new Troll();
        
        queen.fight();
        knight.fight();
        troll.fight();
        
        queen.setWeapon(new Axe());
        knight.setWeapon(new Sword());
        
        queen.fight();
        knight.fight();
        troll.fight();
    }
}


abstract class Character{

    protected Weapon weapon;
    
    public abstract void fight();
    public void useWeapon() {
        weapon.useWeapon();
    }
    public void setWeapon(Weapon w) {
        weapon = w;
    }
}

class Queen extends Character{
    public Queen() {
        weapon = new Sword();
    }
    @Override
    public void fight() {
        System.out.println("I'm a queen.");
        useWeapon();
    };
}

class Troll extends Character{
    public Troll() {
        weapon = new Knife();
    }
    @Override
    public void fight() {
        System.out.println("I'm a troll.");
        useWeapon();
    };
}

class Knight extends Character{
    public Knight() {
        weapon = new BowAndArrow();
    }
    @Override
    public void fight() {
        System.out.println("I'm a knight.");
        useWeapon();
    };
}


// Encapsulate weapon behaviors

interface Weapon{
    public void useWeapon();
}

class Knife implements Weapon{
    public void useWeapon() {
        System.out.println("Knife fight.");
    }
}

class BowAndArrow implements Weapon{
    public void useWeapon() {
        System.out.println("BowAndArrow fight.");
    }
}

class Axe implements Weapon{
    public void useWeapon() {
        System.out.println("Axe fight.");
    }
}

class Sword implements Weapon{
    public void useWeapon() {
        System.out.println("Sword fight.");
    }
}