package org.harvan.example.pattern.strategy.composition;

import org.harvan.example.pattern.strategy.eat.EatFastStrategy;
import org.harvan.example.pattern.strategy.fly.FlyHighStrategy;

public class WildChickenNew extends Chicken {
    public WildChickenNew() {
	flyComposition = new FlyHighStrategy();
	eatComposition = new EatFastStrategy();
    }
}