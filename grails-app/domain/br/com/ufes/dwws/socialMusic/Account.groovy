package br.com.ufes.dwws.socialMusic

class Account {

    String login
    String password
    String email



    void beforeInsert() {
        if (!profile) {
            this.profile = new Profile(account: this, name: "Profile - Account: $this")
        }
    }

    static hasOne = [profile: Profile]

    static constraints = {
        login(nullable: false, unique: true)
        email(nullable: false,unique: true)
        password(nullable: false)
        profile(nullable: true)
    }


}
