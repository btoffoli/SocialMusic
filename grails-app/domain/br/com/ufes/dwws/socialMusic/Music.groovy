package br.com.ufes.dwws.socialMusic

class Music {
    static rdf = [
            '':'http://xmlns.com/foaf/0.1/Music',
            'name':'http://xmlns.com/foaf/0.1/name',
            'album': 'http://xmlns.com/foaf/0.1/album'

            //'friends':'http://xmlns.com/foaf/0.1/knows'
    ]
    String name
    Album album
    String url

    static constraints = {

    }

    static mappedBy = [
            album: 'musics'

    ]

    static belongsTo = Profile

    static hasMany = [profiles: Profile]


}
