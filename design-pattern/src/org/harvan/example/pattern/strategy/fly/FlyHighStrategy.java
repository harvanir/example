package org.harvan.example.pattern.strategy.fly;

public class FlyHighStrategy implements IFly {
    @Override
    public void fly() {
	System.out.print(toString());
	System.out.println(" -> Terbang tinggi banget.");
    }

    @Override
    public String toString() {
	return " -> " + getClass().getSimpleName();
    }
}