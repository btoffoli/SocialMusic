package br.com.ufes.dwws.socialMusic

class Profile {

    String name
    String avatarURL
    Account account

    List<Favoritable> favoritables

    static constraints = {
        name(nullable: false, blank: false)
        avatarURL(nullable: false, blank: false)
        account(nullable: false)
    }
}
