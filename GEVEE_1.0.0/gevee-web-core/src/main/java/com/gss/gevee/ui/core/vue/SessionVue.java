package com.gss.gevee.ui.core.vue;

import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.base.BaseLogger;

public class SessionVue {
	
	
	/*=========================================================== ~ Debut Region ~ =================================================================*/
	/**	~ Propriétés ~ 	**/    
	/*=============================================================================================================================================*/	
	
	
	private String menu;
	private String menuAlign = "top";
	
	/**
	 * Etat du Menu - Partie Gauche intitulé Navigateur
	 */
	private String menuState;
	
	private String statut;
	

	private TabUsr user;
		
	private String login;
	private String password;
	
	private Map<String, Boolean> coherenceMenu;
	
	/**
	 * Chemin du logo du module sur lequel l'utilisateur est connecté
	 */
	private String cheminLogoModule = "/shared/images/cameroun_a.gif";
	
	/**
	 * Attribut qui s'active à la connexion si le mot de passe de l'utilisateur a expiré
	 */
	public boolean modifierMotDePasse = false;
	
	/**
	 * Propriété permettant de gérer l'exactitude du mot de passe à modifier
	 */
	private String oldPassword;

	/**
	 * Propriété permettant de gérer l'exactitude du nouveau mot de passe saisi par l'utilisateur 
	 */
	private String newPassword;
	
	/**
	 * Propriété permettant de gérer la confirmation du nouveau mot de passe en cas de modification de celui-ci 
	 */
	private String confirmNewPassword;
	
	/**
	 * Propriété permettant de savoir si l'opération de modification du mot de passe s'est bien déroulée
	 */
	private boolean modified = false;

	/**
	 * Propriété contenant la valeur active ou non du panel profil du menu de gauche
	 */
	private boolean profileMenuOpened = true;

	public SessionVue() {
																
	}
	/**
	 * @return the user
	 */
	public TabUsr getUser() {
		
		if(user == null){
			user = new TabUsr();
		}
		return user;
	}
	/**
	 * @return the profileMenuOpened
	 */
	public boolean isProfileMenuOpened() {
		return profileMenuOpened;
	}


	/**
	 * @param profileMenuOpened the profileMenuOpened to set
	 */
	public void setProfileMenuOpened(boolean profileMenuOpened) {
		this.profileMenuOpened = profileMenuOpened;
	}


	/**
	 * @return the cheminLogoModule
	 */
	public String getCheminLogoModule() {
		return cheminLogoModule;
	}
	/**
	 * 
	 * @param cheminLogoModule
	 */
	public void setCheminLogoModule(String cheminLogoModule) {
		this.cheminLogoModule = cheminLogoModule;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(TabUsr user) {
		this.user = user;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}


	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}


	/**
	 * @return the menuAlign
	 */
	public String getMenuAlign() {
		return menuAlign;
	}

	/**
	 * @param menu_align the menu_align to set
	 */
	public void setMenuAlign(String menuAlign) {
		this.menuAlign = menuAlign;
	}
	
	
	/**
	 * @return the statut
	 */
	public String getStatut() {
		statut = "Connecté";
		return statut;
	}


	/**
	 * @param statut the statutBudget to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	/**
	 * @return the menuState
	 */
	public String getMenuState() {
		return menuState;
	}


	/**
	 * @param menuState the menuState to set
	 */
	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}


	public boolean getModifierMotDePasse() {
		return modifierMotDePasse;
	}


	public void setModifierMotDePasse(boolean modifierMotDePasse) {
		this.modifierMotDePasse = modifierMotDePasse;
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}


	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}


	public boolean isModified() {
		return modified;
	}


	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public BaseLogger getLogger(){
		return BaseLogger.getLogger(SessionVue.class);
	}

	/**
	 * @param coherenceMenu the coherenceMenu to set
	 */
	public void setCoherenceMenu(Map<String, Boolean> coherenceMenu) {
		this.coherenceMenu = coherenceMenu;
	}

	/**
	 * @return the coherenceMenu
	 */
	public Map<String, Boolean> getCoherenceMenu() {
		
		return coherenceMenu;
	}

	/**
	 * Affiche le Contenu des listes de Combox
	 * @param p$liste
	 */
	private void trace(List<SelectItem> p$liste){
		
		System.out.println("SessionVue.trace()		" + p$liste.toString());
		
		if(p$liste != null && ! p$liste.isEmpty()){
			
			for(SelectItem v$item: p$liste){
				System.out.println("	Code : " + v$item.getValue().toString() +"					Libellé : " + v$item.getLabel());
			}
			System.out.println("\n\n");
		}
		else{
			System.out.println("	Liste nulle ou vide");
		}	
	}
	
}
