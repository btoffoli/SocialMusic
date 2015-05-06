package br.com.ufes.dwws.socialMusic

import grails.transaction.Transactional

@Transactional
class AccountsManagerService {

//    static scope = "session"
    static proxy = true

    Account login(Account account) {
        return  Account.findByLoginAndPassword(account.login, account.password)
    }
}
