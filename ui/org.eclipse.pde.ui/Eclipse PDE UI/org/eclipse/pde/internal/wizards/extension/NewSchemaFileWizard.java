package org.eclipse.pde.internal.wizards.extension;

import org.eclipse.core.resources.*;
import org.eclipse.jface.wizard.*;
import org.eclipse.ui.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.internal.*;

public class NewSchemaFileWizard extends Wizard implements INewWizard {
	private NewSchemaFileMainPage mainPage;
	private IContainer container;

public NewSchemaFileWizard() {
	setDefaultPageImageDescriptor(PDEPluginImages.DESC_NEWEXP_WIZ);
	setNeedsProgressMonitor(true);
}
public void addPages() {
	mainPage = new NewSchemaFileMainPage(container);
	addPage(mainPage);
}
public void init(IWorkbench workbench, IStructuredSelection selection) {
	Object sel = selection.getFirstElement();
	if (sel instanceof IContainer)
		container = (IContainer) sel;
}
public boolean performFinish() {
	return mainPage.finish();
}
}
