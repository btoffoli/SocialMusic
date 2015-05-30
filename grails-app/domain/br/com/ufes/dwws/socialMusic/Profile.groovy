package br.com.ufes.dwws.socialMusic

class Profile {

    String name
    String avatarURL

    Account account


    static constraints = {
        name(nullable: false, blank: false)
        avatarURL(nullable: true)
        account(nullable: false)
    }

    static hasMany = [musics: Music]


}
