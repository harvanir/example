package org.harvan.example.pattern.generic;

public class ValidateA extends AbstractValidate<HolderA> {
    @Override
    public void validateInternal(HolderA holder) {
	System.out.println("validate internal A");
    }

    @Override
    public void validateNullChecking() {
	System.out.println("validate null checking A");
    }
}