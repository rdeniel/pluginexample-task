/*
 * Copyright (c) 2002-2020, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.example.business;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import fr.paris.lutece.plugins.workflowcore.business.config.TaskConfig;

import java.io.Serializable;

/**
 * This is the business class for the object Workflowconfig
 */ 
public class WorkflowConfig extends TaskConfig
{

    // Variables declarations 
    private int _nId;
    
    @Size( max = 255 , message = "#i18n{module.workflow.example.validation.workflowconfig.DefaultMessage.size}" ) 
    private String _strDefaultMessage;

	private boolean _bMandatory;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */ 
    public void setId( int nId )
    {
        _nId = nId;
    }
    
    /**
     * Returns the DefaultMessage
     * @return The DefaultMessage
     */
    public String getDefaultMessage( )
    {
        return _strDefaultMessage;
    }

    /**
     * Sets the DefaultMessage
     * @param strDefaultMessage The DefaultMessage
     */ 
    public void setDefaultMessage( String strDefaultMessage )
    {
        _strDefaultMessage = strDefaultMessage;
    }
    /**
     * @return true if the comment is mandatory, false otherwise
     */
    public boolean isMandatory( )
    {
        return _bMandatory;
    }

    /**
     * @param bMandatory
     *            true if the comment is mandatory, false otherwise
     */
    public void setMandatory( boolean bMandatory )
    {
        _bMandatory = bMandatory;
    }
}
