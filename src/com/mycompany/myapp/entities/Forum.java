package esprit.pidev.entitys;

import java.util.Date;

public class Forum {
	private int id ;
	private String titre, contenu, commentaire, categorie, photo;
	private Date datecreation;

	public Forum(int id, String titre, String contenu, String commentaire, String categorie, String photo, Date datecreation) {
		this.id = id;
		this.titre = titre;
		this.contenu = contenu;
		this.commentaire = commentaire;
		this.categorie = categorie;
		this.photo = photo;
		this.datecreation = datecreation;
	}

	public Forum() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	@Override
	public String toString() {
		return "Forum [id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", commentaire=" + commentaire
				+ ", categorie=" + categorie + ", photo=" + photo + ", datecreation=" + datecreation + "]";
	}

	public byte[] getAuthor() {
		return null;
	}

	public static void add(Forum a) {
	}
}
