package org.eclipse.pde.internal.base.model.plugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.base.schema.*;
/**
 * An attribute of XML elements found in the plug-in.
 */
public interface IPluginAttribute extends IPluginObject {
/**
 * This property will be used to notify that the value
 * of the attribute has changed.
 */
	public static final String P_VALUE = "value";
/**
 * Returns a schema attribute definition that
 * matches this attribute instance.
 *
 * @return schema attribute definition, or <samp>null</samp> if
 * schema not found or if attribute is not defined in the schema.
 */
public ISchemaAttribute getAttributeInfo();
/**
 * Returns the value of this attribute.
 *
 * @return the string value of the attribute
 */
String getValue();
/**
 * Sets the value of this attribute.
 * This method will throw a CoreExeption
 * if the model is not editable.
 *
 * @param value the new attribute value
 */
void setValue(String value) throws CoreException;
}
