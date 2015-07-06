package br.com.ufes.dwws.socialMusic



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MusicController {

    RdfService rdfService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Music.list(params), model:[musicInstanceCount: Music.count()]
    }

    private String formatTimeMillis(Integer milliSeconds) {
        String result = "${(milliSeconds/60000) as Integer}:${((milliSeconds/1000 as Integer)%60)}"

        return result
    }


    def show(Music musicInstance) {
        Map<String, Object> obj = null

        List<Map<String, Object>> rdfMusicData = rdfService.getMusicData(musicInstance.name, musicInstance.album.authorship.name)
        if (rdfMusicData) {
            Integer maxTime = Integer.MIN_VALUE
            Integer minTime = Integer.MAX_VALUE
            rdfMusicData.each { Map<String, Object> map ->
                Integer timeAux = (map.trackDuration as String).replace('\"', '') as Integer
                if (timeAux < minTime)
                    minTime = timeAux

                if (timeAux > maxTime)
                    maxTime = timeAux
            }

            obj = [
                    maxTime: formatTimeMillis(maxTime),
                    minTime: formatTimeMillis(minTime)
            ]
        }

        respond musicInstance, model: [rdfMusicData: obj]
    }

    def create() {
        respond new Music(params)
    }

    @Transactional
    def save(Music musicInstance) {
        if (musicInstance == null) {
            notFound()
            return
        }

        if (musicInstance.hasErrors()) {
            respond musicInstance.errors, view:'create'
            return
        }

        musicInstance.save flush: true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musicInstance.label', default: 'Music'), musicInstance.id])
                redirect musicInstance
            }
            '*' { respond musicInstance, [status: CREATED] }
        }
    }

    def edit(Music musicInstance) {
        respond musicInstance
    }

    @Transactional
    def update(Music musicInstance) {
        if (musicInstance == null) {
            notFound()
            return
        }

        if (musicInstance.hasErrors()) {
            respond musicInstance.errors, view:'edit'
            return
        }

        musicInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Music.label', default: 'Music'), musicInstance.id])
                redirect musicInstance
            }
            '*'{ respond musicInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Music musicInstance) {

        if (musicInstance == null) {
            notFound()
            return
        }

        musicInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Music.label', default: 'Music'), musicInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musicInstance.label', default: 'Music'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }


    def lastAdded() {

        Music music = Music.last()

        render music.url
    }
}
