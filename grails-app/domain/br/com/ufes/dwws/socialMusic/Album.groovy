package br.com.ufes.dwws.socialMusic

class Album {

    String name
    String page

    static hasMany = [musics: Music]

    static constraints = {
    }
}
