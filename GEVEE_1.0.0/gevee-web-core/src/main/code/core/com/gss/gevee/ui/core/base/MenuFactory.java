package com.gss.gevee.ui.core.base;


import java.util.ArrayList;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import org.ajax4jsf.component.html.HtmlActionParameter;
import org.ajax4jsf.component.html.HtmlAjaxCommandButton;
import org.ajax4jsf.component.html.HtmlAjaxFunction;
import org.richfaces.component.html.ContextMenu;
import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlHotKey;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlMenuSeparator;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.component.html.HtmlToolBarGroup;

/**
 * 
 * Générateur de Menus :  Implémation assez basique
 * 
 * TODO A Optimiser 
 * 
 * @author lkamhoua
 *
 */
@SuppressWarnings("deprecation")
public class MenuFactory {
	
	
	String confirmDialog;
	
	String hidden;
		
	String pageContexte;
	
	String nomControleur;
	
	int index = -1;
	
	boolean enModification = false;
	
	boolean disabled = false;
	
	List<Traitement> trtStandards = new ArrayList<Traitement>();
	List<Traitement> trtSpecifiques = new ArrayList<Traitement>();
	List<Traitement> trtNavigation = new ArrayList<Traitement>();
	
	FacesContext facesContext =  FacesContext.getCurrentInstance();
	Application application = facesContext.getApplication();
	
	
	public MenuFactory() {

	}	
		
	public MenuFactory(String hidden, String nomControleur, List<Traitement> listeTraitements ) {

		this.hidden = hidden;
		this.nomControleur = nomControleur;
		trierTraitement(listeTraitements);	
	}
	
	public MenuFactory(String hidden, String nomControleur, List<Traitement> listeTraitements, boolean enModification) {
		
		this.hidden = hidden;
		this.nomControleur = nomControleur;
		this.enModification = enModification;
		trierTraitement(listeTraitements);	
	}
	

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	
	/**
	 * @return the pageContexte
	 */
	public String getPageContexte() {
		
		if(pageContexte == null){			
			if(getHidden().endsWith("Details"))
				pageContexte =  "DETAILS";			
			else if(getHidden().endsWith("Liste"))
				pageContexte = "LISTE";
			else if(getHidden().endsWith("Edition")){
				pageContexte = "EDITION";		
				setDisabled(true);
			}	
		}
		return pageContexte;
	}


	/**
	 * @param pageContexte the pageContexte to set
	 */
	public void setPageContexte(String pageContexte) {
		this.pageContexte = pageContexte;
	}
	
	/**
	 * @return the hidden
	 */
	public String getHidden() {
		return hidden;
	}


	/**
	 * @return the nomControleur
	 */
	public String getNomControleur() {
		return nomControleur;
	}


	/**
	 * @param nomControleur the nomControleur to set
	 */
	public void setNomControleur(String nomControleur) {
		this.nomControleur = nomControleur;
	}
	
	public String getConfirmDialog(Traitement t) {
		
		if(Traitement.MODAL_NO.equals(t.getModalType())){
			confirmDialog = "";
		}
		else if(Traitement.MODAL_SIMPLE.equals(t.getModalType())){
			//confirmDialog = "if(! confirm('Etês vous certain de vouloir réaliser cette action ?'','Confirmation')) return false;";
			confirmDialog = "if(! confirm('"+ t.getModalMsg()+ "','Confirmation')) return false;";

		}
		
		else if( Traitement.MODAL_MOTIF.equals(t.getModalType()) ||
				Traitement.MODAL_FILE.equals(t.getModalType())  ||
				Traitement.MODAL_SPECIAL.equals(t.getModalType())   ){
			confirmDialog = "javascript:Richfaces.showModalPanel('" + t.getModalPanel() + "')";
		}
		
/*		else if(t.getModalType().equals(Traitement.MODAL_MOTIF)){
			confirmDialog = "";
		}		
		
		else if(t.getModalType().equals(Traitement.MODAL_FILE)){
			confirmDialog = "javascript:Richfaces.showModalPanel('mpnl_file')";
		}	
		
		else if(t.getModalType().equals(Traitement.MODAL_SPECIAL)){
			confirmDialog = "javascript:Richfaces.showModalPanel('" + t.getModalPanel() + "')";
		}	*/		
		
		return confirmDialog;
	}

	
	/**
	 * @return the index
	 */
	public int getIndex() {
		
		if(index == -1){		
			if(getPageContexte().equals("LISTE")) index = 0;
			else if(getPageContexte().equals("DETAILS")) index = 2;
			else if(getPageContexte().equals("EDITION")) index = 3;
		}
		
		return index;
	}

	
	/**
	 * @return the enModification
	 */
	public boolean isEnModification() {
		return enModification;
	}


	/**
	 * @return the param
	 */
	public HtmlActionParameter getParam() {
		
		HtmlActionParameter v$param = new HtmlActionParameter();
		v$param.setName("param1");
		v$param.setValue(getPageContexte());
		v$param.setAssignToBinding(application.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{"+ getNomControleur() +".defaultVue.navigationMgr.pageContexte}",String.class));
		
		return v$param;
	}
	
	public HtmlActionParameter getParam(String p$actionSpecifique) {
				
		HtmlActionParameter v$param = new HtmlActionParameter();
		v$param.setName("param2");
		v$param.setValue(p$actionSpecifique);
		v$param.setAssignToBinding(application.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{"+ getNomControleur() +".actionSpecifique}",String.class));
			
		return v$param;
	}
	
	public HtmlActionParameter getParam(String p$value, String p$target) {
		
		HtmlActionParameter v$param = new HtmlActionParameter();		
		v$param = new HtmlActionParameter();
		v$param.setName("param3");
		v$param.setValue(p$value);
		v$param.setAssignToBinding(application.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), p$target,String.class));
	
		return v$param;
	}	

	public HtmlActionParameter getParam2(String p$value, String p$target) {
		
		HtmlActionParameter v$param = new HtmlActionParameter();		
		v$param = new HtmlActionParameter();
		v$param.setName("param4");
		v$param.setValue(p$value);
		v$param.setAssignToBinding(application.getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), p$target,String.class));
	
		return v$param;
	}		


	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}


	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public void trierTraitement(List<Traitement> listeTraitements){
		
		for( Traitement t: listeTraitements){
			
			if(t.getType().equals(Traitement.STANDARD))
				trtStandards.add(t);
			else if(t.getType().equals(Traitement.SPECIFIQUE))
				trtSpecifiques.add(t);
			else if(t.getType().equals(Traitement.NAVIGATION))
				trtNavigation.add(t);
		}
	}
	
	public HtmlToolBar getMenu(){
		
		HtmlToolBar v$toolBar = null;
		HtmlMenuItem v$menuItem;
		HtmlHotKey v$hotKey;
		HtmlAjaxFunction v$fonction;
		MethodBinding v$method;
								
		// Barre d'outils pour les actions de base et spécifiques 
		v$toolBar = new HtmlToolBar();
		
		// ToolBarGroup pour les actions 
		HtmlToolBarGroup v$toolBarGroup = new HtmlToolBarGroup();
		
		// ToolBarGroup pour les notes rapides
		HtmlToolBarGroup v$toolBarGroup2 = new HtmlToolBarGroup();
		v$toolBarGroup2.setLocation("right");
		
		//ToolBarGroup pour les hotKeys (raccourcis claviers)
		HtmlToolBarGroup v$toolBarGroupHotKey = new HtmlToolBarGroup();
		v$toolBarGroupHotKey.setStyle("display:none");
		
		// Menu pour les actions de base 
		HtmlDropDownMenu v$menuBase = new HtmlDropDownMenu();
		///v$menuBase.setValue("Actions de Base");
		v$menuBase.setValue("Actions de Base");
		v$menuBase.setDisabled(disabled);
		v$menuBase.setDirection("bottom-right");
		v$menuBase.setJointPoint("bl");
		v$menuBase.setSubmitMode("ajax");
		v$menuBase.setOnexpand("updateMenu();");
		
		// Menu pour les actions Sécifiques 
		HtmlDropDownMenu v$menuSpe = new HtmlDropDownMenu();
		///v$menuSpe.setValue("Actions Spécifiques");
		v$menuSpe.setValue("Actions Spécifiques");
		v$menuSpe.setDisabled(disabled);
		v$menuSpe.setDirection("bottom-right");
		v$menuSpe.setJointPoint("bl");
		v$menuSpe.setSubmitMode("ajax");
		v$menuSpe.setOnexpand("updateMenu();");
		
		// Menu pour l'aide 
		HtmlDropDownMenu v$menuAide = new HtmlDropDownMenu();
		v$menuAide.setValue("Menu pour l'aide");
		
		// Menu pour les Notes 
		HtmlDropDownMenu v$menuNote = new HtmlDropDownMenu();
		v$menuNote.setValue("Menu pour les Notes");
			
		// Les actions de bases et spécifiques ne sont générées que si nous sommes en Liste ou en Details
		if(getPageContexte().equals("LISTE") || getPageContexte().equals("DETAILS") ){
			
			// Actions de Base 			
			for(Traitement t: trtStandards){
				
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				v$hotKey = new HtmlHotKey();
				v$fonction = new HtmlAjaxFunction();
				
			// ID de l'action
				v$menuItem.setId("menu-"+t.getCode());
				v$hotKey.setId("hotKey-"+t.getCode());
				v$fonction.setId("fonction-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				v$hotKey.setKey(t.getClavier());
				v$fonction.setName(t.getMethode());	
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				v$fonction.getChildren().add(getParam());
				v$fonction.addActionListener(getParam());
				
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
								
				String v$codeTrt = t.getCode();				
				v$hotKey.setHandler("updateMenu();if(activateHotKey['"+v$codeTrt+"'])"+t.getMethode()+"();return false;");		
				v$fonction.setAction(v$method);
				
			// Activation ou désactivation du bouton selon le contexte de page
				//v$menuItem.setDisabled(! t.getConfigIHM()[getIndex()]);
				v$menuItem.setRendered(t.getConfigIHM()[getIndex()]);
				v$fonction.setRendered(t.getConfigIHM()[getIndex()]);
				
			// Spécification du type de modal panel
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));		
					v$fonction.setOncomplete(getConfirmDialog(t));
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
					v$hotKey.setHandler("updateMenu();if(activateHotKey['"+v$codeTrt+"']){"+getConfirmDialog(t)+""+t.getMethode()+"();}return false;");
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
				v$fonction.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());		
				v$fonction.setReRender(t.getReRender());
					
			// Ajout de l'action au menu 
				v$menuBase.getChildren().add(v$menuItem);
				v$toolBarGroupHotKey.getChildren().add(v$hotKey);
				v$toolBarGroupHotKey.getChildren().add(v$fonction);
				//v$toolBar.getChildren().add(v$toolBarGroupHotKey);
			
			}
			
			// Actions Spécifiques			
			for(Traitement t: trtSpecifiques){	
								
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				v$hotKey = new HtmlHotKey();
				v$fonction = new HtmlAjaxFunction();
				
			// ID de l'action
				v$menuItem.setId("menu-"+t.getCode());
				v$hotKey.setId("hotKey-"+t.getCode());
				v$fonction.setId("fonction-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				v$hotKey.setKey(t.getClavier());
				// Les actions spécifiques utilisant une fonction de même nom, seront différentiées au niveau de la fonction js par un suffixe
				v$fonction.setName(t.getMethode()+"_"+t.getCode().replace('-', '_'));
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
				v$fonction.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				v$fonction.getChildren().add(getParam());
				v$fonction.addActionListener(getParam());
				
			// Code de l'action comme paramètre à envoyer au controleur
				v$menuItem.getChildren().add(getParam(t.getCode()));
				v$menuItem.addActionListener(getParam(t.getCode()));
				v$fonction.getChildren().add(getParam(t.getCode()));
				v$fonction.addActionListener(getParam(t.getCode()));
							
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
				
				String v$codeTrt = t.getCode();				
				v$hotKey.setHandler("updateMenu();if(activateHotKey['"+v$codeTrt+"'])"+t.getMethode()+"_"+t.getCode().replace('-', '_')+"();return false;");		
				v$fonction.setAction(v$method);
											
			// Activation ou désactivation de l'action selon le contexte de page
				v$menuItem.setRendered(t.getConfigIHM()[getIndex()] && t.getRenderedBool() );
				v$fonction.setRendered(t.getConfigIHM()[getIndex()] && t.getRenderedBool() );
				
			// Spécification du type de modal panel				
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));	
					v$fonction.setOncomplete(getConfirmDialog(t));
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
					v$hotKey.setHandler("updateMenu();if(activateHotKey['"+v$codeTrt+"']){"+getConfirmDialog(t)+""+t.getMethode()+"_"+t.getCode().replace('-', '_')+"();}return false;");
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
				v$fonction.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());	
				v$fonction.setReRender(t.getReRender());
					
			// Ajout de l'action au menu 
				v$menuSpe.getChildren().add(v$menuItem);
				v$toolBarGroupHotKey.getChildren().add(v$hotKey);
				v$toolBarGroupHotKey.getChildren().add(v$fonction);
				//v$toolBar.getChildren().add(v$toolBarGroupHotKey);
			}
			
			// Ajout du séparateur entre les actions spécifiques et les actions de navigation
			if(trtNavigation.size() > 0 && trtSpecifiques.size() > 0 ){
				v$menuSpe.getChildren().add(new HtmlMenuSeparator());
			}
			
			// Actions de Navigation			
			for(Traitement t: trtNavigation){
					
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				
			// ID de l'action
				v$menuItem.setId("menu-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				
			// Envoi des paramètres au controleur de destination				
				v$menuItem.getChildren().add(getParam(getHidden(), "#{"+ t.getCtrlDestination()+".defaultVue.navigationMgr.relatedSource}"));
				v$menuItem.addActionListener(getParam(getHidden(), "#{"+t.getCtrlDestination()+".defaultVue.navigationMgr.relatedSource}"));
				
			// MAJ des paramètres du controleur de la source						
				v$menuItem.getChildren().add(getParam2(t.getCode(), "#{"+getNomControleur()+".defaultVue.navigationMgr.relatedDestination}"));
				v$menuItem.addActionListener(getParam2(t.getCode(),"#{"+getNomControleur()+".defaultVue.navigationMgr.relatedDestination}"));
							
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
											
			// Activation ou désactivation de l'action selon le contexte de page
				v$menuItem.setRendered(t.getConfigIHM()[getIndex()]);
				
			// Spécification du type de modal panel				
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));						
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());			
					
			// Ajout de l'action au menu 
				v$menuSpe.getChildren().add(v$menuItem);
														
			}					
						
		}
							
		// Ajout des menus dans le toolbarGroup de gauche 
		v$toolBarGroup.getChildren().add(v$menuBase);				
		v$toolBarGroup.getChildren().add(v$menuSpe);
		v$toolBarGroup.getChildren().add(v$menuAide);
					
		// Ajout des menus dans le toolbarGroup de droite
		 v$toolBarGroup2.getChildren().add(v$menuNote);
			
		// Ajout des toolbarGroup dans le yoolBar
		v$toolBar.getChildren().add(v$toolBarGroup);										 		 		 		 	 
		 v$toolBar.getChildren().add(v$toolBarGroup2);
		 v$toolBar.getChildren().add(v$toolBarGroupHotKey);
		 	
		 return v$toolBar;		 
	}
	
		

	
	
	public ContextMenu getContextMenu(){	
		
		
		ContextMenu contextMenu = null;
		HtmlMenuItem v$menuItem;
		HtmlMenuGroup v$menu;
		MethodBinding v$method;
								
						
		if(getPageContexte().equals("LISTE")){
			
			// Barre d'outils pour les actions de base et spécifiques 
			contextMenu = new ContextMenu();
			
			//contextMenu.setId("ctmn_menu");
			contextMenu.setAttachTo("tble_table");
			//contextMenu.setAttached(false);			
			contextMenu.setSubmitMode("ajax");
			contextMenu.setOnexpand("updateMenu();");
			
			v$menu = new HtmlMenuGroup();
			v$menu.setValue("Actions Spécifiques");
			///v$menu.setValue("Actions Spécifiques");
			v$menu.setId("actions_spec");
						
			// Actions de Base 			
			for(Traitement t: trtStandards){
								
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				
			// ID de l'action
				v$menuItem.setId("contextMenu-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
								
				
			// Activation ou désactivation du bouton selon le contexte de page
				//v$menuItem.setDisabled(! t.getConfigIHM()[1]);
				v$menuItem.setRendered(t.getConfigIHM()[1]);
				
				
			// Spécification du type de modal panel
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));						
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());			
					
			// Ajout de l'action au menu 
				contextMenu.getChildren().add(v$menuItem);				
			}
			
			// Actions Spécifiques			
			for(Traitement t: trtSpecifiques){	
																
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				
			// ID de l'action
				v$menuItem.setId("contextMenu-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				
			// Code de l'action comme paramètre à envoyer au controleur
				v$menuItem.getChildren().add(getParam(t.getCode()));
				v$menuItem.addActionListener(getParam(t.getCode()));
							
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
											
			// Activation ou désactivation de l'action selon le contexte de page
				v$menuItem.setRendered(t.getConfigIHM()[1]);
				
			// Spécification du type de modal panel				
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));						
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());			
					
			// Ajout de l'action au menu 								
				v$menu.getChildren().add(v$menuItem);								
				
			}
			
			// Ajout du séparateur entre les actions spécifiques et les actions de navigation
			if(trtNavigation.size() > 0 && trtSpecifiques.size() > 0){
				v$menu.getChildren().add(new HtmlMenuSeparator());
			}
			
			// Actions de Navigation			
			for(Traitement t: trtNavigation){
					
			// Instance du menu
				v$menuItem = new HtmlMenuItem();
				
			// ID de l'action
				v$menuItem.setId("contextMenu-"+t.getCode());
				
			// Libellé de l'action
				String v$libelle = t.getLibelle() + ((t.getClavier() == null || t.getClavier().trim().isEmpty()) ? "" : " - "+ t.getClavier());
				v$menuItem.setValue( v$libelle /* t.getLibelle()+" - "+t.getClavier()) */);
				
			// Description de l'action
					// TODO A intégrer à l'aide du composant Tooltip
				
			// Exécution en mode ajax
				v$menuItem.setAjaxSingle(true);
								
			// Définition du contexte de Page
				v$menuItem.getChildren().add(getParam());
				v$menuItem.addActionListener(getParam());
				
			// Envoi des paramètres au controleur de destination				
				v$menuItem.getChildren().add(getParam(getHidden(), "#{"+ t.getCtrlDestination()+".defaultVue.navigationMgr.relatedSource}"));
				v$menuItem.addActionListener(getParam(getHidden(), "#{"+t.getCtrlDestination()+".defaultVue.navigationMgr.relatedSource}"));
				
			// MAJ des paramètres du controleur de la source						
				v$menuItem.getChildren().add(getParam2(t.getCode(), "#{"+getNomControleur()+".defaultVue.navigationMgr.relatedDestination}"));
				v$menuItem.addActionListener(getParam2(t.getCode(),"#{"+getNomControleur()+".defaultVue.navigationMgr.relatedDestination}"));
							
			// Définition de la méthode a exécuter
				v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
				v$menuItem.setAction(v$method);
											
			// Activation ou désactivation de l'action selon le contexte de page
				v$menuItem.setRendered(t.getConfigIHM()[1]);
				
			// Spécification du type de modal panel				
				if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
						|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
				{						
					v$menuItem.setOncomplete(getConfirmDialog(t));						
				}
				else{
					v$menuItem.setOnclick(getConfirmDialog(t));
				}				
				
			// Spécification du ProgressBar;
				v$menuItem.setStatus(t.getProgressBar());
										
			// Zone à raffraichir
				v$menuItem.setReRender(t.getReRender());			
					
			// Ajout de l'action au menu 
				v$menu.getChildren().add(v$menuItem);			
			}		
			
			if(v$menu.getChildCount() > 0){
				contextMenu.getChildren().add(v$menu);
			}
						
		}
							
		return contextMenu;
	}
	
/*	
	public HtmlToolBar getToolbar(){
		
		HtmlToolBar v$toolBar;
		HtmlAjaxCommandButton v$bouton;
		MethodBinding v$method;		
		
		// Création  de la barre d'outils
		v$toolBar = new HtmlToolBar();
		// Définition du type de séparateurs des boutons 
		v$toolBar.setItemSeparator("line");
		v$toolBar.setOnmouseover("updateMenu();");
				
		// Pour chaque traitements standards
		for(Traitement t: trtStandards){
				
			// Création d'un bouton ajax
			v$bouton = new HtmlAjaxCommandButton();
			
			// ID de l'action
			v$bouton.setId("toolbar-"+t.getCode());
			
			
			// Commentaire associé au bouton
			v$bouton.setTitle(t.getDescription()+" - "+t.getClavier());
			
			// Image du bouton
			v$bouton.setImage(t.getImage());
			
			// Requête de type ajax
			v$bouton.setAjaxSingle(true);
						
			// Définition du contexte de page
			v$bouton.getChildren().add(getParam());
			v$bouton.addActionListener(getParam());
			
			// Définition de la méthode a exécuter
			v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
			v$bouton.setAction(v$method);
			
			// Activation ou désactivation du bouton selon le contexte de page
			v$bouton.setDisabled(! t.getConfigIHM()[getIndex()]);
			
			// Spécification du type de modal panel
			if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
					|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
			{									
				v$bouton.setOncomplete(getConfirmDialog(t));						
			}
			else{
				v$bouton.setOnclick(getConfirmDialog(t));
			}				
			
			// Spécification du ProgressBar;
			v$bouton.setStatus(t.getProgressBar());
									
			// Zone à raffraichir
			v$bouton.setReRender(t.getReRender());			
			
		
			// Ajout du boutton
			v$toolBar.getChildren().add(v$bouton);
						
		}
			
		// Retour du Toolbar
		return v$toolBar;
	}		
	
*/
	
	public HtmlToolBar getToolbar(){
		
		HtmlToolBar v$toolBar;
		HtmlAjaxCommandButton v$bouton;
		MethodBinding v$method;		
		
		// Création  de la barre d'outils
		v$toolBar = new HtmlToolBar();

		// Définition du type de séparateurs des boutons 
//		v$toolBar.setItemSeparator("line");
		v$toolBar.setOnmouseover("updateMenu();");

		// ToolBarGroup pour les actions de base 
		HtmlToolBarGroup v$toolBarGroup = new HtmlToolBarGroup();		
		v$toolBarGroup.setItemSeparator("line");
//		v$toolBarGroup.setOnitemmouseover("updateMenu();");
		
		// ToolBarGroup pour la recherche par id
		HtmlToolBarGroup v$toolBarGroup2 = new HtmlToolBarGroup();
		v$toolBarGroup2.setLocation("right");
	
				
		// Pour chaque traitements standards
		for(Traitement t: trtStandards){
				
			// Création d'un bouton ajax
			v$bouton = new HtmlAjaxCommandButton();
			
			// ID de l'action
			v$bouton.setId("toolbar-"+t.getCode());
			
			
			// Commentaire associé au bouton
			v$bouton.setTitle(t.getDescription()+" - "+t.getClavier());
			
			// Image du bouton
			v$bouton.setImage(t.getImage());
			
			// Requête de type ajax
			v$bouton.setAjaxSingle(true);
						
			// Définition du contexte de page
			v$bouton.getChildren().add(getParam());
			v$bouton.addActionListener(getParam());
			
			// Définition de la méthode a exécuter
			v$method = application.createMethodBinding("#{"+getNomControleur()+"."+t.getMethode()+"}", null);
			v$bouton.setAction(v$method);
			
			// Activation ou désactivation du bouton selon le contexte de page
			v$bouton.setDisabled(! t.getConfigIHM()[getIndex()]);
			
			// Spécification du type de modal panel
			if(t.getModalType().equals(Traitement.MODAL_FILE)  || t.getModalType().equals(Traitement.MODAL_MOTIF)  
					|| t.getModalType().equals(Traitement.MODAL_SPECIAL))
			{									
				v$bouton.setOncomplete(getConfirmDialog(t));						
			}
			else{
				v$bouton.setOnclick(getConfirmDialog(t));
			}				
			
			// Spécification du ProgressBar;
			v$bouton.setStatus(t.getProgressBar());
									
			// Zone à raffraichir
			v$bouton.setReRender(t.getReRender());			
			
			// Ajout du boutton
			v$toolBarGroup.getChildren().add(v$bouton);					
		}

		// Ajout des options dans le toolbarGroup de droite pour un formulaire DETAILS
		if(getPageContexte().equals("DETAILS") ){
			
			HtmlPanelGroup v$panelGroup = new HtmlPanelGroup();
			
			String v$inputTextId = "idEntityToDisplay";
			String v$searchBoutonId = "idSearchBouton";
			
			HtmlInputText v$inputText = new HtmlInputText();
			v$inputText.setId(v$inputTextId);
			v$inputText.setTitle("MenuFactory.getToolbar");
			v$inputText.setStyle("text-align:right");
			ValueExpression v$valueExpression = application.getExpressionFactory().createValueExpression(facesContext.getELContext(), "#{" +getNomControleur() + "." + "idEntityToDisplay}", String.class);
			v$inputText.setValueExpression("value", v$valueExpression);	
			v$inputText.setOnkeydown("if(event.keyCode == '13') document.getElementById('actionForm:"+v$searchBoutonId+"').onclick();");
			
			HtmlAjaxCommandButton v$searchBouton = new HtmlAjaxCommandButton();
			v$searchBouton.setId(v$searchBoutonId);
			v$searchBouton.setTitle("MenuFactory.getToolbar2");
			v$searchBouton.setImage("/shared/images/search-16x16.png");
			v$searchBouton.setAjaxSingle(true);
			v$searchBouton.setProcess(v$inputTextId);
			v$searchBouton.setStatus(Traitement.PROGRESS_SIMPLE);
			v$searchBouton.setReRender(Traitement.RERENDER_MAIN_PANEL);			
			v$method = application.createMethodBinding("#{" +getNomControleur() + "." + "afficherParId}", null);
			v$searchBouton.setAction(v$method);
	
			v$panelGroup.getChildren().add(v$inputText);
			v$panelGroup.getChildren().add(v$searchBouton);
			
			v$toolBarGroup2.getChildren().add(v$panelGroup);			
		}
		
		// Ajout des toolbarGroup dans le toolBar
		 v$toolBar.getChildren().add(v$toolBarGroup);										 		 		 		 	 
		 v$toolBar.getChildren().add(v$toolBarGroup2);
					
		// Retour du Toolbar
		return v$toolBar;
	}	
	
	
		
}
