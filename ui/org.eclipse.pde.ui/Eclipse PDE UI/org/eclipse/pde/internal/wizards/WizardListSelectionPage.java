package org.eclipse.pde.internal.wizards;

import org.eclipse.jface.wizard.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.pde.internal.elements.*;
import org.eclipse.jface.action.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import java.util.*;
import org.eclipse.swt.custom.*;


public abstract class WizardListSelectionPage
	extends BaseWizardSelectionPage
	implements ISelectionChangedListener {
	protected TableViewer wizardSelectionViewer;
	private ElementList wizardElements;
	private WizardSelectedAction doubleClickAction = new WizardSelectedAction();

	private class WizardSelectedAction extends Action {
		public WizardSelectedAction() {
			super("wizardSelection");
		}

		public void run() {
			selectionChanged(
				new SelectionChangedEvent(
					wizardSelectionViewer,
					wizardSelectionViewer.getSelection()));
			advanceToNextPage();
		}
	}

public WizardListSelectionPage(ElementList wizardElements, String message) {
	super("ListSelection", message);
	this.wizardElements = wizardElements;
}
public void advanceToNextPage() {
	getContainer().showPage(getNextPage());
}
public void createControl(Composite parent) {
	// create composite for page.
	Composite outerContainer = new Composite(parent, SWT.NONE);
	outerContainer.setLayout(new GridLayout());
	outerContainer.setLayoutData(
		new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL)); 

	new Label(outerContainer, SWT.NONE).setText(getLabel());

	// list view.

	wizardSelectionViewer = new TableViewer(createTable(outerContainer, SWT.BORDER));
	wizardSelectionViewer.setContentProvider(new ListContentProvider()); 
	wizardSelectionViewer.setLabelProvider(ListUtil.TABLE_LABEL_PROVIDER);
	wizardSelectionViewer.setSorter(ListUtil.NAME_SORTER);
	wizardSelectionViewer.addDoubleClickListener(new IDoubleClickListener() {
		public void doubleClick(DoubleClickEvent event) {
			doubleClickAction.run();
		}
	});
	wizardSelectionViewer.addSelectionChangedListener(this);

	// list view pane.  Add a border to the pane.
	wizardSelectionViewer.getControl().setLayoutData(
		new GridData(
			GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL
				| GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL)); 

	createDescriptionIn(outerContainer);
	wizardSelectionViewer.setInput(wizardElements);
	wizardSelectionViewer.getTable().setFocus();

	setControl(outerContainer);
}
private Table createTable(Composite parent, int style) {
	Table table = new Table(parent, style);
	new TableColumn(table, SWT.NONE);
	TableLayout layout = new TableLayout();
	layout.addColumnData(new ColumnWeightData(100));
	table.setLayout(layout);
	return table;
}
public void selectionChanged(SelectionChangedEvent event) {
	setErrorMessage(null);
	IStructuredSelection selection = (IStructuredSelection) event.getSelection();
	WizardElement currentWizardSelection = null;
	Iterator iter = selection.iterator();

	if (iter.hasNext())
		currentWizardSelection = (WizardElement) iter.next();
	if (currentWizardSelection == null) {
		setDescriptionText("");
		setSelectedNode(null);
		return;
	}
	final WizardElement finalSelection = currentWizardSelection;
/*
	BusyIndicator.showWhile(wizardSelectionViewer.getControl().getDisplay(), new Runnable() {
		public void run() {
*/
			setSelectedNode(createWizardNode(finalSelection));
			setDescriptionText((String) finalSelection.getDescription());
/*
		}
	});
*/
}
public void setSelectedNode(IWizardNode node) {
	super.setSelectedNode(node);
}
}
