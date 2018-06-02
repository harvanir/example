package org.harvan.example.pattern.strategy.nocomposition;

public class StandardChicken implements IChicken {
    @Override
    public void fly() {
	System.out.println("terbang dikit banget.");
    }

    @Override
    public void eat() {
	System.out.println("makan cepat");
    }
}