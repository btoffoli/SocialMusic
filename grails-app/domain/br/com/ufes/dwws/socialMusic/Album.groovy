package br.com.ufes.dwws.socialMusic

class Album {

    String name
    String page
    Authorship authorship

    static hasMany = [musics: Music]

    static mappedBy = [
            authorship : 'albuns'
    ]

    static constraints = {
    }
}
