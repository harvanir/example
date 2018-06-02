package org.harvan.example.pattern.strategy.nocomposition;

public class WildChickenOld implements IChicken {
    @Override
    public void fly() {
	System.out.println("terbang tinggi banget kaya burung.");
    }

    @Override
    public void eat() {
	System.out.println("makan cepat");
    }
}