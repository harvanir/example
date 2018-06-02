package org.harvan.example.pattern.strategy;

import org.harvan.example.pattern.strategy.composition.Chicken;
import org.harvan.example.pattern.strategy.composition.WildChickenNew;
import org.harvan.example.pattern.strategy.nocomposition.IChicken;
import org.harvan.example.pattern.strategy.nocomposition.StandardChicken;
import org.harvan.example.pattern.strategy.nocomposition.WildChickenOld;

public class App {
    private App() {
	// hide
    }

    public static void main(String[] args) {
	IChicken standardChicken = new StandardChicken();

	standardChicken.fly();
	standardChicken.eat();

	IChicken wildChickenOld = new WildChickenOld();

	wildChickenOld.fly();
	wildChickenOld.eat();

	Chicken wildChicken = new WildChickenNew();
	wildChicken.fly();
	wildChicken.eat();
    }
}