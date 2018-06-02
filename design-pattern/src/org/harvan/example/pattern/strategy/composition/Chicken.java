package org.harvan.example.pattern.strategy.composition;

import org.harvan.example.pattern.strategy.eat.IEat;
import org.harvan.example.pattern.strategy.fly.IFly;

public class Chicken {
    IFly flyComposition;
    IEat eatComposition;

    public void fly() {
	System.out.print(toString());
	flyComposition.fly();
    }

    public void eat() {
	System.out.print(toString());
	eatComposition.eat();
    }

    @Override
    public String toString() {
	return " -> " + getClass().getSimpleName();
    }
}