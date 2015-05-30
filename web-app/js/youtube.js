//STARTING YOUTUBE API

var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

//EVENT LOGIC

function onYouTubeIframeAPIReady() {
  onYoutubeReady();
}

function startPlayer(playerID, videoURL, playOnReady) {

	var mainPlayer = new YT.Player(playerID, {
		height: '390',
		width: '640',
		videoId: extractVideoID(videoURL),
		events: {
			'onReady': function(event) {
        if (playOnReady)
				  event.target.playVideo();
			}
		}
	});
}

//UTILS

function extractVideoID(url) {
	var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
    var match = url.match(regExp);
    if (match&&match[7].length==11){
        return match[7];
    }else{
        console.log("URL invalida");
        return false;
    }
}
