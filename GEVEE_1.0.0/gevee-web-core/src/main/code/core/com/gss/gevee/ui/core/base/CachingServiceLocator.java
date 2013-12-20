package com.gss.gevee.ui.core.base;

//import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.gss.gevee.be.core.base.BaseLogger;

/**
 *
 */
public class CachingServiceLocator {
	
	BaseLogger logger = BaseLogger.getLogger(CachingServiceLocator.class);
    
    private InitialContext ic;
    @SuppressWarnings("unused")
	private Properties props;
    private Map<String,Object> cache;
    
    
    private CachingServiceLocator() throws NamingException, IOException  {
        ic = new InitialContext();
        
        // Cache partagée, pour la gestion des ressources (SVCO) distantes  
        cache = Collections.synchronizedMap(new HashMap<String,Object>());
    }
      
    private CachingServiceLocator(String jndiConfig) throws NamingException, IOException  {
        ic = new InitialContext();
        cache = Collections.synchronizedMap(new HashMap<String,Object>());
    } 
    
       
    
    public static CachingServiceLocator getInstance() throws ServiceLocatorException {
    	
        try{
            CachingServiceLocator v$cachingServiceLocator = new CachingServiceLocator();
            return v$cachingServiceLocator;
        }
        catch(NamingException ex){
        	ex.printStackTrace();
            throw new ServiceLocatorException("ServiceLocatorException",ex);
        }
        catch(IOException ex){
        	ex.printStackTrace();
            throw new ServiceLocatorException("ServiceLocatorException", ex);
        }       
    }
    
    
   public static CachingServiceLocator getInstance(String jndiConfig) throws ServiceLocatorException {
    	
        try{
            CachingServiceLocator v$cachingServiceLocator = new CachingServiceLocator(jndiConfig);
            return v$cachingServiceLocator;
        }
        catch(NamingException ex){
        	ex.printStackTrace();
            throw new ServiceLocatorException("NamingException",ex);
        }
        catch(IOException ex){
            throw new ServiceLocatorException("IOException", ex);
        }
        
    }    
      

    public Object lookup(String jndiName) throws ServiceLocatorException {

        try{
            Object cachedObj = cache.get(jndiName);
            if (cachedObj == null) {
                cachedObj = ic.lookup(jndiName);
                cache.put(jndiName, cachedObj);
            }
            return cachedObj;
            }
        catch(NamingException ex){
        	ex.printStackTrace();
            throw new ServiceLocatorException("NamingException");
        }


    }
    
    /**
     * Returns the ejb Local home factory. If this ejb home factory has already been
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     * @throws ServiceLocatorException 
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws NamingException, ServiceLocatorException {
        return (EJBLocalHome) lookup(jndiHomeName);
    }
    
    /**
     * Returns the ejb Remote home factory. If this ejb home factory has already been
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     * @throws ServiceLocatorException 
     */
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String jndiHomeName, Class className) throws NamingException, ServiceLocatorException {
        Object objref = lookup(jndiHomeName);
        return (EJBHome) PortableRemoteObject.narrow(objref, className); 
    }
    
    /**
     * This method helps in obtaining the topic factory
     * @return the factory for the factory to get topic connections from
     * @throws ServiceLocatorException 
     */
    public ConnectionFactory getConnectionFactory(String connFactoryName) throws NamingException, ServiceLocatorException {
        return (ConnectionFactory) lookup(connFactoryName);
    }
    
    /**
     * This method obtains the topc itself for a caller
     * @return the Topic Destination to send messages to
     * @throws ServiceLocatorException 
     */
    public Destination getDestination(String destName) throws NamingException, ServiceLocatorException {
        return (Destination) lookup(destName);
    }
    
    /**
     * This method obtains the datasource 
     * @return the DataSource corresponding to the name parameter
     * @throws ServiceLocatorException 
     */
    public DataSource getDataSource(String dataSourceName) throws NamingException, ServiceLocatorException {
        return (DataSource) lookup(dataSourceName);
    }

    /**
     * This method obtains the mail session 
     * @return the Session corresponding to the name parameter
     * @throws ServiceLocatorException 
     */
    public Session getSession(String sessionName) throws NamingException, ServiceLocatorException {
        return (Session) lookup(sessionName);
    }
    
    /**
     * This method obtains the URL
     * @return the URL value corresponding to the env entry name.
     * @throws ServiceLocatorException 
     */
    public URL getUrl(String envName) throws NamingException, ServiceLocatorException {
        return (URL) lookup(envName);
    }
    
    /**
     * This method obtains the boolean
     * @return the boolean value corresponding
     * to the env entry such as SEND_CONFIRMATION_MAIL property.
     * @throws ServiceLocatorException 
     */
    public boolean getBoolean(String envName) throws NamingException, ServiceLocatorException {
        Boolean bool = (Boolean) lookup(envName);
        return bool.booleanValue();
    }
    
    /**
     * This method obtains the String
     * @return the String value corresponding to the env entry name.
     * @throws ServiceLocatorException 
     */
    public String getString(String envName) throws NamingException, ServiceLocatorException {
        return (String) lookup(envName);
    }
}

