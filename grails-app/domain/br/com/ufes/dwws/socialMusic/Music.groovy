package br.com.ufes.dwws.socialMusic

class Music {

    String name
    Album album
    String url

    static constraints = {
    }

    static mappedBy = [album: 'musics']
}
