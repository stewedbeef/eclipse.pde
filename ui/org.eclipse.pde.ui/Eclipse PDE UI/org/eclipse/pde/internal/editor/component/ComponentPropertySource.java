package org.eclipse.pde.internal.editor.component;

import org.eclipse.core.runtime.*;
import java.net.*;
import org.eclipse.ui.*;
import java.util.*;
import org.eclipse.ui.views.properties.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.pde.internal.*;
import org.eclipse.pde.internal.editor.*;
import org.eclipse.pde.internal.base.model.component.*;

public abstract class ComponentPropertySource implements IPropertySource {
	protected IComponentObject object;

public ComponentPropertySource(IComponentObject object) {
	this.object = object;
}
protected PropertyDescriptor createTextPropertyDescriptor(String name, String displayName) {
	if (isEditable()) return new ModifiedTextPropertyDescriptor(name, displayName);
	else return new PropertyDescriptor(name, displayName);
}
public Object getEditableValue() {
	return null;
}
public boolean isEditable() {
	return object.getModel().isEditable();
}
public boolean isPropertySet(Object property) {
	return false;
}
public void resetPropertyValue(Object property) {
}
protected IPropertyDescriptor[] toDescriptorArray(Vector result) {
	IPropertyDescriptor [] array = new IPropertyDescriptor[result.size()];
	result.copyInto(array);
	return array;
}
}
