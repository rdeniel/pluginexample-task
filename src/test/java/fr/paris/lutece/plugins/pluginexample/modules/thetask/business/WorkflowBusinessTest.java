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

import fr.paris.lutece.plugins.workflow.modules.example.business.Workflow;
import fr.paris.lutece.test.LuteceTestCase;


/**
 * This is the business class test for the object Workflow
 */
public class WorkflowBusinessTest extends LuteceTestCase
{
    private static final String MESSAGE1 = "Message1";
    private static final String MESSAGE2 = "Message2";

	/**
	* test Workflow
	*/
    public void testBusiness(  )
    {
        // Initialize an object
        Workflow workflow = new Workflow();
        workflow.setMessage( MESSAGE1 );

        // Create test
        WorkflowHome.create( workflow );
        Workflow workflowStored = WorkflowHome.findByPrimaryKey( workflow.getId( ) );
        assertEquals( workflowStored.getMessage() , workflow.getMessage( ) );

        // Update test
        workflow.setMessage( MESSAGE2 );
        WorkflowHome.update( workflow );
        workflowStored = WorkflowHome.findByPrimaryKey( workflow.getId( ) );
        assertEquals( workflowStored.getMessage() , workflow.getMessage( ) );

        // List test
        WorkflowHome.getWorkflowsList( );

        // Delete test
        WorkflowHome.remove( workflow.getId( ) );
        workflowStored = WorkflowHome.findByPrimaryKey( workflow.getId( ) );
        assertNull( workflowStored );
        
    }
    
    
     

}