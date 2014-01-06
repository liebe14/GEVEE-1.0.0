package com.gss.gevee.ui.core.controleur;


import javax.faces.event.ActionEvent;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.ui.admin.util.AdminSvcoDeleguate;
import com.gss.gevee.ui.core.base.FacesUtil;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.vue.SessionVue;

public class SessionCtrl {


	private static BaseLogger logger = BaseLogger.getLogger(SessionCtrl.class);
	
	private boolean connecter = false;

	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration
	 */
	private static String nomManagedBean = "sessionCtrl";

	/**
	 * Vue du Controleur
	 */
	SessionVue defaultVue;

	/**
	 * Thème (Couleur) par défaut de l'application
	 */
	String skin = "classic";


	public SessionCtrl() {

		// Instance de la vue
		defaultVue = new SessionVue();
	}


	/**
	 * Retourne le nom du Bean Managé par JSF dans le Fichier de Configuration
	 * Utilile pour ne pas avoir a ecrire le nom des Beans en dur dans le Code
	 * 
	 * @return
	 */
	public static String getNomManagedBean() {
		return nomManagedBean;
	}

	/**
	 * @return the defaultVue
	 */
	public SessionVue getDefaultVue() {
		return defaultVue;
	}

	/**
	 * @param defaultVue
	 *            the defaultVue to set
	 */
	public void setDefaultVue(SessionVue defaultVue) {
		this.defaultVue = defaultVue;
	}

	/**
	 * Retourne un Logger pour la Classe
	 * 
	 * @return
	 */
	public BaseLogger getLogger() {
		return logger;
	}

	/**
	 * @return the connecter
	 */
	public boolean isConnecter() {
		return connecter;
	}

	/**
	 * @param connecter
	 *            the connecter to set
	 */
	public void setConnecter(boolean connecter) {
		this.connecter = connecter;

		// Injection du statut de connexion de l'utilisateur dans le scope
		// session
		FacesUtil.setSessionMapValue("ISLOGGED", connecter);
	}

	/**
	 * @return the skin
	 */
	public String getSkin() {
		return skin;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin) {
		this.skin = skin;
	}


	/****
	 * Authentifie un utilisateur, Permet de savoir si ce dernier peut ou non se connecter a l'application
	 * 
	 * @return 
	 */
	public String authentification(){
		
		String v$navigation = null;
		try {
			TabUsr	usr = AdminSvcoDeleguate.getSvcoUsr().authenticate(defaultVue.getLogin(), defaultVue.getPassword());
			if(usr != null && usr.getCodUsr() != null && !usr.getPwdExpire()){
				// Navigation vers le formulaire d'acceuil
				defaultVue.setUser(usr);
				v$navigation = "AcceuilDeBase";
			}else if(usr.getPwdExpire()){
				FacesUtil.addWarnMessage("Votre mot de passe a expiré : veuillez contacter l'administrateur", "Votre mot de passe a expiré : veuillez contacter l'administrateur");
			}
			else{
				FacesUtil.addWarnMessage("Login/Mot de passe incorrect", "Login/Mot de passe incorrect");
			}
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return v$navigation;
	}

	/***
	 * Annule l'authentification d'un utilisateur et le redirige a la connexion
	 * 
	 * @return
	 */
	public String retourPageAuthentification() {
		defaultVue.setPassword(null);
		// Retour à la page d'authentification
		return "LoginPassword";
	}
	public String continuerConnexion() {

		String v$navigation = "AcceuilDeBase";

		return v$navigation;
	}

	/***
	 * Validation du Choix du module
	 * 
	 * @return
	 */
	public String validerChoixModule() {

		String v$navigation = "Contexte";

		return v$navigation;
	}

	/****
	 * Dirige l'utilisateur vers le formulaire d'acceuil adéquat après
	 * authentification
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public String validerConnexion() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		try {
			TabUsr	usr = AdminSvcoDeleguate.getSvcoUsr().authenticate(defaultVue.getLogin(), defaultVue.getPassword());
			if(usr != null && usr.getCodUsr() != null){
				// Mise à jour du statut de connexion
				setConnecter(true);
				// Navigation vers le formulaire d'acceuil
				v$navigation = "AcceuilDeBase";
			}
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return v$navigation;

	}
	
	/***
	 * Deconnecte l'utilisateur de l'application et le redirige vers le
	 * Formulaire d'authentification
	 * 
	 * @return
	 */
	public String deconnexion() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;

		/***
		 * TODO Implementation Basique a finaliser
		 */

		// Raz des paramètres de connexion de l'utilisateur
		// defaultVue.setAuthentifier(false);
		setConnecter(false);

		defaultVue.setLogin("");
		defaultVue.setPassword("");
		// defaultVue.setBudgetCourant(new TblBdg());
		// defaultVue.setRoleCourant(new TblRol());
		defaultVue.setUser(null);

		// Vider le Scope session
		FacesUtil.getSessionMap().clear();

		// Redirection vers le formulaire d'authentification
		v$navigation = "Login";

		//Decrementer le nombre d'user connecté
		////CountUserBean.decrementCounter();
		
		// Retour à la page adéquate
		return v$navigation;
	}

	/****
	 * Modifie le mot de passe de l'utilisateur
	 * 
	 * @return
	 */
	public String modifyPassword() {

		String v$navigation = null;

		String v$oldPwd = getDefaultVue().getOldPassword();
		String v$newPwd = getDefaultVue().getNewPassword();
		String v$confirmPwd = getDefaultVue().getConfirmNewPassword();

		if (v$newPwd.equals(v$confirmPwd)) {
			try {

				AdminSvcoDeleguate.getSvcoUsr().modifierPwd(getDefaultVue().getLogin(), v$oldPwd, v$newPwd);

				 getDefaultVue().setModified(true);



				 FacesUtil.addInfoMessage("", "Modification de mot de passe réussie");
			}

			catch (GeveeAppException e) {
				e.printStackTrace();
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			}

		} else {
			FacesUtil.addWarnMessage("", "echec");
		}

		return v$navigation;
	}


	/****
	 * Annule la modification mot de passe de l'utilisateur
	 * 
	 * @param evt
	 */
	public void annulerModifPassword(ActionEvent evt) {

		defaultVue.setOldPassword(null);
		defaultVue.setNewPassword(null);
		defaultVue.setConfirmNewPassword(null);

	}

	/****
	 * Terminer ou fermer la fenêtre de modification de mot de passe de
	 * l'utilisateur
	 * 
	 * @param evt
	 */
	public void terminerModifPassword(ActionEvent evt) {
		// Mise à jour du champ de modification
		defaultVue.setModified(false);
	}

	/**
	 * Retourne l'instance (Bean Managé)
	 * 
	 * @return
	 */
	public static SessionCtrl getManagedBeanInstance() {

		SessionCtrl v$instance = (SessionCtrl) FacesUtil
				.getSessionMapValue(getNomManagedBean());

		if (v$instance == null) {
			v$instance = new SessionCtrl();
			FacesUtil.setSessionMapValue(getNomManagedBean(), v$instance);
		}
		return v$instance;
	}
	
}
