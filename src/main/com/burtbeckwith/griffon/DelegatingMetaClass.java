package com.burtbeckwith.griffon;

import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import groovy.lang.MetaProperty;
import groovy.lang.MissingMethodException;
import groovy.lang.MissingPropertyException;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.ast.ClassNode;

public class DelegatingMetaClass implements MetaClass {

	protected MetaClass real;
	protected MetaClass delegate;

	public DelegatingMetaClass(MetaClass real, MetaClass delegate) {
		this.real = real;
		this.delegate = delegate;
	}

	public List<MetaMethod> respondsTo(Object obj, String name, Object[] argTypes) {
		List<MetaMethod> methods = new ArrayList<MetaMethod>();
		methods.addAll(real.respondsTo(obj, name, argTypes));
		methods.addAll(delegate.respondsTo(obj, name, argTypes));
		return methods;
	}

	public List<MetaMethod> respondsTo(Object obj, String name) {
		List<MetaMethod> methods = new ArrayList<MetaMethod>();
		methods.addAll(real.respondsTo(obj, name));
		methods.addAll(delegate.respondsTo(obj, name));
		return methods;
	}

	public MetaProperty hasProperty(Object obj, String name) {
		MetaProperty prop = real.hasProperty(obj, name);
		if (prop == null) {
			prop = delegate.hasProperty(obj, name);
		}
		return prop;
	}

	public MetaProperty getMetaProperty(String name) {
		MetaProperty prop = real.getMetaProperty(name);
		if (prop == null) {
			prop = delegate.getMetaProperty(name);
		}
		return prop;
	}

	public MetaMethod getStaticMetaMethod(String name, Object[] args) {
		MetaMethod method = real.getStaticMetaMethod(name, args);
		if (method == null) {
			method = delegate.getStaticMetaMethod(name, args);
		}
		return method;
	}

	public MetaMethod getMetaMethod(String name, Object[] args) {
		MetaMethod method = real.getMetaMethod(name, args);
		if (method == null) {
			method = delegate.getMetaMethod(name, args);
		}
		return method;
	}

	public Class<?> getTheClass() {
		return real.getTheClass();
	}

	public Object invokeConstructor(Object[] arguments) {
		try {
			return real.invokeConstructor(arguments);
		}
		catch (GroovyRuntimeException e) {
			return delegate.invokeConstructor(arguments);
		}
	}

	public Object invokeMethod(Object object, String methodName, Object[] arguments) {
		try {
			return real.invokeMethod(object, methodName, arguments);
		}
		catch (MissingMethodException e) {
			return delegate.invokeMethod(object, methodName, arguments);
		}
	}

	public Object invokeMethod(Object object, String methodName, Object arguments) {
		try {
			return real.invokeMethod(object, methodName, arguments);
		}
		catch (MissingMethodException e) {
			return delegate.invokeMethod(object, methodName, arguments);
		}
	}

	public Object invokeStaticMethod(Object object, String methodName, Object[] arguments) {
		try {
			return real.invokeStaticMethod(object, methodName, arguments);
		}
		catch (MissingMethodException e) {
			return delegate.invokeStaticMethod(object, methodName, arguments);
		}
	}

	public Object getProperty(Object object, String property) {
		try {
			return real.getProperty(object, property);
		}
		catch (MissingPropertyException e) {
			return delegate.getProperty(object, property);
		}
	}

	public void setProperty(Object object, String property, Object newValue) {
		try {
			real.setProperty(object, property, newValue);
		}
		catch (MissingPropertyException e) {
			delegate.setProperty(object, property, newValue);
		}
	}

	public Object getAttribute(Object object, String attribute) {
		try {
			return real.getAttribute(object, attribute);
		}
		catch (MissingPropertyException e) {
			return delegate.getAttribute(object, attribute);
		}
	}

	public void setAttribute(Object object, String attribute, Object newValue) {
		try {
			real.setAttribute(object, attribute, newValue);
		}
		catch (MissingPropertyException e) {
			delegate.setAttribute(object, attribute, newValue);
		}
	}

	public Object invokeMethod(@SuppressWarnings("rawtypes") Class sender, Object receiver, String methodName,
			Object[] arguments, boolean isCallToSuper, boolean fromInsideClass) {
		try {
			return real.invokeMethod(sender, receiver, methodName, arguments, isCallToSuper, fromInsideClass);
		}
		catch (MissingMethodException e) {
			return delegate.invokeMethod(sender, receiver, methodName, arguments, isCallToSuper, fromInsideClass);
		}
	}

	public Object getProperty(@SuppressWarnings("rawtypes") Class sender, Object receiver, String property,
			boolean isCallToSuper, boolean fromInsideClass) {
		try {
			return real.getProperty(sender, receiver, property, isCallToSuper, fromInsideClass);
		}
		catch (MissingPropertyException e) {
			return delegate.getProperty(sender, receiver, property, isCallToSuper, fromInsideClass);
		}
	}

	public void setProperty(@SuppressWarnings("rawtypes") Class sender, Object receiver, String property, Object value,
			boolean isCallToSuper, boolean fromInsideClass) {
		try {
			real.setProperty(sender, receiver, property, value, isCallToSuper, fromInsideClass);
		}
		catch (MissingPropertyException e) {
			delegate.setProperty(sender, receiver, property, value, isCallToSuper, fromInsideClass);
		}
	}

	public Object invokeMissingMethod(Object instance, String methodName, Object[] arguments) {
		try {
			return real.invokeMissingMethod(instance, methodName, arguments);
		}
		catch (MissingMethodException e) {
			return delegate.invokeMissingMethod(instance, methodName, arguments);
		}
	}

	public Object invokeMissingProperty(Object instance, String propertyName, Object optionalValue, boolean isGetter) {
		try {
			return real.invokeMissingProperty(instance, propertyName, optionalValue, isGetter);
		}
		catch (MissingMethodException e) {
			return delegate.invokeMissingProperty(instance, propertyName, optionalValue, isGetter);
		}
	}

	public Object getAttribute(@SuppressWarnings("rawtypes") Class sender, Object receiver, String messageName, boolean useSuper) {
		try {
			return real.getAttribute(sender, receiver, messageName, useSuper);
		}
		catch (MissingMethodException e) {
			return delegate.getAttribute(sender, receiver, messageName, useSuper);
		}
	}

	public void setAttribute(@SuppressWarnings("rawtypes") Class sender, Object receiver, String messageName, Object messageValue,
			boolean useSuper, boolean fromInsideClass) {
		try {
			real.setAttribute(sender, receiver, messageName, messageValue, useSuper, fromInsideClass);
		}
		catch (MissingMethodException e) {
			delegate.setAttribute(sender, receiver, messageName, messageValue, useSuper, fromInsideClass);
		}
	}

	public void initialize() {
		real.initialize();
		delegate.initialize();
	}

	public List<MetaProperty> getProperties() {
		List<MetaProperty> properties = new ArrayList<MetaProperty>();
		properties.addAll(real.getProperties());
		properties.addAll(delegate.getProperties());
		return properties;
	}

	public List<MetaMethod> getMethods() {
		List<MetaMethod> methods = new ArrayList<MetaMethod>();
		methods.addAll(real.getMethods());
		methods.addAll(delegate.getMethods());
		return methods;
	}

	public ClassNode getClassNode() {
		return real.getClassNode();
	}

	public List<MetaMethod> getMetaMethods() {
		List<MetaMethod> methods = new ArrayList<MetaMethod>();
		methods.addAll(real.getMetaMethods());
		methods.addAll(delegate.getMetaMethods());
		return methods;
	}

	public int selectConstructorAndTransformArguments(int numberOfConstructors, Object[] arguments) {
		// ???
		return 0;
	}

	public MetaMethod pickMethod(String methodName, @SuppressWarnings("rawtypes") Class[] arguments) {
		MetaMethod method = real.pickMethod(methodName, arguments);
		if (method == null) {
			method = delegate.pickMethod(methodName, arguments);
		}
		return method;
	}
}
