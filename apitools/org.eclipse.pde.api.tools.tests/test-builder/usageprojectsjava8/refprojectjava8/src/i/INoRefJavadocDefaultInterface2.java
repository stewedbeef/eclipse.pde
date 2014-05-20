/*******************************************************************************
 * Copyright (c) May 16, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package i;

import org.eclipse.pde.api.tools.annotations.NoReference;

public interface INoRefJavadocDefaultInterface2 {
	/**
	 * @noreference This method is not intended to be referenced by clients.
	 *
	 */
	default public void m1() {
		
	}
}
