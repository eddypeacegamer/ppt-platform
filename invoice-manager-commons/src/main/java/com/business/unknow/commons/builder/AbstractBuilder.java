package com.business.unknow.commons.builder;

public abstract class AbstractBuilder<T> {
	protected T instance;

	public AbstractBuilder(T instance) {
		this.instance = instance;
		setDefaults();
	}

	protected void setDefaults() {

	}

	public T build() {
		inject();
		validate();
		return instance;
	}

	public void validate() {
	}

	public void inject() {
	}
}