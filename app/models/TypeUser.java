package models;

import models.utils.AppException;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeUser extends Model {

	@Id
	public Long id;

    @Formats.NonEmpty
	public String name;
    
    public static Model.Finder<Long, TypeUser> find = new Model.Finder<Long, TypeUser>(Long.class, TypeUser.class);
}
