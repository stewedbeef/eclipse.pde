package org.eclipse.pde.internal.schema;

import org.eclipse.pde.internal.base.schema.*;

public abstract class RepeatableSchemaObject extends SchemaObject implements ISchemaRepeatable {
	public static final String P_MIN_OCCURS="min_occurs";
	public static final String P_MAX_OCCURS="max_occurs";
	private int minOccurs = 1;
	private int maxOccurs = 1;

public RepeatableSchemaObject(ISchemaObject parent, String name) {
	super(parent, name);
}
public int getMaxOccurs() {
	return maxOccurs;
}
public int getMinOccurs() {
	return minOccurs;
}
public boolean isRequired() {
	return minOccurs >0;
}
public boolean isUnbounded() {
	return maxOccurs ==Integer.MAX_VALUE;
}
public void setMaxOccurs(int newMaxOccurs) {
	maxOccurs = newMaxOccurs;
	getSchema().fireModelObjectChanged(this, P_MAX_OCCURS);
}
public void setMinOccurs(int newMinOccurs) {
	minOccurs = newMinOccurs;
	getSchema().fireModelObjectChanged(this, P_MIN_OCCURS);
}
}
