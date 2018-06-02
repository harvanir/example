package org.harvan.example.pattern.generic;

public class ValidateB extends AbstractValidate<HolderB> {
    @Override
    public void validateInternal(HolderB holder) {
	System.out.println("validate internal B");
    }

    @Override
    public void validateNullChecking() {
	System.out.println("validate null checking B");
    }
}