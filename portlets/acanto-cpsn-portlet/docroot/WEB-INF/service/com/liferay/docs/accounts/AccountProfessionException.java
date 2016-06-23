/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.docs.accounts;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author a616659
 */
public class AccountProfessionException extends PortalException {

	public AccountProfessionException() {
		super();
	}

	public AccountProfessionException(String msg) {
		super(msg);
	}

	public AccountProfessionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AccountProfessionException(Throwable cause) {
		super(cause);
	}

}