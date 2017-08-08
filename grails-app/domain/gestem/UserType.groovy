package gestem

class UserType {

    String name
    String description

    static hasMany = [user:User]

    static constraints = {
    }
}
