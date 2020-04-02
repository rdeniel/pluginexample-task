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
 *"
 * License 1.0
 */

package fr.paris.lutece.plugins.pluginexample.modules.thetask.business;

import fr.paris.lutece.test.LuteceTestCase;


/**
 * This is the business class test for the object Workflowconfig
 */
public class WorkflowconfigBusinessTest extends LuteceTestCase
{
    private static final String DEFAULTMESSAGE1 = "DefaultMessage1";
    private static final String DEFAULTMESSAGE2 = "DefaultMessage2";

	/**
	* test Workflowconfig
	*/
    public void testBusiness(  )
    {
        // Initialize an object
        Workflowconfig workflowconfig = new Workflowconfig();
        workflowconfig.setDefaultMessage( DEFAULTMESSAGE1 );

        // Create test
        WorkflowconfigHome.create( workflowconfig );
        Workflowconfig workflowconfigStored = WorkflowconfigHome.findByPrimaryKey( workflowconfig.getId( ) );
        assertEquals( workflowconfigStored.getDefaultMessage() , workflowconfig.getDefaultMessage( ) );

        // Update test
        workflowconfig.setDefaultMessage( DEFAULTMESSAGE2 );
        WorkflowconfigHome.update( workflowconfig );
        workflowconfigStored = WorkflowconfigHome.findByPrimaryKey( workflowconfig.getId( ) );
        assertEquals( workflowconfigStored.getDefaultMessage() , workflowconfig.getDefaultMessage( ) );

        // List test
        WorkflowconfigHome.getWorkflowconfigsList( );

        // Delete test
        WorkflowconfigHome.remove( workflowconfig.getId( ) );
        workflowconfigStored = WorkflowconfigHome.findByPrimaryKey( workflowconfig.getId( ) );
        assertNull( workflowconfigStored );
        
    }
    
    
     

}