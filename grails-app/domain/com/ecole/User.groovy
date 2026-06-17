package com.ecole

import com.ecole.Role
import com.ecole.UserRole
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
	}

	static constraints = {
		password blank: false
		username blank: false, unique: true
		email blank: false, email: true, unique: true
	}

	static mapping = {
		table 'app_user'
		password column: '`password`'
	}
}