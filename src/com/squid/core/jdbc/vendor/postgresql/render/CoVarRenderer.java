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

import com.squid.core.domain.IDomain;
import com.squid.core.domain.operators.ExtendedType;
import com.squid.core.domain.operators.OperatorDefinition;
import com.squid.core.sql.db.render.CoVarianceRenderer;
import com.squid.core.sql.render.OperatorPiece;
import com.squid.core.sql.render.RenderingException;
import com.squid.core.sql.render.SQLSkin;

public class CoVarRenderer extends CoVarianceRenderer {

	@Override
	public String prettyPrint(SQLSkin skin, OperatorPiece piece, OperatorDefinition opDef,
			String[] args) throws RenderingException
	{
		String result = "";
		ExtendedType[] types = piece.getParamTypes();
		//
		if (types.length == 2) {
			String[] newArgs = new String[2];
			newArgs[0] = types[0].getDomain().isInstanceOf(IDomain.TEMPORAL) ? getLocalEpoch(skin,piece.getParams()[0],types[0],args[0]) : args[0];
			newArgs[1] = types[1].getDomain().isInstanceOf(IDomain.TEMPORAL) ? getLocalEpoch(skin,piece.getParams()[1],types[1],args[1]) : args[1];

			result = opDef.prettyPrint(newArgs, false);
		}
		//
		return result;
	}

	@Override
	public String prettyPrint(SQLSkin skin, OperatorDefinition opDef,
			String[] args) throws RenderingException {
		return null;
	}
	

}
