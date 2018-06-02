package org.harvan.example.pattern.strategy.eat;

public class EatFastStrategy implements IEat {
    @Override
    public void eat() {
	System.out.print(toString());
	System.out.println(" -> Makan sangat cepat.");
    }

    @Override
    public String toString() {
	return " -> " + getClass().getSimpleName();
    }
}