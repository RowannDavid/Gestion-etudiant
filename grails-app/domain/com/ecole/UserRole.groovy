package com.ecole

class UserRole implements Serializable {

	User user
	Role role

	@Override
	boolean equals(other) {
		if (other instanceof UserRole) {
			other.userId == user?.id && other.roleId == role?.id
		}
	}

	@Override
	int hashCode() {
		int hashCode = 31
		if (user) hashCode += user.id.hashCode()
		if (role) hashCode += role.id.hashCode()
		return hashCode
	}

	static UserRole create(User user, Role role, boolean flush = false) {

		def existing = UserRole.findByUserAndRole(user, role)

		if (existing) {
			return existing
		}

		def instance = new UserRole(
				user: user,
				role: role
		)

		instance.save(
				flush: flush,
				failOnError: true
		)

		instance
	}
	static boolean exists(Long userId, Long roleId) {
		UserRole.where {
			user == User.load(userId) &&
					role == Role.load(roleId)
		}.count() > 0
	}

	static boolean remove(User u, Role r) {
		if (u != null && r != null) {
			UserRole.where { user == u && role == r }.deleteAll()
		}
	}



	static constraints = {
	}

	static mapping = {
		id composite: ['user', 'role']
		version false
	}
}