package org.eclipse.pde.internal.editor.schema;

import org.eclipse.pde.internal.base.schema.*;
import org.eclipse.swt.widgets.*;

public interface IRestrictionPage {
	public Control createControl(Composite parent);
	public Class getCompatibleRestrictionClass();
	public Control getControl();
	public ISchemaRestriction getRestriction();
	public void initialize(ISchemaRestriction restriction);
}
