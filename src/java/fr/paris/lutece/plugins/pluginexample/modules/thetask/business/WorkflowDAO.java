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

package fr.paris.lutece.plugins.pluginexample.modules.thetask.business;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.sql.DAOUtil;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides Data Access methods for Workflow objects
 */
public final class WorkflowDAO implements IWorkflowDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_workflow, message FROM pluginexamplethetask_workflow WHERE id_workflow = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO pluginexamplethetask_workflow ( message ) VALUES ( ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM pluginexamplethetask_workflow WHERE id_workflow = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE pluginexamplethetask_workflow SET id_workflow = ?, message = ? WHERE id_workflow = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_workflow, message FROM pluginexamplethetask_workflow";
    private static final String SQL_QUERY_SELECTALL_ID = "SELECT id_workflow FROM pluginexamplethetask_workflow";
    private static final String SQL_QUERY_DELETE_BY_TASK = " DELETE FROM pluginexamplethetask_workflow WHERE id_task = ? ";
    private static final String SQL_QUERY_SELECT_BY_ID_TASK = " SELECT id_task, message FROM pluginexamplethetask_workflow WHERE id_task = ? ";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Workflow workflow, Plugin plugin )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS, plugin ) )
        {
            int nIndex = 1;
            daoUtil.setString( nIndex++ , workflow.getMessage( ) );
            
            daoUtil.executeUpdate( );
            if ( daoUtil.nextGeneratedKey( ) ) 
            {
                workflow.setId( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Workflow load( int nKey, Plugin plugin )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin ) )
        {
	        daoUtil.setInt( 1 , nKey );
	        daoUtil.executeQuery( );
	        Workflow workflow = null;
	
	        if ( daoUtil.next( ) )
	        {
	            workflow = new Workflow();
	            int nIndex = 1;
	            
	            workflow.setId( daoUtil.getInt( nIndex++ ) );
	            workflow.setMessage( daoUtil.getString( nIndex ) );            
	        }
	
	        daoUtil.free( );
	        return workflow;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin ) )
        {
	        daoUtil.setInt( 1 , nKey );
	        daoUtil.executeUpdate( );
	        daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Workflow workflow, Plugin plugin )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin ) )
        {
	        int nIndex = 1;
	        
	        daoUtil.setInt( nIndex++ , workflow.getId( ) );
	        daoUtil.setString( nIndex++ , workflow.getMessage( ) );
	        daoUtil.setInt( nIndex , workflow.getId( ) );
	
	        daoUtil.executeUpdate( );
	        daoUtil.free( );
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Workflow> selectWorkflowsList( Plugin plugin )
    {
        List<Workflow> workflowList = new ArrayList<>(  );
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            Workflow workflow = new Workflow(  );
	            int nIndex = 1;
	            
	            workflow.setId( daoUtil.getInt( nIndex++ ) );
	            workflow.setMessage( daoUtil.getString( nIndex ) );            
	
	            workflowList.add( workflow );
	        }
	
	        daoUtil.free( );
	        return workflowList;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectIdWorkflowsList( Plugin plugin )
    {
        List<Integer> workflowList = new ArrayList<>( );
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            workflowList.add( daoUtil.getInt( 1 ) );
	        }
	
	        daoUtil.free( );
	        return workflowList;
        }
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public ReferenceList selectWorkflowsReferenceList( Plugin plugin )
    {
        ReferenceList workflowList = new ReferenceList();
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin ) )
        {
	        daoUtil.executeQuery(  );
	
	        while ( daoUtil.next(  ) )
	        {
	            workflowList.addItem( daoUtil.getInt( 1 ) , daoUtil.getString( 2 ) );
	        }
	
	        daoUtil.free( );
	        return workflowList;
    	}
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByIdTask( int nIdTask, Plugin plugin )
    {
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_BY_TASK, plugin ) )
        	{
		        daoUtil.setInt( 1, nIdTask );
		        daoUtil.executeUpdate( );
		        daoUtil.free( );
        	}
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Workflow> loadByIdTask( int nIdTask, Plugin plugin )
    {
        List<Workflow> listWorkflows = new ArrayList<>( );
        try( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_ID_TASK, plugin ) )
        {
	        daoUtil.setInt( 1, nIdTask );
	
	        daoUtil.executeQuery( );
	
	        while ( daoUtil.next( ) )
	        {
	            int nIndex = 1;
	
	            Workflow workflow = new Workflow( );
	            workflow.setId( daoUtil.getInt( nIndex++ ) );
	            workflow.setMessage( daoUtil.getString( nIndex++ ) );
	            listWorkflows.add( workflow );
	        }
	
	        daoUtil.free( );
	
	        return listWorkflows;
        }
    }
}
