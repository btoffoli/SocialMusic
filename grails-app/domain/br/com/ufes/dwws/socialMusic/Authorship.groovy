package br.com.ufes.dwws.socialMusic

class Authorship {

    String name
    String page

    static hasMany = [albuns: Album]

    static constraints = {
    }
}
