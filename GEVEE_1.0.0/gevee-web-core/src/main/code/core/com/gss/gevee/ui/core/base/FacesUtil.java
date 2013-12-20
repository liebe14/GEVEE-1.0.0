package com.gss.gevee.ui.core.base;


import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class FacesUtil {

	
	/**
	 * Recupération d'un paramètre dans le Scope Request 
	 * 
	 * @param name	: Nom du paramètre 
	 * @return
	 */
	 public static String getRequestParameter(String name) {
	        return (String) FacesContext.getCurrentInstance().getExternalContext()
	            .getRequestParameterMap().get(name);
	    }

	 
	 /**
	  * Recuperation d'un objet dans l'entete de la requete 
	  * 
	  * @param key	: Clé identifiant l'objet à recuperer
	  * @return
	  */
	  public static Object getHeaderMapValue(String key) {
	        return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get(key);
	    }	 
	 

	  /**
	  * Recupération du Scope Session 
	  * 
	  * @param key	: Nom de la clé 
	  * @return
	  */
	 public static Map<String,Object> getSessionMap() {
	        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	    }	  
	  
	 /**
	  * Recupération d'un objet  dans le scope Session 
	  * 
	  * @param key	: Nom de la clé 
	  * @return
	  */
	 public static Object getSessionMapValue(String key) {
	        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	    }
	   
	  
	 /**
	  * Injection d'une objet dans le scope Session
	  * 
	  * @param key	: Clé identifiant  l'objet 
	  * @param value	: Valeur 
	  */
	 public static void setSessionMapValue(String key, Object value) {
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	   }
	 
	 
	 /**
	  * Recupération d'un objet  dans le scope Session 
	  * 
	  * @param key	: Nom de la clé 
	  * @return
	  */
	 public static void removeSessionMapValue(String key) {
	        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	    }	 
	 
	 /**
	  * Recupération d'un objet dans le scope Application 
	  * 
	  * @param key	: Clé identifiant l'objet 
	  * @return
	  */
	  public static Object getApplicationMapValue(String key) {
	        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
	    }
	  
	 /**
	  * Injection d'une objet dans le scope Application 
	  * 
	  * @param key	: Clé identifiant  l'objet 
	  * @param value	: Valeur 
	  */
	 public static void setApplicationMapValue(String key, Object value) {
	        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
	    }
	 	  
	 
	/**
	 * Recupération d'un paramètre dans un objet évènement 
	 * 
	 * @param event	: Evènement 
	 * @param name	: Nom du paramètre
	 * @return
	 */
	  public static String getActionAttribute(ActionEvent event, String name) {
	        return (String) event.getComponent().getAttributes().get(name);
	    }
	  
	  
	  
	 
	 /***
	  * Ajout d'un message applicatif 
	  * 
	  * 
	  * @param id		: Id du composant graphique affichant le message 
	  * @param summary	:	Entete du méssage 
	  * @param detail	:	Details du message 
	  * @param severity	: 	Sévérité du méssage
	  */
	public static void addMessage(String id,String summary,String detail,FacesMessage.Severity severity){
        
		FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(severity);
        facesMessage.setSummary(summary);
        facesMessage.setDetail(detail);
        facesContext.addMessage(id, facesMessage);
	}	
	
	/**
	 * Recherche une instance d'un bean managé dans le scope session 
	 * Si ce dernier n'existe pas encore, il sera créé
	 * 
	 * @param name
	 * @return
	 */
	public static Object lookupSessionManagedBeanInstance(String name){
		
		return null;
	}
	
	/***
	 * Recherche du nom ou de la clé d'une instance d'une bean managé 
	 * 
	 * @param bean	: Nom du bean 
	 * @return
	 */
	public static String lookupManagedBeanName(Object bean) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        // Get requestmap.
        Map<String, Object> requestMap = externalContext.getRequestMap();
    
        // Lookup the current bean instance in the request scope.
        for (String key : requestMap.keySet()) {
            if (bean.equals(requestMap.get(key))) {
                // The key is the managed bean name.
                return key;
            }
        }
    
        // Bean is not in the request scope. Get the sessionmap then.
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        // Lookup the current bean instance in the session scope.
        for (String key : sessionMap.keySet()) {
            if (bean.equals(sessionMap.get(key))) {
                // The key is the managed bean name.
                return key;
            }
        }

        // Bean is also not in the session scope. Get the applicationmap then.
        Map<String, Object> applicationMap = externalContext.getApplicationMap();

        // Lookup the current bean instance in the application scope.
        for (String key : applicationMap.keySet()) {
            if (bean.equals(applicationMap.get(key))) {
                // The key is the managed bean name.
                return key;
            }
        }

        // Bean is also not in the application scope.
        // Is the bean's instance actually a managed bean instance then? =)
        return null;
    }

	
	
	/**
	 * Message d'Informations
	 * 
	 * @param summary
	 * @param detail
	 */
	public static void addInfoMessage(String summary,String detail){
        
        addMessage("msgs_barreMessage", summary, detail, FacesMessage.SEVERITY_INFO);
	}	
	
	/**
	 * Message d'avertissement (Warning)
	 * 
	 * @param summary
	 * @param detail
	 */
	public static void addWarnMessage(String summary,String detail){
        
        addMessage("msgs_barreMessage", summary, detail, FacesMessage.SEVERITY_WARN);
	}
	
	/***
	 * Message d'Erreur
	 * 
	 * @param summary
	 * @param detail
	 */
	public static void addErrorMessage(String summary,String detail){
        
        addMessage("msgs_barreMessage", summary, detail, FacesMessage.SEVERITY_ERROR);
	}	

	/**
	 * Message d'Erreur Fatale
	 * 
	 * @param id
	 * @param summary
	 * @param detail
	 */
	public static void addFatalMessage(String summary,String detail){
        
        addMessage("msgs_barreMessage", summary, detail, FacesMessage.SEVERITY_FATAL);
	}
		
}
