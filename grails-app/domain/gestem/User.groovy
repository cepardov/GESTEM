package gestem

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import org.grails.datastore.mapping.query.Query

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	String username
	String password
	String rut
	String nombre
	String segNombre
	String paterno
	String materno
	Date fechaNacimiento
	String genero
	String nacionalidad
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [direccion:Direccion, telefono:Telefono, correo:Correo]
	static belongsTo = [userType:UserType, institucion:Institucion]

	Set<Role> getAuthorities() {
		(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
	}

	static constraints = {
		rut unique: true
		password blank: false, password: true
		username blank: false, unique: true
		fechaNacimiento blank:true, nullable: true
		genero blank:true, nullable: true
		nacionalidad blank:true, nullable: true

		institucion nullable: true, blank: true
	}

	//static mapping = {
	//	password column: '`password`'
	//}
}
