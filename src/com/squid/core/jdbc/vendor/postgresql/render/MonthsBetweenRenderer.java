/*******************************************************************************
 * Copyright © Squid Solutions, 2016
 *
 * This file is part of Open Bouquet software.
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation (version 3 of the License).
 *
 * There is a special FOSS exception to the terms and conditions of the 
 * licenses as they are applied to this program. See LICENSE.txt in
 * the directory of this program distribution.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Squid Solutions also offers commercial licenses with additional warranties,
 * professional functionalities or services. If you purchase a commercial
 * license, then it supersedes and replaces any other agreement between
 * you and Squid Solutions (above licenses and LICENSE.txt included).
 * See http://www.squidsolutions.com/EnterpriseBouquet/
 *******************************************************************************/
package com.squid.core.jdbc.vendor.postgresql.render;

import com.squid.core.domain.operators.OperatorDefinition;
import com.squid.core.sql.db.render.BaseOperatorRenderer;
import com.squid.core.sql.render.RenderingException;
import com.squid.core.sql.render.SQLSkin;

public class MonthsBetweenRenderer
extends BaseOperatorRenderer
{
	@Override
	public String prettyPrint(SQLSkin skin, OperatorDefinition opDef,
			String[] args) throws RenderingException {
		if (args == null || args.length!=2) {
			throw new RenderingException("invalid syntax for MONTHS_BETWEEN operator");
		}
		String date1 = args[0];
		String date2 = args[1];
		String res = "((EXTRACT(MONTH FROM ("+date1+")) - EXTRACT(MONTH FROM ("+date2+")))";
		res += " + (EXTRACT(YEAR FROM ("+date1+")) - EXTRACT(YEAR FROM ("+date2+")))*12)";
		res += " + CAST((EXTRACT(DAY FROM ("+date1+")) - EXTRACT(DAY FROM ("+date2+"))) AS FLOAT)/31";

		return res;
	}

}
