package org.eclipse.pde.internal.preferences;

import org.eclipse.core.runtime.*;
import java.net.URL;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.jface.preference.*;
import org.eclipse.ui.*;
import org.eclipse.pde.internal.*;
import org.eclipse.swt.*;
import org.eclipse.core.boot.BootLoader;
import org.eclipse.jdt.core.*;

/**
 * Insert the type's description here.
 * Creation date: (12/1/2000 9:35:46 AM)
 * @author: Dejan Glozic
 */
public class PDEBasePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String PROP_PLATFORM_PATH="org.eclipse.pde.platformPath";
	public static final String PROP_PLATFORM_LOCATION="org.eclipse.pde.platformLocation";
	public static final String KEY_PLATFORM_HOME ="Preferences.MainPage.PlatformHome";
	public static final String KEY_ARGUMENTS ="Preferences.MainPage.Arguments";
	public static final String KEY_DESCRIPTION ="Preferences.MainPage.Description";
	public static final String KEY_LOCATION ="Preferences.MainPage.Location";
	public static final String PROP_PLATFORM_ARGS="org.eclipse.pde.platformArgs";
	private DirectoryFieldEditor editor;
/**
 * MainPreferencePage constructor comment.
 */
public PDEBasePreferencePage() {
	super(GRID);
	setPreferenceStore(PDEPlugin.getDefault().getPreferenceStore());
	setDescription(PDEPlugin.getResourceString(KEY_DESCRIPTION));
}
/**
 * Insert the method's description here.
 * Creation date: (12/1/2000 9:41:47 AM)
 */
protected void createFieldEditors() {
	editor =
		new DirectoryFieldEditor(
			PROP_PLATFORM_PATH,
			PDEPlugin.getResourceString(KEY_PLATFORM_HOME),
			getFieldEditorParent());
	DirectoryFieldEditor platform =
		new DirectoryFieldEditor(
			PROP_PLATFORM_LOCATION,
			PDEPlugin.getResourceString(KEY_LOCATION),
			getFieldEditorParent());
	StringFieldEditor args =
		new StringFieldEditor(
			PROP_PLATFORM_ARGS,
			PDEPlugin.getResourceString(KEY_ARGUMENTS),
			getFieldEditorParent());

	ExternalPluginsEditor plugins = new ExternalPluginsEditor(getFieldEditorParent());
	addField(editor);
	addField(platform);
	addField(args);
	addField(plugins);
}
/**
 * Insert the method's description here.
 * Creation date: (3/26/2001 4:36:34 PM)
 * @return java.lang.String
 */
public String getPlatformPath() {
	String value=editor.getStringValue();
	if (value!=null) value.trim();
	return value;
}
/**
 * Initializes this preference page using the passed desktop.
 *
 * @param desktop the current desktop
 */
public void init(IWorkbench workbench) {
}
/**
 * Insert the method's description here.
 * Creation date: (5/6/2001 6:45:47 PM)
 */
public static void initializePlatformPath() {
	IPreferenceStore store = PDEPlugin.getDefault().getPreferenceStore();
	String path = store.getString(PROP_PLATFORM_PATH);
	if (path == null || path.length() == 0) {
		URL installURL = BootLoader.getInstallURL();
		String file = installURL.getFile();
		//IPath ppath = new Path(file).removeTrailingSeparator().removeLastSegments(1);
		//path = ppath.toOSString();
		//if (path.charAt(0)=='/') path = path.substring(1);
		IPath ppath = new Path(file);
		path = ppath.toOSString();
		store.setDefault(PROP_PLATFORM_PATH, path);
		store.setValue(PROP_PLATFORM_PATH, path);
	}
}
/** 
 *
 */
public boolean performOk() {
	IPreferenceStore store = getPreferenceStore();
	String oldEclipseHome = store.getString(PROP_PLATFORM_PATH);
	String newEclipseHome = editor.getStringValue();
	if (!oldEclipseHome.equals(newEclipseHome)) {
		// home changed -update Java variable
		try {
			JavaCore.setClasspathVariable(
				PDEPlugin.ECLIPSE_HOME_VARIABLE,
				new Path(newEclipseHome));
		} catch (JavaModelException e) {
			PDEPlugin.logException(e);
		}
	}
	return super.performOk();
}
}
