package socialmusic

class LoginFilters {


    static Set<Map<String, String>> nonAuthenticatedActions = [
            [controller: 'console', action: '*'],
            [controller: 'account', action: 'signIn'],
            [controller: 'account', action: 'loginForm'],
            [controller: 'account', action: 'saveNewAccount'],
            [controller: 'account', action: 'newAccount'],
            [controller: 'account', action: 'home'],
            [controller: 'music', action: 'lastAdded'],
            [controller: 'album', action: 'lastAdded'],
            [controller: 'authorship', action: 'lastAdded'],
            [controller: 'RDF', action: '*']

    ]

    private static boolean actionRequiresAuthentication(String controllerName, String actionName) {
        Map<String, String> key = [
                controller: controllerName, action: actionName
        ];
        // Ação específica liberada
        if (nonAuthenticatedActions.contains(key)) {
            return false;
        }
        // Controlador inteiro liberado
        key.action = "*";
        if (nonAuthenticatedActions.contains(key)) {
            return false;
        }
        return true;
    }

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                if (!controllerName) {
                    return true
                }



                if (actionRequiresAuthentication(controllerName, actionName) && !session?.objetoLogin) {
                    redirect(controller: 'account', action: 'home')
                    return false
                } else {
                    return true
                }
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
