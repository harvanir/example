package org.harvan.example.pattern.generic;

public abstract class AbstractValidate<T extends IHolder> implements IValidate<T> {
    @Override
    public final void validate(T holder) {
	validateNullChecking();
	validateInternal(holder);
    }

    public abstract void validateInternal(T holder);

    public abstract void validateNullChecking();
}