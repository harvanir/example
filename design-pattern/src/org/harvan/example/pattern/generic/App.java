package org.harvan.example.pattern.generic;

public class App {
    private App() {
    }

    public static void main(String[] args) {
	IValidate<HolderA> validateA = new ValidateA();
	IValidate<HolderB> validateB = new ValidateB();

	HolderA holderA = new HolderA();
	HolderB holderB = new HolderB();

	validateA.validate(holderA);
	validateB.validate(holderB);
    }
}